package fr.campus.dede.model;

public class Characters {
    private int id;
    private String type;
    private String name;
    private int lifePoints;
    private int maxLifePoints;
    private int strength;
    private String offensiveEquipment;
    private String defensiveEquipment;

    public Characters(String type, String name, int lifePoints, int strength, String offensiveEquipment, String defensiveEquipment) {
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

    public String getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public String getDefensiveEquipment() { return defensiveEquipment; }

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

    public void setOffensiveEquipment(String offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    public void setDefensiveEquipment(String defensiveEquipment) { this.defensiveEquipment = defensiveEquipment; }

    @Override
    public String toString() {
        return String.format(
                "     ------------------------------%n" +
                "     |      CHARACTER SHEET       |%n" +
                "     ------------------------------%n" +
                "     | %-10s : %-13s |%n" +
                "     | %-10s : %-13s |%n" +
                "     | %-10s : %-13d |%n" +
                "     | %-10s : %-13d |%n" +
                "     | %-10s : %-13s |%n" +
                "     | %-10s : %-13s |%n" +
                "     ------------------------------",
                "Class", type,
                "Name", name,
                "Life", maxLifePoints,
                "Strength", strength,
                "Equipment", offensiveEquipment,
                "", defensiveEquipment
        );
    }
}
