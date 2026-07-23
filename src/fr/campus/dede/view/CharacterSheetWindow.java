package fr.campus.dede.view;
import fr.campus.dede.fonts.Fonts;
import fr.campus.dede.model.*;
import javax.swing.*;
import java.awt.*;

public class CharacterSheetWindow  extends JFrame {
    private final Color background = new Color(100,10,0);
    private final Color gold = new Color(220,180,80);

    public CharacterSheetWindow(Characters character) {
        setSize(600, 500);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(background);


        JLabel title = new JLabel("Hero sheet", SwingConstants.CENTER);

        title.setFont(Fonts.getUnderTitleFont(50));
        title.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        title.setForeground(Color.BLACK);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(background);
        infoPanel.setLayout(new GridLayout(0,1,10,10));

        JLabel name = createLabel("Name : "+character.getName());
        JLabel type = createLabel("Class : "+character.getType());
        JLabel life = createLabel("Life : "+character.getLifePoints());
        JLabel strength = createLabel("Strength : "+character.getStrength());
        JLabel offEquip = createLabel("Equipment 1 : "+character.getOffensiveEquipment());
        JLabel defEquip = createLabel("Equipment 2 : "+character.getDefensiveEquipment());

        infoPanel.add(name);
        infoPanel.add(type);
        infoPanel.add(life);
        infoPanel.add(strength);
        infoPanel.add(offEquip);
        infoPanel.add(defEquip);


        JButton close = new JButton("BACK");
        close.setForeground(gold);
        close.setFont(Fonts.getButtonFont(25));
        close.setOpaque(false);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        close.setFocusPainted(false);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                close.setForeground(new Color(255, 220, 120));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                close.setForeground(new Color(220, 180, 80));
            }
        });


        close.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setBackground(background);
        bottom.add(close);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);

        label.setFont(Fonts.getButtonFont(30));
        label.setForeground(Color.WHITE);

        return label;
    }
}
