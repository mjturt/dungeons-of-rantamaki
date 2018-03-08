package gui;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage img;

    public SpriteSheet(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return img.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
    }
}
