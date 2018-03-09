package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/*
 * Class for loading fonts
 */

public class FontLoader {
    
    private Font font;

    public Font loadFont(String path, float size){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path));
            return font.deriveFont(size);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
