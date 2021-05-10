package hu.fantasztik.szofttech.building.tower.types;

import hu.fantasztik.szofttech.building.tower.Tower;

import javax.swing.*;

public class AirTower extends Tower {
    public static int priceAirTower = 100;
    public static int sellPriceAirTower = 60;
    public static int healthAirTower = 100;
    public static int damageAirTower = 40;
    public static int rangeAirTower = 3;

    public AirTower(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "Air Tower";
        price = priceAirTower;
        sellPrice = sellPriceAirTower;
        health = healthAirTower;
        damage = damageAirTower;
        range = rangeAirTower;
    }

    public void drawTower(final JPanel tile) {
        try {

            final ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/assets/AirTower.gif"));

            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}