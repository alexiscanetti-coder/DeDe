package fr.campus.dede.items.equipment;
import fr.campus.dede.items.DefensiveEquipment;

public abstract class Potion extends DefensiveEquipment {
    private int heal;

    public Potion(String type, int heal) {
        super(type);
        this.heal=heal;
    }
    //GETTERS
    public int getHeal() {
        return heal;
    }

    //SETTERS
    public void setHeal(int heal) {
        this.heal = heal;
    }

    @Override
    public void use() {

    }
}
