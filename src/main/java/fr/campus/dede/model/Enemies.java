package fr.campus.dede.model;
import fr.campus.dede.map.*;

public class Enemies implements CellContent {
    private int id;
    private String type;
    private int lifePoints;
    private int strength;

    public Enemies(String type, int lifePoints, int strength) {
        this.type=type;
        this.lifePoints=lifePoints;
        this.strength=strength;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getStrength() {
        return strength;
    }

    //GETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String interact(Heroes hero, Cell cell) {
        return fight(hero, cell);
    }

    protected String fight(Heroes hero, Cell cell) {
        StringBuilder log = new StringBuilder();

        int damageHero = hero.getStrength();
        setLifePoints(getLifePoints() - damageHero);
        log.append(hero.getName()).append(" deal ").append(damageHero).append(" damages\n");

        if (getLifePoints() <= 0) {
            log.append(getType()).append(" is dead");
            cell.setContent(null);
        } else {
            int damageEnemy = getStrength();
            hero.setLifePoints(hero.getLifePoints() - damageEnemy);
            log.append(getType()).append(" deal ").append(damageEnemy).append(" damages\n");
            log.append(getType()).append(" is retreating");
        }

        return log.toString();
    }

}
