package fr.campus.dede;
import fr.campus.dede.view.*;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TitleScreen());

        //Menu menu = new Menu();
        //menu.startMenu();
    }
}
