package hu.fantasztik.szofttech.building.tower;

import hu.fantasztik.szofttech.building.Building;
import hu.fantasztik.szofttech.building.tower.types.AirTower;
import hu.fantasztik.szofttech.building.tower.types.EarthTower;
import hu.fantasztik.szofttech.building.tower.types.FireTower;
import hu.fantasztik.szofttech.building.tower.types.WaterTower;
import hu.fantasztik.szofttech.state.Game;

import java.awt.*;

public class Tower extends Building {

    public int damage;
    public int range;

    public void shoot(Building building){
        switch (building.type) {
            case "Fire Tower":
                range = FireTower.rangeFireTower;
                damage = FireTower.damageFireTower;
                break;
            case "Water Tower":
                range = WaterTower.rangeWaterTower;
                damage = WaterTower.damageWaterTower;
                break;
            case "Air Tower":
                range = AirTower.rangeAirTower;
                damage = AirTower.damageAirTower;
                break;
            case "Earth Tower":
                range = EarthTower.rangeEarthTower;
                damage = EarthTower.damageEarthTower;
                break;
        }

            for (int i = 1; i <= range; i++){
                if(building.x+i>9) {
                    break;
                }else if (Game.matrix.get(building.x + i).get(building.y).getMonster() != null) {
                    switchTowerShoot(i,building);
                    Game.matrix.get(building.x + i).get(building.y).getMonster().modifyHealth(Game.matrix.get(building.x + i).get(building.y).getMonster().health, damage);

                    if (Game.matrix.get(building.x + i).get(building.y).getMonster().health <= 0) {
                        Game.matrix.get(building.x + i).get(building.y).getMonster().deleteMonster(Game.matrix.get(building.x + i).get(building.y).getPanel(), Game.matrix.get(building.x + i).get(building.y).getMonster());
                        Game.matrix.get(building.x + i).get(building.y).setMonster(null);
                    }

                    break;
                }
            }

    }

    private void switchTowerShoot(int i, Building building) {

        switch (Game.matrix.get(building.x).get(building.y).getBuilding().type) {
            case "Air Tower":
                Game.matrix.get(building.x + i).get(building.y).getPanel().setBackground(Color.CYAN);
                break;
            case "Water Tower":
                Game.matrix.get(building.x + i).get(building.y).getPanel().setBackground(Color.BLUE);
                break;
            case "Fire Tower":
                Game.matrix.get(building.x + i).get(building.y).getPanel().setBackground(Color.RED);
                break;
            case "Earth Tower":
                Game.matrix.get(building.x + i).get(building.y).getPanel().setBackground(Color.DARK_GRAY);
                break;
        }
    }

}