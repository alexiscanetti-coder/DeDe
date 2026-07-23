package fr.campus.dede.items;
import fr.campus.dede.map.CellContent;

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

}