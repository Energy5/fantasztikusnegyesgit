package hu.fantasztik.szofttech.state;

import hu.fantasztik.szofttech.database.Database;
import hu.fantasztik.szofttech.database.HighScore;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighscoreWindow {
    private static   Database database = new Database();
    public static void createFrame() {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("High Scores");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setSize(500, 500);
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JPanel panel = new JPanel();

            ArrayList<HighScore> highScores;
            highScores = database.getHighScores();
            String[] cols = new String[]{"name", "points", "id"};
            Object[][] data;
            if (highScores.size() < 10) {
                data = new Object[highScores.size()][3];
            } else {
                data = new Object[10][3];
            }

            for (int i = 0; i < 10 && i < highScores.size(); i++) {
                data[i][0] = highScores.get(i).name;
                data[i][1] = highScores.get(i).points;
                data[i][2] = highScores.get(i).id;
            }

            JTable table = new JTable(data, cols);

            frame.add(new JScrollPane(table));

            frame.pack();
            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }
}
