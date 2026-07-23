package fr.campus.dede.items.equipment.utility;
import fr.campus.dede.items.equipment.Spell;

public class Fireball extends Spell {
    public Fireball() {
        super("FIREBALL",7);
    }

    @Override
    public String toString() {
        return "Fireball{" +
                "type='" + type + '\'' +
                '}';
    }
}
