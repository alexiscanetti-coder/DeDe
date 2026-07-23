package fr.campus.dede.model.characters;
import fr.campus.dede.model.Characters;

public class Warrior extends Characters {
    public Warrior(String name, String offensiveEquipment, String defensiveEquipment) {
        super("WARRIOR", name, 10, 5, offensiveEquipment, defensiveEquipment);
    }
}
