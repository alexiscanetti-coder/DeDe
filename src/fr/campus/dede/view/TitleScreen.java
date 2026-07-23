package fr.campus.dede.view;
import fr.campus.dede.fonts.*;
import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JFrame {
    public TitleScreen() {
        setSize(800,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 10, 0));

        JLabel title = new JLabel("Dungeons and Dragons", SwingConstants.CENTER);
        title.setFont(Fonts.getTitleFont(120f));
        title.setForeground(Color.WHITE);

        JButton playButton = new JButton("PLAY");
        playButton.setForeground(new Color(220, 180, 80));
        playButton.setFont(Fonts.getButtonFont(40));
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                playButton.setForeground(new Color(255, 220, 120));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                playButton.setForeground(new Color(220, 180, 80));
            }
        });

        playButton.addActionListener(e -> {
            dispose();
            new MenuWindow();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(100, 10, 0));
        buttonPanel.setOpaque(true);
        buttonPanel.add(playButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,40,0));

        panel.add(title, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
