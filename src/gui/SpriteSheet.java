package gui;

import java.awt.image.BufferedImage;

/**
 * Cut out the sprite from spritesheet
 */

public class SpriteSheet {

    private BufferedImage img;

    public SpriteSheet(BufferedImage img) {
        this.img = img;
    }

    /**
     * Works with spritesheet where individual sprites dimensions are symmetrical
     */

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return img.getSubimage((col * width) - width, (row * height) - height, width, height);
    }
}
