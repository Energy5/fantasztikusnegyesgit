package hu.fantasztik.szofttech.enemy;

import javax.swing.*;

public class TanKy extends Monster {
    public TanKy(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "TanKy";
        health = 400;
        damage = 5;
    }

    public static void drawMonster(final JPanel tile){

        try {
            ImageIcon imgIcon = new ImageIcon(TanKy.class.getResource("/assets/tankyKis.gif"));

            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
