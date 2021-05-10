package hu.fantasztik.szofttech.building;

import hu.fantasztik.szofttech.player.Player;

import javax.swing.*;
import java.awt.*;

public class Building {
    public int health;
    public String type;
    public static int price;
    public int x;
    public int y;
    protected static int sellPrice;

    public void sellBuilding(){
        Player.playerGold += sellPrice;
    }

    public static void addBuilding(){
            Player.playerGold -= price;
    }

    public void deleteBuilding(final JPanel panel) {
        panel.setBackground(Color.BLUE);
        panel.setBackground(Color.WHITE);
        panel.removeAll();
    }

    public void modifyHealth(int health, int monsterDamage){
        this.health = health - monsterDamage;
    }

    @Override
    public String toString() {
        return type;
    }

}


