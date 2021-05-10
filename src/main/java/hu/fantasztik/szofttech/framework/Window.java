package hu.fantasztik.szofttech.framework;

import hu.fantasztik.szofttech.database.Database;
import hu.fantasztik.szofttech.player.Player;
import hu.fantasztik.szofttech.state.Difficulty;
import hu.fantasztik.szofttech.state.Game;
import hu.fantasztik.szofttech.state.HighscoreWindow;
import hu.fantasztik.szofttech.state.TimerLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame implements ActionListener {

    private static ImageIcon coinIcon = new ImageIcon("src/main/resources/assets/coinIcon.gif");
    private static ImageIcon highscoreIcon = new ImageIcon("src/main/resources/assets/highscoreCup.png");
    private static JLabel counter = new JLabel("Score: ", highscoreIcon, JLabel.CENTER);
    private static JLabel playerGold = new JLabel("Gold: ", coinIcon, JLabel.CENTER);
    private static JLabel pause = new JLabel("Press 'p' to pause");
    private static JPanel abovePane;
    private JMenuItem sirEasy, sirMedium, sirHard, highScores;
    private static final int WAIT_TIME = 200;
    private static boolean waitTime = true;
    public static TimerLabel timerLabel;
    static Database Database;
    public static boolean paused = true;

    public Window(Difficulty difficulty) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenu newGameMenu = new JMenu("New Game");
        highScores = new JMenuItem("HighScores");
        newGameMenu.addActionListener(this);
        highScores.addActionListener(this);
        menu.add(newGameMenu);
        menu.add(highScores);

        sirEasy = new JMenuItem("Easy", null);
        sirEasy.addActionListener(this);
        newGameMenu.add(sirEasy);

        sirMedium = new JMenuItem("Medium", null);
        sirMedium.addActionListener(this);
        newGameMenu.add(sirMedium);

        sirHard = new JMenuItem("Hard", null);
        sirHard.addActionListener(this);
        newGameMenu.add(sirHard);

        setJMenuBar(menuBar);

        Database = new Database();

        counter.setFont(new Font("Monaco", Font.PLAIN, 15));
        counter.setHorizontalAlignment(JLabel.CENTER);

        playerGold.setFont(new Font("Monaco", Font.PLAIN, 15));
        playerGold.setHorizontalAlignment(JLabel.CENTER);

        pause.setFont(new Font("Monaco", Font.PLAIN, 15));
        pause.setHorizontalAlignment(JLabel.CENTER);

        abovePane = new JPanel(new GridLayout(2, 3));
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        timerLabel = new TimerLabel();
        timerLabel.setFont(new Font("Monaco", Font.PLAIN, 15));

        abovePane.add(pause, BorderLayout.PAGE_END);
        abovePane.add(timerLabel, BorderLayout.CENTER);
        abovePane.add(counter, BorderLayout.PAGE_START);
        abovePane.add(playerGold, BorderLayout.PAGE_START);

        getContentPane().add(abovePane, BorderLayout.PAGE_START);


        Game game = new Game();
        new Player(difficulty);
        getContentPane().add(game, BorderLayout.CENTER);

        setTitle("Fantastic Four");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        game.setLocation(10, 10);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        Game.initGame();

        counter.setText("Score: " + Game.points);
        playerGold.setText("Gold: " + Player.playerGold);
        pause.setText("Press 'p' to pause");
        addKeyListener(new KeyboardListener());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }

        });

        switch (difficulty) {
            case EASY:
                waitSec(5);
                break;
            case MEDIUM:
                waitSec(2);
                break;
            case HARD:
                waitSec(0);
                break;
        }

        game.run();
    }

    private void showExitConfirmation() {
        timerLabel.stopTimer();
        paused = false;
        int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        } else {
            paused = true;
            timerLabel.startTimer();
        }
    }

    public static void updatePoints(int points) {
        counter.setText("Points: " + points);
    }

    public static void gameEnd() {
        Game.end = true;
        timerLabel.stopTimer();
        paused = false;
        JOptionPane.showMessageDialog(null, "Game Over", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        String name = JOptionPane.showInputDialog(abovePane, "Write your name: ");
        if (name != null) {
            hu.fantasztik.szofttech.database.Database.storeToDatabase(name, Game.points);
            HighscoreWindow.createFrame();
        }
    }

    private static void doUponExit() {
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == sirEasy) {
            Game.newGame = true;
            Game.difficulty = Difficulty.EASY;
            Game.end = false;

        } else if (e.getSource() == sirMedium) {
            Game.newGame = true;
            Game.difficulty = Difficulty.MEDIUM;
            Game.end = false;

        } else if (e.getSource() == sirHard) {
            Game.newGame = true;
            Game.difficulty = Difficulty.HARD;
            Game.end = false;
        } else if (e.getSource() == highScores) {
            HighscoreWindow.createFrame();
        }
    }

    public static void giveGoldPerSec() {

        if (paused) {
            if (timerLabel.time % 30 == 0 && timerLabel.time != 0) {
                try {
                    Player.gainGold(50);
                    playerGold.setText("Gold: " + (Player.playerGold));
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updatePlayerGold() {
        playerGold.setText("Gold: " + (Player.playerGold));
    }

    public static void waitSec(int sec) {
        for (int i = sec; i > 0; i--) {
            counter.setText("" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitTime = false;
        counter.setText("Points: " + Game.points);
        timerLabel.startTimer();
    }

    public static void pause() {
        if (!waitTime) {
            paused = !paused;

            try {
                if (!paused) {
                    JFrame frame = new JFrame();
                    timerLabel.stopTimer();
                    Thread.sleep(WAIT_TIME);

                    JOptionPane.showMessageDialog(frame, "The game is paused.", "Pause Message", JOptionPane.INFORMATION_MESSAGE);
                    paused = true;
                    timerLabel.startTimer();

                } else {
                    timerLabel.startTimer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}