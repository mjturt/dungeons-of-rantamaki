package gui;

import java.awt.image.BufferedImage;

/*
 * Cut out the sprite from spritesheet
 */

public class SpriteSheet {

    private final BufferedImage img;

    public SpriteSheet(BufferedImage img) {
    	System.out.println("created spritesheet with image " + img.toString());
        this.img = img;
    }

    /*
     * Making grid layout to spritesheet
     */

    public BufferedImage grabImage(int col, int row, int width, int height) {
    	System.out.println("Grabbed an image" + col + row + width + height);
        return img.getSubimage((col * width) - width, (row * height) - height, width, height);
    }
}
