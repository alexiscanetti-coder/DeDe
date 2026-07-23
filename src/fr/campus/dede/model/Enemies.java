package fr.campus.dede.model;
import fr.campus.dede.map.CellContent;

public class Enemies implements CellContent {
    private int id;
    private String type;
    private int lifePoints;
    private int strength;

    public Enemies(String type, int lifePoints, int strength) {
        this.type=type;
        this.lifePoints=lifePoints;
        this.strength=strength;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getStrength() {
        return strength;
    }

    //GETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }


}
