package hu.fantasztik.szofttech.database;
import java.sql.*;

public class ConnectionFactory{
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/highscores?useUnicode=yes&characterEncoding=UTF-8", "root", "admin");

        } catch (ClassNotFoundException|SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}