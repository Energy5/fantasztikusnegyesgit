package hu.fantasztik.szofttech.enemy;

import javax.swing.*;

public class PostmAn extends Monster {
    public PostmAn(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = "PostmAn";
        health = 200;
        damage = 20;
    }

    public static void drawMonster(final JPanel tile){

        try {
            ImageIcon imgIcon = new ImageIcon(BoMbHead.class.getResource("/assets/postmanKis.gif"));

            JLabel picLabel = new JLabel(imgIcon);
            picLabel.setIcon(imgIcon);
            tile.add(picLabel, java.awt.BorderLayout.CENTER);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
