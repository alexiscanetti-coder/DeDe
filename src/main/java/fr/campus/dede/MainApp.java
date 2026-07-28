package fr.campus.dede;

import fr.campus.dede.view.TitleScreen;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static StackPane rootContainer;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        rootContainer = new StackPane();
        rootContainer.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(rootContainer, 800, 450);
        scene.getStylesheets().add(
                MainApp.class.getResource("/fr/campus/dede/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle("Dungeons and Dragons");
        stage.setResizable(false);

        showScreen(new TitleScreen());

        stage.show();
    }

    public static Stage getStage() {
            return primaryStage;
    }

    public static void showScreen(Parent screen) {
        rootContainer.getChildren().setAll(screen);
    }

    public static void main(String[] args) {
        launch(args);
    }
}