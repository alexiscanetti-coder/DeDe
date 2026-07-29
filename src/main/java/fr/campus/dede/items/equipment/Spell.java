package fr.campus.dede.items.equipment;
import fr.campus.dede.items.OffensiveEquipment;

public class Spell extends OffensiveEquipment {
    int magicalDamage;

    public Spell(String type, int magicalDamage) {
        super(type);
        this.magicalDamage=magicalDamage;
    }

    //GETTERS
    public int getMagicalDamage() {
        return magicalDamage;
    }

    //SETTERS
    public void setMagicalDamage(int magicDamage) {
        this.magicalDamage = magicalDamage;
    }

    @Override
    public void use() {
    }
}
