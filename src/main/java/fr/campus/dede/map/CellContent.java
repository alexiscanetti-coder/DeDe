package fr.campus.dede.map;
import fr.campus.dede.model.Heroes;

public interface CellContent {
    String interact(Heroes hero, Cell cell);
}
