package hu.fantasztik.szofttech.enemy;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonsterTest {

    @Test
    void getXBombhead() {

        BoMbHead b1 = new BoMbHead(1, 1);
        assertEquals(1,b1.getX());
    }

    @Test
    void getXTanky() {

        TanKy t1 = new TanKy(2, 2);
        assertEquals(2,t1.getX());
    }

    @Test
    void getXPostman() {

        PostmAn p1 = new PostmAn(3, 3);
        assertEquals(3,p1.getX());
    }

    @Test
    void setXBombhead() {

        BoMbHead b1 = new BoMbHead(1, 10);
        b1.setX(9);
        assertEquals(9,b1.getX());
    }

    @Test
    void setXTanky() {

        TanKy t1 = new TanKy(3, 10);
        t1.setX(9);
        assertEquals(9,t1.getX());
    }

    @Test
    void setXPostman() {

        PostmAn p1 = new PostmAn(10, 9);
        p1.setX(9);
        assertEquals(9,p1.getX());
    }

    @Test
    void getYBombhead() {

        BoMbHead b1 = new BoMbHead(1, 1);
        assertEquals(1,b1.getY());
    }

    @Test
    void getYTanky() {

        TanKy t1 = new TanKy(2, 2);
        assertEquals(2,t1.getY());
    }

    @Test
    void getYPostman() {

        PostmAn p1 = new PostmAn(3, 3);
        assertEquals(3,p1.getY());
    }

    @Test
    void setYBombhead() {

        BoMbHead b1 = new BoMbHead(1, 10);
        b1.setY(9);
        assertEquals(9,b1.getY());
    }

    @Test
    void setYTanky() {

        TanKy t1 = new TanKy(3, 10);
        t1.setY(9);
        assertEquals(9,t1.getY());

    }

    @Test
    void setYPostman() {

        PostmAn p1 = new PostmAn(10, 1);
        p1.setY(9);
        assertEquals(9,p1.getY());
    }

    @Test
    void modifyHealthBombhead() {

        BoMbHead b1 = new BoMbHead(1, 1);
        b1.modifyHealth(100, 1);
        assertEquals(99,b1.health);

    }

    @Test
    void modifyHealthTanky() {

        TanKy t1 = new TanKy(10, 10);
        t1.modifyHealth(80, 1);
        assertEquals(79,t1.health);

    }

    @Test
    void modifyHealthPostman() {

        PostmAn p1 = new PostmAn(10, 10);
        p1.modifyHealth(60, 1);
        assertEquals(59,p1.health);

    }

    @Test
    void deleteMonsterBomb() {

        JPanel panel = new JPanel();

        BoMbHead b1 = new BoMbHead(1, 1);
        b1.deleteMonster(panel,b1);
        assertEquals(Color.WHITE,panel.getBackground());

    }
    @Test
    void deleteMonsterTanky() {

        JPanel panel = new JPanel();

        TanKy t1 = new TanKy(2, 2);
        t1.deleteMonster(panel,t1);
        assertEquals(Color.WHITE,panel.getBackground());

    }
    @Test
    void deleteMonsterPostman() {

        JPanel panel = new JPanel();

        PostmAn p1 = new PostmAn(3, 3);
        p1.deleteMonster(panel,p1);
        assertEquals(Color.WHITE,panel.getBackground());
    }



    @Test
    void zombieSpawn() {
        //random az egyik koordináltája vannak
    }

    @Test
    void zombieMove() {
       //kód miatt nincs megírva
    }
}