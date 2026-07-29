package fr.campus.dede.view;

import fr.campus.dede.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TitleScreen extends BorderPane {

    public TitleScreen() {
        setPadding(new Insets(20));
        getStyleClass().add("ff-panel");

        Text title = new Text("Dungeons and Dragons");
        title.setFont(UiFactory.loadFont("/fr/campus/dede/fonts/AGTime.ttf", 130));
        title.setFill(Color.WHITE);

        Button playButton = UiFactory.createButton("PLAY", 20);
        playButton.setOnAction(e -> MainApp.showScreen(new MenuWindow()));

        VBox buttonBox = new VBox(playButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(0, 0, 40, 0));

        setCenter(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        setBottom(buttonBox);
    }
}