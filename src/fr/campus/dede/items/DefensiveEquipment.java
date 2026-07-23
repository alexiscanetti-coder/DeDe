package fr.campus.dede.items;

public abstract class DefensiveEquipment implements Equipments {
    protected String type;

    public DefensiveEquipment (String type) {
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
}
