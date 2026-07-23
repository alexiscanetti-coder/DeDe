package fr.campus.dede.model.characters;
import fr.campus.dede.model.Characters;

public class Wizard extends Characters {
    public Wizard(String name, String offensiveEquipment, String defensiveEquipment) {
        super("WIZARD", name, 7, 7, offensiveEquipment, defensiveEquipment);
    }
}
