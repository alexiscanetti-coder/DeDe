package fr.campus.dede.items.equipment;
import fr.campus.dede.items.OffensiveEquipment;

public class Weapon extends OffensiveEquipment {
    int physicalDamage;

    public Weapon(String type, int physicalDamage) {
        super(type);
        this.physicalDamage=physicalDamage;
    }

    //GETTERS
    public int getPhysicalDamage() {
        return physicalDamage;
    }

    //SETTERS
    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    @Override
    public void use() {
    }
}
