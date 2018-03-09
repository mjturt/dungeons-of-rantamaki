package gui;

import java.awt.image.BufferedImage;

/*
 * Cut out the sprite from spritesheet
 */

public class SpriteSheet {

    private BufferedImage img;

    public SpriteSheet(BufferedImage img) {
        this.img = img;
    }

    /*
     * Making grid layout to spritesheet
     */

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return img.getSubimage((col * width) - width, (row * height) - height, width, height);
    }
}
