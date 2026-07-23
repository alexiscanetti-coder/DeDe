/*package fr.campus.dede.view;
import java.util.*;
import fr.campus.dede.db.Database;
import fr.campus.dede.core.Game;
import fr.campus.dede.model.*;
import fr.campus.dede.model.characters.*;

public class Menu {
    private final Scanner scanner;

    private String choiceMenu;
    private Characters characters;
    private final Database database;

    public Menu() {
        scanner = new Scanner(System.in);
        choiceMenu = "";
        database = new Database();
    }

    public void startMenu() {
        createCharacter();

        do {
            displayMenu();
            choiceMenu = scanner.nextLine().trim();

            switch(choiceMenu) {
                case "1":
                    Game game = new Game(characters);
                    game.startGame();
                    break;
                case "2":
                    displayCharacter();
                    break;
                case "3":
                    editCharacter();
                    database.editHero(characters);
                    break;
                case "4":
                    database.getHeroes();
                    break;
                case "0":
                    System.out.println("=========================================");
                    System.out.println(">>>>>>>>>>>>>>>>>> Exit <<<<<<<<<<<<<<<<<");
                    System.out.println("=========================================");
                    break;
                default:
                    System.out.println("Invalid choice !");
                    break;
            }
        }while(!choiceMenu.equals("0"));
    }

    private String chooseCharacterType() {
        String choice;

        do {
            System.out.println("=========================================");
            System.out.println("Choose a type (1 : WIZARD, 2 : WARRIOR): ");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    return "1";
                case "2":
                    return "2";
                default:
                    System.out.println("Invalid choice !");
            }
        } while (true);
    }

    private void createCharacter() {
        String type = chooseCharacterType();

        System.out.println("=========================================");
        System.out.println("Choose a name : ");
        String name = scanner.nextLine().trim().toUpperCase();

        switch(type) {
            case "1":
                characters = new Wizard(name," ", " ");
                break;
            case "2":
                characters = new Warrior(name," ", " ");
                break;
        }

        database.createHero(characters);

    }

    private void editCharacter() {
        database.getHeroes();
        System.out.println("=========================================");
        System.out.println("Choose a character id : ");
        String heroId = scanner.nextLine().trim();
        characters.setId(Integer.parseInt(heroId));
        database.editHero(characters);
    }

    private void displayCharacter() {
        System.out.println(characters);
    }

    private void displayMenu() {
        System.out.println("=========================================");
        System.out.println("||                MENU                 ||");
        System.out.println("=========================================");
        System.out.println("||          1 = Start Game             ||");
        System.out.println("||        2 = Character sheet          ||");
        System.out.println("||        3 = Change character         ||");
        System.out.println("||    4 = Display all characters       ||");
        System.out.println("||             0 = Exit                ||");
        System.out.println("=========================================");
    }
}
*/