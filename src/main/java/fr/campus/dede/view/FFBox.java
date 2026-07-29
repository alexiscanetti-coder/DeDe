package fr.campus.dede.view;

import javax.swing.*;
import java.awt.*;

public class FFBox extends JPanel {

    private final Color topColor = new Color(180, 20, 0);
    private final Color midColor = new Color(100, 10, 0);

    private final boolean showBorder;
    private final int arc;

    public FFBox() {
        this(true, 20);
    }

    public FFBox(boolean showBorder, int arc) {
        this.showBorder = showBorder;
        this.arc = arc;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int borderWidth = 6;

        GradientPaint gradient = new GradientPaint(
                0, h, topColor,
                0, 0, midColor
        );
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, w, h, arc, arc);

        if (showBorder) {
            g2.setStroke(new BasicStroke(borderWidth));
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(borderWidth / 2, borderWidth / 2, w - borderWidth, h - borderWidth, arc, arc);
        }

        g2.dispose();
    }
}
