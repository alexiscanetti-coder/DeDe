package fr.campus.dede.view;
import java.awt.*;

import fr.campus.dede.db.Database;
import fr.campus.dede.fonts.Fonts;
import fr.campus.dede.model.*;

import javax.swing.*;

public class CharacterChooseWindow extends JFrame {
    private Characters characters;
    private final Database database;

    private final Color background = new Color(100, 10, 0);
    private final Color gold = new Color(220, 180, 80);

    public CharacterChooseWindow() {
        database = new Database();

        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseHero();

        this.setVisible(true);
    }

    private void chooseHero() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Choose hero", SwingConstants.CENTER);

        title.setFont(Fonts.getUnderTitleFont(60));
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 40,0));
        title.setForeground(Color.WHITE);


    }
}
