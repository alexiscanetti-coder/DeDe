package fr.campus.dede.model;

import fr.campus.dede.items.DefensiveEquipment;
import fr.campus.dede.items.OffensiveEquipment;
import fr.campus.dede.items.equipment.*;

public class Heroes {
    private int id;
    private String type;
    private String name;
    private int lifePoints;
    private int maxLifePoints;
    private int strength;
    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;

    public Heroes(String type, String name, int lifePoints, int strength, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment) {
        this.type=type;
        this.name=name;
        this.lifePoints=lifePoints;
        this.maxLifePoints=lifePoints;
        this.strength=strength;
        this.offensiveEquipment=offensiveEquipment;
        this.defensiveEquipment=defensiveEquipment;
    }

    //GETTERS
    public int getId() { return id; };

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getMaxLifePoints() {
        return maxLifePoints;
    }

    public int getStrength() {
        return strength;
    }

    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public DefensiveEquipment getDefensiveEquipment() { return defensiveEquipment; }

    //SETTERS
    public void setId(int id) { this.id = id; }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setMaxLifePoints(int maxLifePoints) {
        this.maxLifePoints = maxLifePoints;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    public int getOffensiveDamage() {
        if (offensiveEquipment instanceof Weapon) {
            return ((Weapon) offensiveEquipment).getPhysicalDamage();
        }

        if (offensiveEquipment instanceof Spell) {
            return ((Spell) offensiveEquipment).getMagicalDamage();
        }
        return 0;
    }
}
