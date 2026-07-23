package fr.campus.dede.map;
import fr.campus.dede.items.*;
import fr.campus.dede.items.equipment.*;
import fr.campus.dede.model.*;
import fr.campus.dede.model.characters.*;

public class Cell {
    private final int position;
    private final int row;
    private final int col;
    private CellContent content;

    public Cell(int position, int row, int col) {
        this.position = position;
        this.row = row;
        this.col = col;
    }

    //GETTERS
    public int getPosition() {
        return position;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellContent getContent() { return content; }

    //SETTERS
    public void setContent(CellContent content) {
        this.content = content;
    }


    public void interact(Characters characters) {
        if(content instanceof Enemies enemy) {
            System.out.println(("An ennemy has appeared : "+enemy.getType()));
            fight(characters, enemy);
        }

        if(content instanceof MysteryBox mysteryBox) {
            System.out.println(("You open a mystery box"));

            Equipments item = mysteryBox.getItem();

            if(item instanceof Potion potion) {
                int currentLife = characters.getLifePoints();
                int maxLife = characters.getMaxLifePoints();

                if(currentLife >= maxLife) {
                    System.out.println("You're full life");
                } else {
                    characters.setLifePoints(currentLife + Math.min(potion.getHeal(), maxLife - currentLife));
                    System.out.println("You find an item : " + potion.getType());
                    System.out.println("You heal " + potion.getHeal() + "HP");
                }
            }

            if(item instanceof Weapon weapon) {
                if(characters instanceof Warrior){
                    characters.setOffensiveEquipment((weapon.getType()));
                    characters.setStrength(characters.getStrength() + weapon.getPhysicalDamage());
                    System.out.println("You find a weapon : "+weapon.getType());
                } else {
                    System.out.println(("A Wizard can't equip this item : "+weapon.getType()));
                }
            }

            if(item instanceof Spell spell) {
                if(characters instanceof Wizard){
                    characters.setOffensiveEquipment((spell.getType()));
                    characters.setStrength(characters.getStrength() + spell.getMagicalDamage());
                    System.out.println("You find a weapon : "+spell.getType());
                } else {
                    System.out.println(("A Warrior can't equip this item : "+spell.getType()));
                }
            }
        }
    }

    private void fight(Characters characters, Enemies enemy) {
        int damageCharacters = characters.getStrength();
        enemy.setLifePoints(enemy.getLifePoints() - damageCharacters);
        System.out.println(characters.getName()+" deal "+damageCharacters+" damages");

        if(enemy.getLifePoints() <= 0) {
            System.out.println(enemy.getType() +" is dead");
            content = null;
        } else {
            int damageEnemy = enemy.getStrength();
            characters.setLifePoints(characters.getLifePoints() - damageEnemy);
            System.out.println(enemy.getType()+" deal "+damageEnemy+" damages");
            System.out.println(enemy.getType()+" is retreating");
        }
    }
}
