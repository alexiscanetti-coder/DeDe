package fr.campus.dede.view;
import java.awt.*;

import fr.campus.dede.db.HeroesDAO;
import fr.campus.dede.fonts.Fonts;
import fr.campus.dede.model.*;
import fr.campus.dede.model.heroes.*;

import javax.swing.*;

public class HeroChooseWindow extends JFrame {
    private Heroes heroes;
    private final HeroesDAO database;

    private final Color background = new Color(100, 10, 0);
    private final Color gold = new Color(220, 180, 80);

    public HeroChooseWindow() {
        database = new HeroesDAO();

        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseHero();

        this.setVisible(true);
    }

    private void chooseHero() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(background);

        JLabel title = new JLabel("Choose hero", SwingConstants.CENTER);
        title.setFont(Fonts.getUnderTitleFont(60));
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 40,0));
        title.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2,40,0));
        buttonPanel.setBackground(background);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,150,60,150));

        JButton warriorButton = createButton("WARRIOR");
        JButton wizardButton = createButton("WIZARD");

        warriorButton.addActionListener(e -> createHero("2"));
        wizardButton.addActionListener(e -> createHero("1"));

        buttonPanel.add(warriorButton);
        buttonPanel.add(wizardButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    private void createHero(String type) {
        String name = JOptionPane.showInputDialog(this,
                "Choose a name :",
                "Creation hero",
                JOptionPane.QUESTION_MESSAGE);

        if(name == null || name.isBlank()) {
            name = "HEROS";
        }
        name = name.trim().toUpperCase();

        switch(type) {
            case "1":
                heroes = new Wizard(name, null, null);
                break;
            case "2":
                heroes = new Warrior(name, null, null);
                break;
        }

        database.create(heroes);

        this.dispose();
        //new MenuWindow();
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
                button.setForeground(new Color(255, 220, 120));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setForeground(gold);
            }
        });
        return button;
    }
}
