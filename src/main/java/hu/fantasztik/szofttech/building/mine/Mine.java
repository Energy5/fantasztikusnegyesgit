package hu.fantasztik.szofttech.building.mine;

import hu.fantasztik.szofttech.building.Building;
import hu.fantasztik.szofttech.framework.Window;
import hu.fantasztik.szofttech.player.Player;

import javax.swing.*;

public class Mine extends Building {
    public static int priceMine = 100;
    public static int sellPriceMine = 60;
    public static int healthMine = 100;

    public Mine(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "Gold Mine";
        price = priceMine;
        sellPrice = sellPriceMine;
        health = healthMine;
    }
    public static void giveGold (){
        if(Window.paused) {
            Player.gainGold(50);
        }
    }

    public void drawMine(final JPanel tile)
    {
        try {
            ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/assets/goldmine.png"));

            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
