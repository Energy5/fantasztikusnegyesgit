package hu.fantasztik.szofttech.building.tower;

import hu.fantasztik.szofttech.building.tower.types.AirTower;
import hu.fantasztik.szofttech.building.tower.types.EarthTower;
import hu.fantasztik.szofttech.building.tower.types.FireTower;
import hu.fantasztik.szofttech.building.tower.types.WaterTower;
import hu.fantasztik.szofttech.enemy.BoMbHead;
import hu.fantasztik.szofttech.state.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TowerTest {

    @Test
    void shootFire() {

        Game game = new Game();
        Tower tower = new Tower();

        BoMbHead b1 = new BoMbHead(6, 5);
        FireTower f1 = new FireTower(5, 5);
        Game.matrix.get(6).get(5).setMonster(b1);
        Game.matrix.get(5).get(5).setBuilding(f1);
        tower.shoot(f1);
        assertEquals(40, b1.health);

        f1 = null;
        b1 = null;
    }

    @Test
    void shootEarth() {

        Game game = new Game();
        Tower tower = new Tower();
        BoMbHead b1 = new BoMbHead(6, 5);
        EarthTower e1 = new EarthTower(5, 5);
        Game.matrix.get(6).get(5).setMonster(b1);
        Game.matrix.get(5).get(5).setBuilding(e1);
        tower.shoot(e1);
        assertEquals(90, b1.health);

        e1 = null;
        b1 = null;
    }

    @Test
    void shootWater() {

        Game game = new Game();
        Tower tower = new Tower();

        BoMbHead b1 = new BoMbHead(6, 5);
        WaterTower w1 = new WaterTower(5, 5);
        Game.matrix.get(6).get(5).setMonster(b1);
        Game.matrix.get(5).get(5).setBuilding(w1);
        tower.shoot(w1);
        assertEquals(30, b1.health);

        w1 = null;
        b1 = null;
    }

    @Test
    void shootAir() {

        Game game = new Game();
        Tower tower = new Tower();

        BoMbHead b1 = new BoMbHead(6, 5);
        AirTower a1 = new AirTower(5, 5);
        Game.matrix.get(6).get(5).setMonster(b1);
        Game.matrix.get(5).get(5).setBuilding(a1);
        tower.shoot(a1);
        assertEquals(60, b1.health);

        a1 = null;
        b1 = null;
    }
}