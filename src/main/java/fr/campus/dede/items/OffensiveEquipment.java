package fr.campus.dede.items;

public abstract class OffensiveEquipment implements Equipments {
    protected String type;

    public OffensiveEquipment (String type) {
        this.type=type;
    }

    //GETTERS
    public String getType() {
        return type;
    }

    //SETTERS
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public abstract void use();

    @Override
    public String toString() {
        return type;
    }
}
