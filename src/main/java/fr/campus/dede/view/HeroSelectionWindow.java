package fr.campus.dede.view;

import fr.campus.dede.MainApp;
import fr.campus.dede.db.*;
import fr.campus.dede.model.Heroes;
import fr.campus.dede.model.heroes.Warrior;
import fr.campus.dede.model.heroes.Wizard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class HeroSelectionWindow extends HBox {

    private final HeroesDAO heroesDAO;
    private Heroes heroes;

    private Heroes selectedHero;
    private Button selectedButton;
    private Button editButtonRef;
    private Button deleteButtonRef;
    private Button playButtonRef;

    private final Label titleLabel;
    private final VBox rightBox;
    private final StackPane rightWrapper;
    private final VBox rightContainer;

    private static final double LEFT_WIDTH = 230, LEFT_HEIGHT = 430;
    private static final double RIGHT_WIDTH = 540, TITLE_HEIGHT = 50, RIGHT_HEIGHT = 370;
    private static final double SPACING = 10;

    public HeroSelectionWindow() {
        setSpacing(SPACING);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: black;");

        VBox leftBox = createLeftBox();

        titleLabel = new Label("");
        titleLabel.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 15));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER_LEFT);

        StackPane titleBox = new StackPane(titleLabel);
        titleBox.setPrefSize(RIGHT_WIDTH, TITLE_HEIGHT);
        titleBox.setMinSize(RIGHT_WIDTH, TITLE_HEIGHT);
        titleBox.setMaxSize(RIGHT_WIDTH, TITLE_HEIGHT);
        titleBox.setPadding(new Insets(0, 0, 0, 20));
        titleBox.getStyleClass().add("ff-box");

        rightBox = new VBox(10);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPadding(new Insets(10));
        rightBox.setPrefSize(RIGHT_WIDTH, RIGHT_HEIGHT);
        rightBox.setMinSize(RIGHT_WIDTH, RIGHT_HEIGHT);
        rightBox.setMaxSize(RIGHT_WIDTH, RIGHT_HEIGHT);
        rightBox.getStyleClass().add("ff-box");

        rightContainer = new VBox(SPACING, titleBox, rightBox);

        Pane emptyPanel = new Pane();
        emptyPanel.setPrefSize(RIGHT_WIDTH, TITLE_HEIGHT + SPACING + RIGHT_HEIGHT);

        rightWrapper = new StackPane(emptyPanel);

        heroesDAO = new HeroesDAO();

        getChildren().addAll(leftBox, rightWrapper);
    }

    private VBox createLeftBox() {
        VBox leftBox = new VBox(10);
        leftBox.setPrefSize(LEFT_WIDTH, LEFT_HEIGHT);
        leftBox.setMinSize(LEFT_WIDTH, LEFT_HEIGHT);
        leftBox.setMaxSize(LEFT_WIDTH, LEFT_HEIGHT);
        leftBox.setPadding(new Insets(20));
        leftBox.getStyleClass().add("ff-box");

        Button newHero = UiFactory.createButton("NEW HERO", 15);
        newHero.setOnAction(e -> createHero());

        Button manageHeroesBtn = UiFactory.createButton("ALL HEROES", 15);
        manageHeroesBtn.setOnAction(e -> manageHeroes());

        Button exit = UiFactory.createButton("EXIT", 15);
        exit.setOnAction(e -> MainApp.showScreen(new MenuWindow()));

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        leftBox.getChildren().addAll(newHero, manageHeroesBtn, spacer, exit);
        return leftBox;
    }

    // -------------------- CREATE HERO -------------------- \\
    private void createHero() {
        chooseClass();
    }

    private void chooseClass() {
        clearRightBox();
        setTitle("CHOOSE A CLASS : ");

        Button warriorButton = UiFactory.createButton("WARRIOR", 15);
        Button wizardButton = UiFactory.createButton("WIZARD", 15);

        warriorButton.setOnAction(e -> {
            heroes = new Warrior("HEROS", null, null);
            chooseName();
        });
        wizardButton.setOnAction(e -> {
            heroes = new Wizard("HEROS", null, null);
            chooseName();
        });

        VBox buttonBox = new VBox(10, warriorButton, wizardButton);
        buttonBox.setAlignment(Pos.CENTER);

        rightBox.getChildren().add(buttonBox);
    }

    private void chooseName() {
        clearRightBox();
        setTitle("WRITE A NAME : ");

        TextField nameField = new TextField();
        nameField.setMaxWidth(300);

        Button confirmButton = UiFactory.createButton("CONFIRM", 15);
        confirmButton.setOnAction(e -> {
            String name = nameField.getText().trim().toUpperCase();
            if (name.isBlank()) name = "HEROS";
            heroes.setName(name);
            heroesDAO.create(heroes);
            MainApp.showScreen(new MenuWindow());
        });

        VBox content = new VBox(10, nameField, confirmButton);
        content.setAlignment(Pos.CENTER);

        rightBox.getChildren().add(content);
    }

    // -------------------- MANAGE HERO -------------------- \\
    private void manageHeroes() {
        clearRightBox();
        setTitle("MANAGE HEROES : ");
        selectedHero = null;
        selectedButton = null;

        List<Heroes> heroes = heroesDAO.fetchAll();

        VBox listBox = new VBox(10);
        listBox.setAlignment(Pos.TOP_LEFT);
        listBox.setPadding(new Insets(10));

        for (Heroes hero : heroes) {
            Button heroButton = UiFactory.createButton(
                    hero.getName() + " (" + hero.getType() + ") : " + hero.getLifePoints() + " HP | " +
                            hero.getStrength() + " STR", 12);
            heroButton.setMaxWidth(Double.MAX_VALUE);
            heroButton.setAlignment(Pos.CENTER_LEFT);
            heroButton.getStyleClass().add("hero-button");

            heroButton.setOnAction(e -> selectHero(hero, heroButton));

            listBox.getChildren().add(heroButton);
        }

        ScrollPane scrollPane = new ScrollPane(listBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(RIGHT_WIDTH - 40, RIGHT_HEIGHT - 100);
        scrollPane.getStyleClass().add("hero-scroll");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Button editButton = UiFactory.createButton("EDIT", 14);
        Button deleteButton = UiFactory.createButton("DELETE", 14);
        Button playButton = UiFactory.createButton("PLAY", 14);
        playButton.setDisable(true);
        editButton.setDisable(true);
        deleteButton.setDisable(true);

        playButton.setOnAction(e -> {
            if(selectedHero != null) {
                MainApp.showScreen(new GameWindow(selectedHero));
            }
        });
        editButton.setOnAction(e -> {
            if (selectedHero != null) editHero(selectedHero);
        });
        deleteButton.setOnAction(e -> {
            if (selectedHero != null) deleteHero(selectedHero);
        });

        this.playButtonRef = playButton;
        this.editButtonRef = editButton;
        this.deleteButtonRef = deleteButton;

        HBox actionBar = new HBox(10, playButton, editButton, deleteButton);
        actionBar.setAlignment(Pos.CENTER);

        rightBox.getChildren().addAll(scrollPane, actionBar);
    }

    private void selectHero(Heroes hero, Button button) {
        if (selectedButton != null) {
            selectedButton.getStyleClass().remove("hero-button-selected");
        }

        selectedHero = hero;
        selectedButton = button;
        button.getStyleClass().add("hero-button-selected");

        playButtonRef.setDisable(false);
        editButtonRef.setDisable(false);
        deleteButtonRef.setDisable(false);
    }

    private void editHero(Heroes hero) {
        clearRightBox();
        setTitle("EDIT " + hero.getName() + " : ");

        TextField nameField = new TextField(hero.getName());
        nameField.setMaxWidth(300);

        Button confirmButton = UiFactory.createButton("SAVE", 15);
        confirmButton.setOnAction(e -> {
            String name = nameField.getText().trim().toUpperCase();
            if (!name.isBlank()) {
                hero.setName(name);
                heroesDAO.update(hero);
            }
            manageHeroes();
        });

        Button cancelButton = UiFactory.createButton("CANCEL", 15);
        cancelButton.setOnAction(e -> manageHeroes());

        VBox content = new VBox(10);
        content.setAlignment(Pos.CENTER);

        HBox actionBar = new HBox(10, confirmButton, cancelButton);
        actionBar.setAlignment(Pos.CENTER);

        content.getChildren().addAll(nameField, actionBar);
        rightBox.getChildren().add(content);
    }

    private void deleteHero(Heroes hero) {
        clearRightBox();
        setTitle("DELETE : ");

        Label confirmLabel = new Label("DELETE " + hero.getName() + " ?");
        confirmLabel.setTextFill(Color.WHITE);
        confirmLabel.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 15));

        Button confirmButton = UiFactory.createButton("YES", 15);
        confirmButton.setOnAction(e -> {
            heroesDAO.delete(hero);
            manageHeroes();
        });

        Button cancelButton = UiFactory.createButton("NO", 15);
        cancelButton.setOnAction(e -> manageHeroes());

        VBox content = new VBox(10);
        content.setAlignment(Pos.CENTER);

        HBox actionBar = new HBox(10, confirmButton, cancelButton);
        actionBar.setAlignment(Pos.CENTER);

        content.getChildren().addAll(confirmLabel, actionBar);
        rightBox.getChildren().add(content);
    }

    // -------------------- UTILS -------------------- \\
    private void setTitle(String text) {
        titleLabel.setText(text);
    }

    private void clearRightBox() {
        rightWrapper.getChildren().setAll(rightContainer);
        rightBox.getChildren().clear();
    }
}