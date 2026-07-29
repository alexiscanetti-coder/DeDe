package fr.campus.dede.view;

import fr.campus.dede.MainApp;
import fr.campus.dede.core.Game;
import fr.campus.dede.model.Heroes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameWindow extends BorderPane {

    private final Game game;
    private final BoardView boardView;
    private Button rollDiceButton;

    private Label nameLabel;
    private Label lifeLabel;
    private Label strengthLabel;
    private Label offEquipLabel;
    private Label defEquipLabel;

    private VBox logBox;
    private ScrollPane logScroll;

    public GameWindow(Heroes hero) {
        this.game = new Game(hero);

        setPadding(new Insets(20));
        setStyle("-fx-background-color: black;");

        boardView = new BoardView(game);

        VBox sidePanel = createSidePanel();

        rollDiceButton = UiFactory.createButton("ROLL DICE", 15);
        rollDiceButton.setOnAction(e -> playTurn());

        Button backButton = UiFactory.createButton("BACK TO MENU", 15);
        backButton.setOnAction(e -> MainApp.showScreen(new MenuWindow()));

        HBox controlBar = new HBox(20, rollDiceButton, backButton);
        controlBar.setAlignment(Pos.CENTER);
        controlBar.setPadding(new Insets(20, 0, 0, 0));

        setLeft(boardView);
        setRight(sidePanel);
        setBottom(controlBar);
        BorderPane.setAlignment(boardView, Pos.CENTER);
        BorderPane.setMargin(sidePanel, new Insets(0, 0, 0, 20));

        refreshStats();
    }

    private VBox createSidePanel() {
        Region spacing = new Region();
        spacing.setPrefHeight(20);

        VBox panel = new VBox(10);
        panel.setPrefWidth(250);
        panel.setPadding(new Insets(20));
        panel.getStyleClass().add("ff-box");

        Label title = new Label("HERO SHEET");
        title.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 12));
        title.setTextFill(Color.WHITE);

        nameLabel = createStatLabel();
        lifeLabel = createStatLabel();
        strengthLabel = createStatLabel();
        offEquipLabel = createStatLabel();
        defEquipLabel = createStatLabel();


        Label logTitle = new Label("LOG");
        logTitle.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 12));
        logTitle.setTextFill(Color.WHITE);

        logBox = new VBox(6);
        logBox.setPadding(new Insets(5));

        logScroll = new ScrollPane(logBox);
        logScroll.setFitToWidth(true);
        logScroll.setPrefHeight(220);
        logScroll.getStyleClass().add("hero-scroll");
        VBox.setVgrow(logScroll, Priority.ALWAYS);

        panel.getChildren().addAll(
                title, nameLabel, lifeLabel, strengthLabel, offEquipLabel, defEquipLabel, spacing,
                logTitle, logScroll
        );

        return panel;
    }

    private Label createStatLabel() {
        Label label = new Label();
        label.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 10));
        label.setTextFill(Color.WHITE);
        label.setWrapText(true);
        return label;
    }

    private void playTurn() {
        String result = game.playTurn();
        boardView.refresh();
        refreshStats();
        appendLog(result);

        if(game.hasPendingCombat()) {
            MainApp.showScreen(new CombatWindow(game, this));
            return;
        }

        if (game.isFinished()) {
            rollDiceButton.setDisable(true);
            String endMessage = game.getHeroes().getLifePoints() <= 0
                    ? game.getHeroes().getName() + " is dead ! GAME OVER"
                    : "Congratulation ! You win !";
            appendLog("=== " + endMessage + " ===");
        }
    }

    public void onCombatResolved(String message) {
        boardView.refresh();
        refreshStats();
        appendLog(message);

        if (game.isFinished()) {
            rollDiceButton.setDisable(true);
            String endMessage = game.getHeroes().getLifePoints() <= 0
                    ? game.getHeroes().getName() + " is dead ! GAME OVER"
                    : "Congratulation ! You win !";
            appendLog("=== " + endMessage + " ===");
        }

        MainApp.showScreen(this);
    }

    private void refreshStats() {
        Heroes hero = game.getHeroes();

        nameLabel.setText("Name : " + hero.getName());
        lifeLabel.setText("Life : " + hero.getLifePoints() + " / " + hero.getMaxLifePoints());
        strengthLabel.setText("Strength : " + hero.getStrength() + " (+" + hero.getOffensiveDamage() + ")");

        if (hero.getOffensiveEquipment() != null) {
            offEquipLabel.setText("Weapon : " + hero.getOffensiveEquipment().getType());
        } else {
            offEquipLabel.setText("Weapon : None");
        }

        if (hero.getDefensiveEquipment() != null) {
            defEquipLabel.setText("Armor : " + hero.getDefensiveEquipment().getType());
        } else {
            defEquipLabel.setText("Armor : None");
        }
    }

    private void appendLog(String message) {
        logBox.getChildren().clear();

        Label entry = new Label(message);
        entry.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 9));
        entry.setTextFill(Color.WHITE);
        entry.setWrapText(true);
        entry.setLineSpacing(6);

        logBox.getChildren().add(entry);
    }
}