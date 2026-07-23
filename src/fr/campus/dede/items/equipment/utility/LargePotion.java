package fr.campus.dede.items.equipment.utility;
import fr.campus.dede.items.equipment.Potion;

public class LargePotion extends Potion {
    public LargePotion() {
        super("LARGE POTION", 5);
    }

    @Override
    public String toString() {
        return "LargePotion{" +
                "type='" + type + '\'' +
                '}';
    }
}
