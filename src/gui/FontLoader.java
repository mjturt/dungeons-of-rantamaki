package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 * Class for loading fonts
 * @author Maks Turtiainen
 */

public class FontLoader {
    
    private Font font;

    /**
     * Loads font from file
     *
     * @param path
     * @param size
     * @return font
     */
    public Font loadFont(String path, float size){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path));
            return font.deriveFont(size);
        } catch (final FontFormatException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
