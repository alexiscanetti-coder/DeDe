package fr.campus.dede.core;
import fr.campus.dede.items.MysteryBox;
import fr.campus.dede.items.equipment.utility.*;
import fr.campus.dede.model.*;
import fr.campus.dede.map.*;
import fr.campus.dede.model.enemies.*;

import java.util.*;

public class Game {

    private final Random random;
    private final List<Cell> pathBoard;
    private String lastContentType;

    private int playerPos;
    private boolean finished;
    private final Heroes heroes;

    private Enemies pendingEnemy;
    private Cell pendingCell;

    public Game(Heroes heroes) {
        random = new Random();
        playerPos = 0;
        finished = false;
        this.heroes = heroes;

        pathBoard = new ArrayList<>();
        initBoard();
    }

    //GETTERS
    public int getPlayerPos() {
        return playerPos;
    }

    public List<Cell> getPathBoard() {return pathBoard; }

    public Heroes getHeroes() {
        return heroes;
    }

    public boolean isFinished() { return finished; }

    public String playTurn() {
        StringBuilder log = new StringBuilder();

        int dice = random.nextInt(10) +1;
        log.append("Dice : ").append(dice).append("\n");
        playerPos += dice;

        if(playerPos >= pathBoard.size()) {
            playerPos = pathBoard.size() - 1;
            finished = true;
            log.append("You reached the end of the board !");
            return log.toString();
        }

        Cell currentCell = pathBoard.get(playerPos);
        lastContentType = currentCell.getContent() != null
                ? currentCell.getContent().getClass().getSimpleName()
                : null;

        if(currentCell.getContent() instanceof Enemies enemy) {
            log.append("An enemy has appeared : ").append(enemy.getType()).append("\n");
            pendingEnemy = enemy;
            pendingCell = currentCell;
        } else {
            log.append(pathBoard.get(playerPos).interact(heroes));
        }

        /*if(heroes.getLifePoints() <= 0) {
            finished = true;
            log.append("\nYOU DIE");
        }*/
        return log.toString();
    }

    public boolean hasPendingCombat() { return pendingEnemy != null; }
    public Enemies getPendingEnemy() { return pendingEnemy; }
    public Cell getPendingCell() { return pendingCell; }
    public void clearPendingCombat() { pendingEnemy = null; pendingCell = null; }
    public void forceGameOver() { finished = true; }

    public String getLastContentType() {
        return lastContentType;
    }

    private void initBoard() {
        int[][] coords = {
                {8,0}, {8,1}, {8,2}, {8,3}, {8,4}, {8,5}, {8,6}, {8,7}, {8,8}, {8,9}, {8,10}, {8,11},
                {7,11},
                {6,11}, {6,10}, {6,9}, {6,8}, {6,7}, {6,6}, {6,5}, {6,4}, {6,3}, {6,2}, {6,1}, {6,0},
                {5,0},
                {4,0}, {4,1}, {4,2}, {4,3}, {4,4}, {4,5}, {4,6}, {4,7}, {4,8}, {4,9}, {4,10}, {4,11},
                {3,11},
                {2,11}, {2,10}, {2,9}, {2,8}, {2,7}, {2,6}, {2,5}, {2,4}, {2,3}, {2,2}, {2,1}, {2,0},
                {1,0},
                {0,0}, {0,1}, {0,2}, {0,3}, {0,4}, {0,5}, {0,6}, {0,7}, {0,8}, {0,9}, {0,10}, {0,11},
        };

        for (int i=0; i< coords.length; i++) {
            Cell cell = new Cell(i, coords[i][0], coords[i][1]);
            cell.setContent(getContentForPosition(i));
            pathBoard.add(cell);
        }
    }

    private CellContent getContentForPosition(int position) {
        return switch (position) {
            case 45, 52, 56, 62 -> new Dragon();
            case 10, 20, 25, 32, 35, 36, 37, 40, 44, 47 -> new Sorcerer();
            case 3, 6, 9, 12, 15, 18, 21, 24, 27, 30 -> new Goblin();
            case 2, 11, 5, 22, 38 -> new MysteryBox(new Mace());
            case 19, 26, 42, 53 -> new MysteryBox(new Sword());
            case 1, 4, 8, 17, 23 -> new MysteryBox(new LightningBolt());
            case 48, 49 -> new MysteryBox(new Fireball());
            case 7, 13, 31, 33, 39, 43 -> new MysteryBox(new StandardPotion());
            case 28, 41 -> new MysteryBox(new LargePotion());
            default -> null;
        };
    }

}