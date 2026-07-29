package fr.campus.dede.view;

import fr.campus.dede.MainApp;
import fr.campus.dede.core.Game;
import fr.campus.dede.map.Cell;
import fr.campus.dede.model.Enemies;
import fr.campus.dede.model.Heroes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CombatWindow extends BorderPane {

    private final Game game;
    private final GameWindow gameWindow;
    private final Heroes hero;
    private final Enemies enemy;
    private final Cell cell;

    private Label heroLifeLabel;
    private Label enemyLifeLabel;
    private VBox logBox;
    private ScrollPane logScroll;

    private Button attackButton;
    private Button fleeButton;
    private Button itemButton;

    public CombatWindow(Game game, GameWindow gameWindow) {
        this.game = game;
        this.gameWindow = gameWindow;
        this.hero = game.getHeroes();
        this.enemy = game.getPendingEnemy();
        this.cell = game.getPendingCell();

        setPadding(new Insets(30));
        setStyle("-fx-background-color: black;");

        Label title = new Label("COMBAT : " + enemy.getType());
        title.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 20));
        title.setTextFill(Color.WHITE);

        VBox statsBox = createStatsBox();
        VBox logSection = createLogSection();
        HBox actionBar = createActionBar();

        VBox center = new VBox(20, title, statsBox, logSection, actionBar);
        center.setAlignment(Pos.CENTER);
        center.setMaxWidth(500);

        setCenter(center);

        appendLog("A wild " + enemy.getType() + " blocks your path !");
    }

    private VBox createStatsBox() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));
        box.getStyleClass().add("ff-box");

        heroLifeLabel = createStatLabel();
        enemyLifeLabel = createStatLabel();
        updateStatsLabels();

        box.getChildren().addAll(heroLifeLabel, enemyLifeLabel);
        return box;
    }

    private Label createStatLabel() {
        Label label = new Label();
        label.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 12));
        label.setTextFill(Color.WHITE);
        return label;
    }

    private void updateStatsLabels() {
        heroLifeLabel.setText(hero.getName() + " : " + hero.getLifePoints() + " / " + hero.getMaxLifePoints());
        enemyLifeLabel.setText(enemy.getType() + " : " + Math.max(enemy.getLifePoints(), 0));
    }

    private VBox createLogSection() {
        logBox = new VBox(6);
        logBox.setPadding(new Insets(10));

        logScroll = new ScrollPane(logBox);
        logScroll.setFitToWidth(true);
        logScroll.setPrefHeight(120);
        logScroll.getStyleClass().add("hero-scroll");
        logScroll.setStyle("-fx-background-color: transparent;");

        StackPane wrapper = new StackPane(logScroll);
        wrapper.setPadding(new Insets(5));
        wrapper.getStyleClass().add("log-box");

        return new VBox(wrapper);
    }

    private HBox createActionBar() {
        attackButton = UiFactory.createButton("ATTACK", 14);
        fleeButton = UiFactory.createButton("FLEE", 14);
        itemButton = UiFactory.createButton("ITEM", 14);
        itemButton.setDisable(true);

        attackButton.setOnAction(e -> playerAttack());
        fleeButton.setOnAction(e -> attemptFlee());

        HBox bar = new HBox(20, attackButton, fleeButton, itemButton);
        bar.setAlignment(Pos.CENTER);
        return bar;
    }

    private void playerAttack() {
        int heroDamage = hero.getStrength();
        enemy.setLifePoints(enemy.getLifePoints() - heroDamage);
        appendLog(hero.getName() + " deals " + heroDamage + " damage to " + enemy.getType());

        if (enemy.getLifePoints() <= 0) {
            updateStatsLabels();
            victory();
            return;
        }

        enemyAttack();
    }

    private void enemyAttack() {
        int enemyDamage = enemy.getStrength();
        hero.setLifePoints(hero.getLifePoints() - enemyDamage);
        appendLog(enemy.getType() + " deals " + enemyDamage + " damage to " + hero.getName());
        updateStatsLabels();

        if (hero.getLifePoints() <= 0) {
            defeat();
        }
    }

    private void attemptFlee() {
        appendLog(hero.getName() + " flees from the battle !");
        endCombat("You fled from the " + enemy.getType());
    }

    private void victory() {
        appendLog(enemy.getType() + " is defeated !");
        cell.setContent(null);
        endCombat(enemy.getType() + " was defeated !");
    }

    private void defeat() {
        appendLog(hero.getName() + " has fallen...");
        disableActions();
        game.forceGameOver();
        endCombat(hero.getName() + " was defeated ! GAME OVER");
    }

    private void disableActions() {
        attackButton.setDisable(true);
        fleeButton.setDisable(true);
        itemButton.setDisable(true);
    }

    private void endCombat(String resultMessage) {
        game.clearPendingCombat();
        gameWindow.onCombatResolved(resultMessage);
    }

    private void appendLog(String message) {
        Label entry = new Label(message);
        entry.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", 9));
        entry.setTextFill(Color.WHITE);
        entry.setWrapText(true);

        logBox.getChildren().add(entry);
        logScroll.layout();
        logScroll.setVvalue(1.0);
    }
}