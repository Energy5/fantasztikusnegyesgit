package hu.fantasztik.szofttech.building.tower.types;

import hu.fantasztik.szofttech.building.tower.Tower;

import javax.swing.*;

public class EarthTower extends Tower {
    public static int priceEarthTower = 150;
    public static int sellPriceEarthTower = 90;
    public static int healthEarthTower = 500;
    public static int damageEarthTower = 10;
    public static int rangeEarthTower = 1;

    public EarthTower(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "Earth Tower";
        price = priceEarthTower;
        sellPrice = sellPriceEarthTower;
        health = healthEarthTower;
        damage = damageEarthTower;
        range = rangeEarthTower;
    }

    public void drawTower(final JPanel tile) {
        try {
            final ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/assets/walnutKis.png"));

            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}