package hu.fantasztik.szofttech.building.tower.types;

import hu.fantasztik.szofttech.building.tower.Tower;

import javax.swing.*;

public class WaterTower extends Tower {
    public static int priceWaterTower = 100;
    public static int sellPriceWaterTower = 30;
    public static int healthWaterTower = 100;
    public static int damageWaterTower = 70;
    public static int rangeWaterTower = 5;

    public WaterTower(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "Water Tower";
        price = priceWaterTower;
        sellPrice = sellPriceWaterTower;
        health = healthWaterTower;
        damage = damageWaterTower;
        range = rangeWaterTower;
    }

    public void drawTower(final JPanel tile){

        try {
            ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/assets/WaterTower.gif"));
            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
