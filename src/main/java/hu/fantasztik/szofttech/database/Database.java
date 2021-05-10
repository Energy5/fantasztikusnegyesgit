package hu.fantasztik.szofttech.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static final String tableName;

    static {
        tableName = "highscores";
    }

    private static Connection conn;
    private static ArrayList<HighScore> highScores;

    public Database() {
        Connection c = null;
        try {
            c = ConnectionFactory.getConnection();
        } catch (Exception ex) {
            System.out.println("No connection");
        }
        conn = c;
        highScores = new ArrayList<>();
    }

    public ArrayList<HighScore> getHighScores() {

        try {
            PreparedStatement stmt = conn.prepareStatement("ALTER TABLE " + tableName + " ORDER BY points DESC");
            stmt.execute();
            String query = "SELECT * FROM " + tableName + " ORDER BY points DESC";
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                int points = rs.getInt("points");
                int id = rs.getInt("id");
                highScores.add(new HighScore(name, points,id));
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return highScores;
    }

    public static void storeToDatabase(String name, int points) {

        try {
            String s = "INSERT INTO " + tableName + " (name, points)"
                    + "VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1, name);
            stmt.setInt(2, points);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}