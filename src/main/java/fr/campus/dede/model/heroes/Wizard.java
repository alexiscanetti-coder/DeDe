package fr.campus.dede.model.heroes;
import fr.campus.dede.items.equipment.*;
import fr.campus.dede.model.Heroes;

public class Wizard extends Heroes {
    public Wizard(String name, Spell spell, Potion potion) {
        super("WIZARD", name, 7, 7, spell, potion);
    }
}
