package fr.campus.dede.view;
import java.awt.*;
import java.util.*;
import java.util.List;

import fr.campus.dede.db.Database;
import fr.campus.dede.core.Game;
import fr.campus.dede.fonts.Fonts;
import fr.campus.dede.model.*;
import fr.campus.dede.model.characters.*;
import javax.swing.*;

public class MenuWindow extends JFrame{
    private Characters characters;
    private final Database database;

    private final Color background = new Color(100, 10, 0);
    private final Color gold = new Color(220, 180, 80);

    public MenuWindow() {
        database = new Database();

        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createCharacter();
        initMenu();

        this.setVisible(true);
    }

    private void initMenu() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Menu", SwingConstants.CENTER);

        title.setFont(Fonts.getTitleFont(60));
        title.setBorder(BorderFactory.createEmptyBorder(50,0,40,0));
        title.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new GridLayout(5,1,10,10));

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,250,30,250));

        JButton newGame = createButton("NEW GAME");
        JButton sheet = createButton("HERO SHEET");
        JButton list = createButton("ALL HEROES");
        JButton exit = createButton("EXIT");

        newGame.addActionListener(e -> {
            setVisible(false);

            Game game = new Game(characters);
            game.startGame();

            setVisible(true);
        });

        sheet.addActionListener(e -> new CharacterSheetWindow(characters));
        list.addActionListener(e -> displayAllHeroes());
        exit.addActionListener(e -> System.exit(0));

        buttonPanel.add(newGame);
        buttonPanel.add(sheet);

        buttonPanel.add(list);
        buttonPanel.add(exit);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private String chooseCharacterType() {
        String[] options = {"WIZARD", "WARRIOR"};
        int choice = JOptionPane.showOptionDialog(this,
                "Choose a type :",
                "Creation character",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

    return (choice == 1) ? "2" : "1";
    }

    private void createCharacter() {
        String type = chooseCharacterType();

        String name = JOptionPane.showInputDialog(this,
                "Choose a name :",
                "Creation character",
                JOptionPane.QUESTION_MESSAGE);

        if(name == null || name.isBlank()) {
            name = "HEROS";
        }
        name = name.trim().toUpperCase();

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

    /*private void displayCharacter() {
        JOptionPane.showMessageDialog(this,
                characters.toString(),
                "Character sheet",
                JOptionPane.INFORMATION_MESSAGE);
    }*/

    private void displayAllHeroes() {
        List<Characters> heroes = database.fetchHeroes();

        if(heroes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No characters found",
                    "Characters",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(Characters hero : heroes) {
            sb.append(hero.toString()).append("\n\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        JOptionPane.showMessageDialog(this, scrollPane,
                "All characters",
                JOptionPane.PLAIN_MESSAGE);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFont(Fonts.getButtonFont(30));
        button.setForeground(gold);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setForeground(new Color(255,220,120)
                );
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setForeground(gold);
            }
        });
        return button;
    }
}
