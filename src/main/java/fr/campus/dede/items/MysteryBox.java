package fr.campus.dede.items;
import fr.campus.dede.items.equipment.*;
import fr.campus.dede.map.*;
import fr.campus.dede.model.Heroes;
import fr.campus.dede.model.heroes.*;

public class MysteryBox implements CellContent {
    private Equipments item;

    public MysteryBox(Equipments item) {
        this.item = item;
    }

    //GETTERS
    public Equipments getItem() {
        return item;
    }

    //SETTERS
    public void setItem(Equipments item) {
        this.item = item;
    }

    @Override
    public String interact(Heroes heroes, Cell cell) {
        StringBuilder log = new StringBuilder();

        log.append("You open a mystery box\n");

        if(item instanceof Potion potion) {
            int currentLife = heroes.getLifePoints();
            int maxLife = heroes.getMaxLifePoints();
            log.append("You find a ").append(potion.getType()).append("\n");
            if(currentLife >= maxLife) {
                log.append("You're full life\n");
            } else {
                int healedAmount = Math.min(potion.getHeal(), maxLife - currentLife);
                heroes.setLifePoints(currentLife + healedAmount);

                log.append("You heal ").append(healedAmount).append(" HP\n");
            }
        }

        if(item instanceof Weapon weapon) {
            if(heroes instanceof Warrior) {
                heroes.setOffensiveEquipment(weapon);
                heroes.setStrength(heroes.getStrength() + weapon.getPhysicalDamage());

                log.append("You find a ").append(weapon.getType()).append("\n");
            } else {
                log.append("A Wizard can't equip this item : ").append(weapon.getType()).append("\n");
            }
        }

        if(item instanceof Spell spell) {
            if(heroes instanceof Wizard) {
                heroes.setOffensiveEquipment(spell);
                heroes.setStrength(heroes.getStrength() + spell.getMagicalDamage());

                log.append("You find a ").append(spell.getType()).append("\n");
            } else {
                log.append("A Warrior can't equip this item : ").append(spell.getType()).append("\n");
            }
        }
        cell.setContent(null);

        return log.toString();
    }
}