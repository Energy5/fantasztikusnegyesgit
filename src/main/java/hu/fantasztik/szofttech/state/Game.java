package hu.fantasztik.szofttech.state;

import hu.fantasztik.szofttech.building.Building;
import hu.fantasztik.szofttech.building.mine.Mine;
import hu.fantasztik.szofttech.building.tower.Tower;
import hu.fantasztik.szofttech.building.tower.types.AirTower;
import hu.fantasztik.szofttech.building.tower.types.EarthTower;
import hu.fantasztik.szofttech.building.tower.types.FireTower;
import hu.fantasztik.szofttech.building.tower.types.WaterTower;
import hu.fantasztik.szofttech.enemy.Monster;
import hu.fantasztik.szofttech.framework.Window;
import hu.fantasztik.szofttech.map.Ground;
import hu.fantasztik.szofttech.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends JPanel {

    public          static int points = 0;
    public static ArrayList<ArrayList<Ground>> matrix;
    public volatile static boolean end = false;
    public          static boolean newGame;
    public          static boolean turnFirstKeyPress = true;
    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;
    private final ImageIcon AirTowerIcon = new ImageIcon(WindowMenu.class.getResource("/assets/AirTowerIcon.png"));
    private final ImageIcon FireTowerIcon = new ImageIcon(WindowMenu.class.getResource("/assets/FireTowerIcon.png"));
    private final ImageIcon WaterTowerIcon = new ImageIcon(WindowMenu.class.getResource("/assets/WaterTowerIcon.png"));
    private final ImageIcon EarthTowerIcon = new ImageIcon(WindowMenu.class.getResource("/assets/walnutIcon.png"));
    private final ImageIcon goldMineIcon = new ImageIcon(WindowMenu.class.getResource("/assets/goldmineIcon.png"));
    boolean AirTowerBool = false, EarthTowerBool = false, FireTowerBool = false, WaterTowerBool = false, GoldMineBool = false;
    private final JPopupMenu towerDetailsPopup = new JPopupMenu();
    private Building selectedBuilding;
    public static Difficulty difficulty;

    public Game() {


        JMenuItem airTower = new JMenuItem(new AbstractAction("Air Tower ("+AirTower.priceAirTower+" Gold)", AirTowerIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                AirTowerBool = true;
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/main/resources/assets/AirTower.png").getImage(),
                        new Point(0, 0), "custom cursor"));
            }
        });
        final JPopupMenu menuPopup = new JPopupMenu();

        menuPopup.add(airTower);
        menuPopup.addSeparator();

        JMenuItem earthTower = new JMenuItem(new AbstractAction("Earth Tower ("+EarthTower.priceEarthTower+" Gold)", EarthTowerIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                EarthTowerBool = true;
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/main/resources/assets/walnutKis.png").getImage(),
                        new Point(0, 0), "custom cursor"));
            }
        });

        menuPopup.add(earthTower);
        menuPopup.addSeparator();


        JMenuItem fireTower = new JMenuItem(new AbstractAction("Fire Tower ("+FireTower.priceFireTower+" Gold)", FireTowerIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                FireTowerBool = true;
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/main/resources/assets/FireTowerIcon.png").getImage(),
                        new Point(0, 0), "custom cursor"));
            }
        });

        menuPopup.add(fireTower);
        menuPopup.addSeparator();

        JMenuItem waterTower = new JMenuItem(new AbstractAction("Water Tower ("+WaterTower.priceWaterTower+" Gold)", WaterTowerIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                WaterTowerBool = true;
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/main/resources/assets/WaterTower.png").getImage(),
                        new Point(0, 0), "custom cursor"));
            }
        });

        menuPopup.add(waterTower);
        menuPopup.addSeparator();

        JMenuItem goldMine = new JMenuItem(new AbstractAction("Gold Mine (" + Mine.priceMine +  " Gold)", goldMineIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoldMineBool = true;
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/main/resources/assets/goldmine.png").getImage(),
                        new Point(0, 0), "custom cursor"));
            }
        });

        menuPopup.add(goldMine);


        final JMenuItem sellTower = new JMenuItem(new AbstractAction("Sell Building", null) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedBuilding != null){
                    selectedBuilding.sellBuilding();
                    Window.updatePlayerGold();
                    matrix.get(selectedBuilding.x).get(selectedBuilding.y).getPanel().setComponentPopupMenu(menuPopup);
                    selectedBuilding.deleteBuilding(matrix.get(selectedBuilding.x).get(selectedBuilding.y).getPanel());
                    matrix.get(selectedBuilding.x).get(selectedBuilding.y).setBuilding(null);
                    initGameMap();
                    selectedBuilding = null;
                }
            }
        });

        towerDetailsPopup.add(sellTower);
        towerDetailsPopup.addSeparator();

        JMenuItem getTowerDetails = new JMenuItem(new AbstractAction("Get Building Details", null) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedBuilding != null){
                    JOptionPane.showMessageDialog(null,
                            "Health: " + selectedBuilding.health + "\nType: " + selectedBuilding.type, selectedBuilding.type + " details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        towerDetailsPopup.add(getTowerDetails);

        this.setComponentPopupMenu(menuPopup);

        setLayout(new GridLayout(HEIGHT, WIDTH, 1, 1));
        matrix = new ArrayList<>();
        ArrayList<Ground> temp;

        for (int i = 0; i < HEIGHT; i++) {
            temp = new ArrayList<>();
            for (int j = 0; j < WIDTH; j++) {
                JPanel panel = new JPanel();
                Ground tile = new Ground(null, panel);
                tile.getPanel().addMouseListener( mouseClick );
                tile.getPanel().setComponentPopupMenu(menuPopup);
                temp.add(tile);
                add(tile.getPanel());
            }
            matrix.add(temp);
        }

        setMaximumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));
    }

    MouseListener mouseClick = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                int cordX = 0, cordY = 0;
                JPanel tile = (JPanel) e.getSource();
                for (int i = 0; i < HEIGHT; i++) {
                    for (int j = 0; j < WIDTH; j++) {
                        cordX = tile.getX() / 50;
                        cordY = tile.getY() / 50;
                    }
                }
                Ground selectedGround = matrix.get(cordY).get(cordX);
                if(Window.paused) {
                    if (AirTowerBool) {
                        if (matrix.get(cordY).get(cordX).getBuilding() != null || matrix.get(cordY).get(cordX).getMonster() != null || cordY == 9) {
                            cantBuildMsg();
                        } else if(Player.playerGold < AirTower.priceAirTower) {
                            cantAffordMsg();
                        } else {
                            AirTower airTower = new AirTower(cordY, cordX);
                            selectedGround.setBuilding(airTower);
                            Building.addBuilding();
                            airTower.drawTower(tile);
                            setVisible(true);
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            AirTowerBool = false;
                            Window.updatePlayerGold();
                            Game.points = Game.points + 1;
                            Window.updatePoints(Game.points);
                        }
                    } else if (WaterTowerBool) {
                        if (Player.playerGold < WaterTower.priceWaterTower || matrix.get(cordY).get(cordX).getBuilding() != null || matrix.get(cordY).get(cordX).getMonster() != null || cordY == 9) {
                            cantBuildMsg();
                        } else {
                            WaterTower waterTower = new WaterTower(cordY, cordX);
                            selectedGround.setBuilding(waterTower);
                            Building.addBuilding();
                            waterTower.drawTower(tile);
                            setVisible(true);
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            WaterTowerBool = false;
                            Window.updatePlayerGold();
                            Game.points = Game.points + 1;
                            Window.updatePoints(Game.points);
                        }
                    }
                    else if (FireTowerBool) {
                        if (Player.playerGold < FireTower.priceFireTower || matrix.get(cordY).get(cordX).getBuilding() != null || matrix.get(cordY).get(cordX).getMonster() != null || cordY == 9) {
                            cantBuildMsg();
                        } else {
                            FireTower fireTower = new FireTower(cordY, cordX);
                            selectedGround.setBuilding(fireTower);
                            Building.addBuilding();
                            fireTower.drawTower(tile);
                            setVisible(true);
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            FireTowerBool = false;
                            Window.updatePlayerGold();
                            Game.points = Game.points + 1;
                            Window.updatePoints(Game.points);
                        }
                    }
                    else if (EarthTowerBool) {
                            if (Player.playerGold < EarthTower.priceEarthTower || matrix.get(cordY).get(cordX).getBuilding() != null || matrix.get(cordY).get(cordX).getMonster() != null || cordY == 9) {
                                cantBuildMsg();
                            } else {
                                EarthTower earthTower = new EarthTower(cordY, cordX);
                                selectedGround.setBuilding(earthTower);
                                Building.addBuilding();
                                earthTower.drawTower(tile);
                                setVisible(true);
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                EarthTowerBool = false;
                                Window.updatePlayerGold();
                                Game.points = Game.points + 1;
                                Window.updatePoints(Game.points);
                            }
                        }
                    else if (GoldMineBool) {
                        if (Player.playerGold < Mine.priceMine || matrix.get(cordY).get(cordX).getBuilding() != null || matrix.get(cordY).get(cordX).getMonster() != null || cordY == 9) {
                            cantBuildMsg();
                        } else {
                            Mine goldMine = new Mine(cordY, cordX);
                            selectedGround.setBuilding(goldMine);
                            Building.addBuilding();
                            goldMine.drawMine(tile);
                            setVisible(true);
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            GoldMineBool = false;
                            Window.updatePlayerGold();
                            Game.points = Game.points + 1;
                            Window.updatePoints(Game.points);
                        }
                    }
                }
            } else if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1)
            {
                AirTowerBool = false; WaterTowerBool = false; FireTowerBool = false; EarthTowerBool = false; GoldMineBool = false;
                int cordX = 0, cordY = 0;
                JPanel tile = (JPanel) e.getSource();
                for (int i = 0; i < HEIGHT; i++) {
                    for (int j = 0; j < WIDTH; j++) {
                        cordX = tile.getX() / 50;
                        cordY = tile.getY() / 50;
                    }
                }
                initGameMap();
                Ground selectedGround = matrix.get(cordY).get(cordX);
                if(selectedGround.getBuilding() != null) {
                    selectedBuilding = selectedGround.getBuilding();
                    tile.setComponentPopupMenu(towerDetailsPopup);
                    tile.setBackground(Color.GREEN);
                }

            }
        }

    };

    private void cantBuildMsg() {

        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "You can't build a tower here");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        AirTowerBool = false; WaterTowerBool = false; FireTowerBool = false; EarthTowerBool = false; GoldMineBool = false;
    }

    private void cantAffordMsg() {

        JFrame frame = new JFrame();
        ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/assets/mouseIcon.png"));
        JOptionPane.showMessageDialog(frame, "You are poor as a church mouse.", "Poor Message", JOptionPane.INFORMATION_MESSAGE, imgIcon);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        AirTowerBool = false; WaterTowerBool = false; FireTowerBool = false; EarthTowerBool = false; GoldMineBool = false;
    }

    public static void initGame() {
        newGame = false;
        end = false;
        points = 0;
        initGameMap();
    }

    public static void initNewGame(Difficulty difficulty){
        newGame = false;
        end = false;

        Window.timerLabel.setTimeLabelToZeroZero();
        Window.timerLabel.setTimeToZero();
        Window.timerLabel.stopTimer();

        initNewGameMap();

        points = 0;

        Window.updatePoints(0);

        switch(difficulty) {

            case EASY:
                Player.playerGold = 500;
                Window.updatePlayerGold();
                Window.waitSec(5);
                break;
            case MEDIUM:
                Player.playerGold = 300;
                Window.updatePlayerGold();
                Window.waitSec(3);
                break;
            case HARD:
                Player.playerGold = 100;
                Window.updatePlayerGold();
                Window.waitSec(0);
                break;
        }
    }

    private static void initNewGameMap(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(matrix.get(i).get(j).getBuilding()!= null){
                    matrix.get(i).get(j).getBuilding().deleteBuilding(matrix.get(i).get(j).getPanel());
                    matrix.get(i).get(j).setBuilding(null);
                }
                else if( matrix.get(i).get(j).getMonster() != null){
                    matrix.get(i).get(j).getMonster().deleteMonster(matrix.get(i).get(j).getPanel(), matrix.get(i).get(j).getMonster());
                    matrix.get(i).get(j).getMonster().deleteMonsterGif(matrix.get(i).get(j).getPanel());
                    matrix.get(i).get(j).setMonster(null);
                }
                    matrix.get(i).get(j).getPanel().setBackground(Color.WHITE);
            }
        }
    }

    public static void initGameMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(matrix.get(i).get(j).getMonster() == null ) {
                    matrix.get(i).get(j).getPanel().setBackground(Color.WHITE);
                }
            }
        }
    }

    Tower tower = new Tower();

    public void towerShoot(){
        if ( Window.timerLabel.time % 3 == 0 && Window.timerLabel.time != 0 && Window.paused) {
            try {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++)
                        if (matrix.get(i).get(j).getBuilding() != null && !Objects.equals(matrix.get(i).get(j).getBuilding().type, "Gold Mine")) {
                            tower.shoot(matrix.get(i).get(j).getBuilding());
                        }
                }
                if (Window.timerLabel.time % 15 != 0) {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   public void giveMineGold(){
       if ( Window.timerLabel.time % 15 == 0 && Window.timerLabel.time != 0 && Window.paused) {
           try {
               for (int i = 0; i < 10; i++) {
                   for (int j = 0; j < 10; j++) {
                       if ((matrix.get(i).get(j).getBuilding() != null) && Objects.equals(matrix.get(i).get(j).getBuilding().type, "Gold Mine")) {
                           Mine.giveGold();
                           Window.updatePlayerGold();
                       }
                   }
               }
               if (Window.timerLabel.time % 30 != 0) {
                   Thread.sleep(1000);
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    Monster monster = new Monster();

    public void run() {
        while (true) {
            if(!end) {
                turnFirstKeyPress = true;
                monster.zombieSpawn();
                monster.zombieMove();
                towerShoot();
                giveMineGold();
                if (Window.paused) Window.giveGoldPerSec();
                if (newGame) {
                    initNewGame(difficulty);
                }
            }
        }
    }
}
