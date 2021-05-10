package hu.fantasztik.szofttech.enemy;

import hu.fantasztik.szofttech.framework.Window;
import hu.fantasztik.szofttech.state.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Monster {
    String type;
    public int health;
    int damage;
    int x;
    int y;
    private final Random random1 = new Random();
    private final Random random2 = new Random();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void modifyHealth(int health, int towerDamage){
        this.health = health - towerDamage;
    }

    public void deleteMonster(final JPanel panel, Monster monster) {
        switch (monster.type) {
            case "TanKy":
                Game.points += 10;
                break;
            case "PostmAn":
                Game.points += 7;
                break;
            case "BoMbHead":
                Game.points += 5;
                break;
        }

        Window.updatePoints(Game.points);
        panel.setBackground(Color.WHITE);
        panel.removeAll();
    }

    public void zombieSpawn(){
        if(Window.paused) {
            if ( Window.timerLabel.time % 4 == 0 && Window.timerLabel.time != 0) {
                try {
                    int index = random2.nextInt(3);
                    randomZombieSpawn(index);
                    if(Window.timerLabel.time % 15 != 0 ){
                        Thread.sleep(1100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void randomZombieSpawn(int random2){
        switch(random2){
            case 0:
                BoMbHead bombhead = new BoMbHead(9, random1.nextInt(10));
                Game.matrix.get(bombhead.x).get(bombhead.y).setMonster(bombhead);
                BoMbHead.drawMonster( Game.matrix.get(bombhead.x).get(bombhead.y).getPanel() );
                break;

            case 1:
                PostmAn postman = new PostmAn(9, random1.nextInt(10));
                Game.matrix.get(postman.x).get(postman.y).setMonster(postman);
                PostmAn.drawMonster( Game.matrix.get(postman.x).get(postman.y).getPanel() );
                break;

            case 2:
                TanKy tanky = new TanKy(9, random1.nextInt(10));
                Game.matrix.get(tanky.x).get(tanky.y).setMonster(tanky);
                TanKy.drawMonster( Game.matrix.get(tanky.x).get(tanky.y).getPanel() );
                break;

            default:
                break;
        }
    }

    public void zombieMove(){
        if ( Window.timerLabel.time != 0 && Window.paused) {
            try {
                for (int i = 1; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(Game.matrix.get(i).get(j).getMonster() != null && !Game.end) {
                            if (Game.matrix.get(i - 1).get(j).getMonster() == null && Game.matrix.get(i - 1).get(j).getBuilding() == null) {
                                zombieChoose(i,j);
                                Game.matrix.get(i).get(j).setMonster(null);
                                Game.initGameMap();
                            } else if (Game.matrix.get(i - 1).get(j).getBuilding() != null) {
                                Game.matrix.get(i).get(j).getPanel().setBackground(Color.WHITE);
                                Game.matrix.get(i-1).get(j).getBuilding().modifyHealth(Game.matrix.get(i - 1).get(j).getBuilding().health, Game.matrix.get(i).get(j).getMonster().damage);
                                if(Game.matrix.get(i-1).get(j).getBuilding().health <= 0) {
                                    Game.matrix.get(i-1).get(j).getBuilding().deleteBuilding(Game.matrix.get(i-1).get(j).getPanel());
                                    Game.matrix.get(i-1).get(j).setBuilding(null);
                                }
                            }
                            if(i == 1 && Game.matrix.get(i - 1).get(j).getBuilding() == null || Window.timerLabel.time > 178){

                                Window.gameEnd();
                            }
                        }
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

    public void deleteMonsterGif(final JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    private void zombieChoose(int i, int j){
        String monsterType = Game.matrix.get(i).get(j).getMonster().type;
        switch(monsterType){
            case "TanKy":
                TanKy.drawMonster( Game.matrix.get(i - 1).get(j).getPanel() );
                deleteMonsterGif( Game.matrix.get(i).get(j).getPanel() );
                Game.matrix.get(i - 1).get(j).setMonster(Game.matrix.get(i).get(j).getMonster());
                break;

            case "BoMbHead":
                BoMbHead.drawMonster( Game.matrix.get(i - 1).get(j).getPanel() );
                deleteMonsterGif( Game.matrix.get(i).get(j).getPanel() );
                Game.matrix.get(i - 1).get(j).setMonster(Game.matrix.get(i).get(j).getMonster());
                break;

            case "PostmAn":
                PostmAn.drawMonster( Game.matrix.get(i - 1).get(j).getPanel() );
                deleteMonsterGif( Game.matrix.get(i).get(j).getPanel() );
                Game.matrix.get(i - 1).get(j).setMonster(Game.matrix.get(i).get(j).getMonster());
                break;

            default:
                break;
        }
    }
}

