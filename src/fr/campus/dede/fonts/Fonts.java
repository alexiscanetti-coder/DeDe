package fr.campus.dede.fonts;
import java.awt.*;
import java.io.InputStream;

public class Fonts {
    public static Font getTitleFont(float size) {
        return loadFont("AGTime.ttf", size);
    }

    public static Font getButtonFont(float size) {
        return loadFont("Cinzel-Regular.otf", size);
    }


    public static Font loadFont(String path, float size) {
        try {
            InputStream is = Fonts.class.getResourceAsStream(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(Font.PLAIN, size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Serif", Font.BOLD, (int) size);
        }
    }
}
