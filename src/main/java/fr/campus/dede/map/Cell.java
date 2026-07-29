package fr.campus.dede.map;
import fr.campus.dede.items.*;
import fr.campus.dede.items.equipment.*;
import fr.campus.dede.model.*;
import fr.campus.dede.model.heroes.*;

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

    public String interact(Heroes heroes) {
        if (content == null) {
            return "Nothing happens here";
        }
        return content.interact(heroes, this);
    }
}
