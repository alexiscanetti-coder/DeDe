package fr.campus.dede.items.equipment.utility;
import fr.campus.dede.items.equipment.Potion;

public class StandardPotion extends Potion {
    public StandardPotion() {
        super("LITTLE POTION", 2);
    }

    @Override
    public String toString() {
        return "StandardPotion{" +
                "type='" + type + '\'' +
                '}';
    }
}
