package fr.campus.dede.model.heroes;
import fr.campus.dede.items.equipment.*;
import fr.campus.dede.model.Heroes;

public class Warrior extends Heroes {
    public Warrior(String name, Weapon weapon, Potion potion) {
        super("WARRIOR", name, 10, 5, weapon, potion);
    }
}
