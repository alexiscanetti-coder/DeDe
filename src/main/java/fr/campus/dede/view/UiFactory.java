package fr.campus.dede.view;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class UiFactory {

    public static Button createButton(String text, double fontSize) {
        Button button = new Button(text);
        button.setFont(loadFont("/fr/campus/dede/fonts/PressStart2P-Regular.ttf", fontSize));
        button.setStyle("""
            -fx-text-fill: white;
            -fx-background-color: transparent;
            -fx-cursor: hand;
        """);

        button.setOnMouseEntered(e -> button.setStyle("""
            -fx-text-fill: #ffdc78;
            -fx-background-color: transparent;
            -fx-cursor: hand;
        """));
        button.setOnMouseExited(e -> button.setStyle("""
            -fx-text-fill: white;
            -fx-background-color: transparent;
            -fx-cursor: hand;
        """));

        return button;
    }

    public static Font loadFont(String resourcePath, double size) {
        Font font = Font.loadFont(UiFactory.class.getResourceAsStream(resourcePath), size);
        if (font == null) {
            System.err.println("Police introuvable : " + resourcePath);
            return Font.font("Serif", size);
        }
        return font;
    }
}