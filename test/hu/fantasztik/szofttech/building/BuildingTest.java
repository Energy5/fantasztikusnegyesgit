package hu.fantasztik.szofttech.building;

import hu.fantasztik.szofttech.building.mine.Mine;
import hu.fantasztik.szofttech.building.tower.types.AirTower;
import hu.fantasztik.szofttech.building.tower.types.EarthTower;
import hu.fantasztik.szofttech.building.tower.types.FireTower;
import hu.fantasztik.szofttech.building.tower.types.WaterTower;
import hu.fantasztik.szofttech.player.Player;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static hu.fantasztik.szofttech.state.Difficulty.EASY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildingTest {

    @Test
    void sellBuildingFireTower() {

        new Player(EASY);

        FireTower f1 = new FireTower(5,5);
        f1.sellBuilding();
        assertEquals(590, Player.playerGold);
    }

    @Test
    void sellBuildingWaterTower() {

        new Player(EASY);
        WaterTower w1 = new WaterTower(6,6);
        w1.sellBuilding();
        assertEquals(530, Player.playerGold);
    }

    @Test
    void sellBuildingAirTower() {

        new Player(EASY);

        AirTower a1 = new AirTower(7,7);
        a1.sellBuilding();
        assertEquals(560, Player.playerGold);

    }

    @Test
    void sellBuildingEarthTower() {

        new Player(EASY);

        EarthTower e1 = new EarthTower(8,8);
        e1.sellBuilding();
        assertEquals(590, Player.playerGold);
    }

    @Test
    void sellBuildingMine() {

        new Player(EASY);

        Mine m1 = new Mine(1,1);
        m1.sellBuilding();
        assertEquals(560, Player.playerGold);
    }

    @Test
    void addBuildingFireTower() {

        new Player(EASY);

        new FireTower(5,5);
        Building.addBuilding();
        assertEquals(350, Player.playerGold);
    }

    @Test
    void addBuildingWaterTower() {

        new Player(EASY);

        new WaterTower(6,6);
        Building.addBuilding();
        assertEquals(400, Player.playerGold);
    }

    @Test
    void addBuildingEarthTower() {

        new Player(EASY);

        new EarthTower(8,8);
        Building.addBuilding();
        assertEquals(350, Player.playerGold);
    }

    @Test
    void addBuildingAirTower() {

        new Player(EASY);

        new AirTower(7,7);
        Building.addBuilding();
        assertEquals(400, Player.playerGold);
    }

    @Test
    void addBuildingMine() {

        new Player(EASY);

        new Mine(1,1);
        Building.addBuilding();
        assertEquals(400, Player.playerGold);
    }

    @Test
    void deleteBuildingFireTower() {
        JPanel panel = new JPanel();

        FireTower f1 = new FireTower(5, 5);
        f1.deleteBuilding(panel);
        assertEquals(Color.WHITE,panel.getBackground());
    }

    @Test
    void deleteBuildingWaterTower() {
        JPanel panel = new JPanel();

        WaterTower w1 = new WaterTower(6, 6);
        w1.deleteBuilding(panel);
        assertEquals(Color.WHITE,panel.getBackground());
    }

    @Test
    void deleteBuildingEarthTower() {
        JPanel panel = new JPanel();

        EarthTower e1 = new EarthTower(7, 7);
        e1.deleteBuilding(panel);
        assertEquals(Color.WHITE,panel.getBackground());
    }

    @Test
    void deleteBuildingAirTower() {
        JPanel panel = new JPanel();

        AirTower a1 = new AirTower(8, 8);
        a1.deleteBuilding(panel);
        assertEquals(Color.WHITE,panel.getBackground());
    }

    @Test
    void deleteBuildingMine() {
        JPanel panel = new JPanel();

        Mine m1 = new Mine(5, 5);
        m1.deleteBuilding(panel);
        assertEquals(Color.WHITE,panel.getBackground());
    }

    @Test
    void modifyHealthFireTower() {

        FireTower f1 = new FireTower(5, 5);
        f1.modifyHealth(12, 1);
        assertEquals(11,f1.health);
    }

    @Test
    void modifyHealthWaterTower() {

        WaterTower w1 = new WaterTower(6, 6);
        w1.modifyHealth(12, 1);
        assertEquals(11,w1.health);
    }

    @Test
    void modifyHealthEarthTower() {

        EarthTower e1 = new EarthTower(7, 7);
        e1.modifyHealth(12, 1);
        assertEquals(11,e1.health);
    }

    @Test
    void modifyHealthAirTower() {

        AirTower a1 = new AirTower(8, 8);
        a1.modifyHealth(12, 1);
        assertEquals(11,a1.health);
    }

    @Test
    void modifyHealthMine() {

       Mine m1 = new Mine(5, 5);
        m1.modifyHealth(12, 1);
        assertEquals(11,m1.health);
    }

    @Test
    void testToStringFireTower() {
        FireTower f1 = new FireTower(5, 5);
        assertEquals("Fire Tower",f1.type);
    }

    @Test
    void testToStringWaterTower() {

        WaterTower w1 = new WaterTower(6, 6);
        assertEquals("Water Tower",w1.type);
    }

    @Test
    void testToStringEarthTower() {

        EarthTower e1 = new EarthTower(7, 7);
        assertEquals("Earth Tower",e1.type);
    }

    @Test
    void testToStringAirTower() {

        AirTower a1 = new AirTower(8, 8);
        assertEquals("Air Tower",a1.type);
    }

    @Test
    void testToStringMine() {
        Mine m1 = new Mine(5, 5);
        assertEquals("Gold Mine",m1.type);
    }
}