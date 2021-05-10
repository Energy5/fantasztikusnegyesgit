package hu.fantasztik.szofttech.state;

import hu.fantasztik.szofttech.framework.Window;

import javax.swing.*;

public class WindowMenu {

    public WindowMenu() throws NullPointerException {
        ImageIcon icon = new ImageIcon(WindowMenu.class.getResource("/assets/4logo.png"));
        String[] options = {"Easy", "Medium", "Hard", "HighScores"};
        String n = (String)JOptionPane.showInputDialog(null, "Choose one wisely",
                "Fantastic Four", JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]);


        if (n != null) {
            Difficulty difficulty;
            if (n.equals(options[0])) {
                difficulty = Difficulty.EASY;
               new Window(difficulty);
            } else if (n.equals(options[1])) {
                difficulty = Difficulty.MEDIUM;
               new Window(difficulty);
            } else if (n.equals(options[2])) {
                difficulty = Difficulty.HARD;
               new Window(difficulty);
            } else if (n.equals(options[3])) {
                HighscoreWindow.createFrame();
            }

        }
    }
}