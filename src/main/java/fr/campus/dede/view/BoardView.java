package fr.campus.dede.view;

import fr.campus.dede.core.Game;
import fr.campus.dede.map.Cell;
import fr.campus.dede.model.enemies.*;
import fr.campus.dede.items.MysteryBox;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class BoardView extends Pane {

    private final Game game;
    private static final int CELL_SIZE = 40;
    private final String[] revealedContentType;
    private final Color emptyCell = Color.WHITE;
    private final Color gold = Color.web("#dcb450");
    private final Color unrevealedCell = Color.web("#2a2a2a");

    private final boolean[] revealed;

    private StackPane playerToken;

    public BoardView(Game game) {
        this.game = game;
        revealed = new boolean[game.getPathBoard().size()];
        revealedContentType = new String[game.getPathBoard().size()];

        revealCell(game.getPlayerPos());

        setPrefSize(12 * CELL_SIZE + 20, 9 * CELL_SIZE + 20);
        setStyle("-fx-background-color: black;");

        drawBoard();
    }

    private void revealCell(int position) {
        if (!revealed[position]) {
            revealed[position] = true;
            Cell cell = game.getPathBoard().get(position);
            revealedContentType[position] = cell.getContent() != null
                    ? cell.getContent().getClass().getSimpleName()
                    : null;
        }
    }

    private void drawBoard() {
        getChildren().clear();

        for (Cell cell : game.getPathBoard()) {
            double x = 10 + cell.getCol() * CELL_SIZE;
            double y = 10 + cell.getRow() * CELL_SIZE;

            Rectangle rect = new Rectangle(x, y, CELL_SIZE - 4, CELL_SIZE - 4);
            rect.setArcWidth(10);
            rect.setArcHeight(10);
            rect.setFill(cellColor(cell));
            rect.setStroke(gold);
            rect.setStrokeWidth(2);

            getChildren().add(rect);

            double centerX = x + (CELL_SIZE - 4) / 2.0;
            double centerY = y + (CELL_SIZE - 4) / 2.0;

            Node icon = cellIcon(cell, centerX, centerY);
            if (icon != null) {
                getChildren().add(icon);
            }
        }

        drawPlayerToken();
    }

    private void drawPlayerToken() {
        Cell playerCell = game.getPathBoard().get(game.getPlayerPos());
        double x = 10 + playerCell.getCol() * CELL_SIZE;
        double y = 10 + playerCell.getRow() * CELL_SIZE;

        Circle circle = new Circle(10);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        circle.setEffect(new DropShadow(6, Color.BLACK));

        playerToken = new StackPane(circle);
        playerToken.setLayoutX(x + (CELL_SIZE - 4) / 2.0 - 10);
        playerToken.setLayoutY(y + (CELL_SIZE - 4) / 2.0 - 10);

        getChildren().add(playerToken);
    }

    public void refresh() {
        revealedContentType[game.getPlayerPos()] = game.getLastContentType();
        revealed[game.getPlayerPos()] = true;
        drawBoard();
    }

    private Color cellColor(Cell cell) {
        if (!revealed[cell.getPosition()]) {
            return unrevealedCell;
        }

        String type = revealedContentType[cell.getPosition()];
        if (type == null) return emptyCell;

        return switch (type) {
            case "Dragon" -> Color.web("#b40000");
            case "Sorcerer" -> Color.web("#780096");
            case "Goblin" -> Color.web("#1e8214");
            case "MysteryBox" -> gold;
            default -> emptyCell;
        };
    }

    private Node cellIcon(Cell cell, double centerX, double centerY) {
        if (!revealed[cell.getPosition()]) {
            return null;
        }

        String type = revealedContentType[cell.getPosition()];
        if (type == null) return null;

        return switch (type) {
            case "Dragon" -> {
                Polygon spike = new Polygon(
                        centerX, centerY - 9,
                        centerX - 7, centerY + 7,
                        centerX + 7, centerY + 7
                );
                spike.setFill(Color.WHITE);
                yield spike;
            }
            case "Sorcerer" -> {
                Polygon diamond = new Polygon(
                        centerX, centerY - 9,
                        centerX + 7, centerY,
                        centerX, centerY + 9,
                        centerX - 7, centerY
                );
                diamond.setFill(Color.WHITE);
                yield diamond;
            }
            case "Goblin" -> {
                Circle head = new Circle(centerX, centerY, 7);
                head.setFill(Color.WHITE);
                yield head;
            }
            case "MysteryBox" -> {
                Rectangle box = new Rectangle(centerX - 7, centerY - 7, 14, 14);
                box.setFill(Color.TRANSPARENT);
                box.setStroke(Color.WHITE);
                box.setStrokeWidth(2);
                yield box;
            }
            default -> null;
        };
    }
}