package hu.fantasztik.szofttech.enemy;

import javax.swing.*;

public class BoMbHead extends Monster {
    public BoMbHead(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "BoMbHead";
        health = 100;
        damage = 50;
    }

    public static void drawMonster(final JPanel tile){

        try {
            ImageIcon imgIcon = new ImageIcon(BoMbHead.class.getResource("/assets/bombheadKis.gif"));
            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
