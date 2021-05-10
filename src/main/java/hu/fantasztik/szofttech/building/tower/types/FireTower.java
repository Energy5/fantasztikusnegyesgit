package hu.fantasztik.szofttech.building.tower.types;

import hu.fantasztik.szofttech.building.tower.Tower;

import javax.swing.*;

public class FireTower extends Tower {
    public static int priceFireTower = 150;
    public static int sellPriceFireTower = 90;
    public static int healthFireTower = 150;
    public static int damageFireTower = 60;
    public static int rangeFireTower = 2;

    public FireTower(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "Fire Tower";
        price = priceFireTower;
        sellPrice = sellPriceFireTower;
        health = healthFireTower;
        damage = damageFireTower;
        range = rangeFireTower;
    }

    public void drawTower(final JPanel tile) {
        try {
            final ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/assets/FireTower.png"));
            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}