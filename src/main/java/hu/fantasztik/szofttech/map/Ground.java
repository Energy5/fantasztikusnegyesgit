package hu.fantasztik.szofttech.map;

import hu.fantasztik.szofttech.building.Building;
import hu.fantasztik.szofttech.enemy.Monster;

import javax.swing.*;

public class Ground {

    private Building building;
    private JPanel panel;
    private Monster monster;

    public Ground(Building building, JPanel panel) {
        this.building = building;
        this.panel = panel;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public JPanel getPanel() {
        return panel;
    }

}
