package fr.campus.dede.view;

import fr.campus.dede.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuWindow extends StackPane {

    public MenuWindow() {
        setStyle("-fx-background-color: black;");

        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 40, 20, 40));
        box.setPrefSize(600, 350);
        box.setMaxSize(600, 350);
        box.getStyleClass().add("ff-box");

        Button resumeButton = UiFactory.createButton("CONTINUE", 20);
        Button newGameButton = UiFactory.createButton("NEW GAME", 20);
        Button exitButton = UiFactory.createButton("EXIT", 20);

        newGameButton.setOnAction(e -> MainApp.showScreen(new HeroSelectionWindow()));
        exitButton.setOnAction(e -> System.exit(0));

        resumeButton.setMaxWidth(Double.MAX_VALUE);
        newGameButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setMaxWidth(Double.MAX_VALUE);

        box.getChildren().addAll(resumeButton, newGameButton, exitButton);

        getChildren().add(box);
    }
}