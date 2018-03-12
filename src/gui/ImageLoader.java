package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/* This class is used to load images to buffer so its easy to load images whenever needs to */

public class ImageLoader {
    private BufferedImage image;
    public ImageLoader () {
    	System.out.println("Created imageloader");
    }

    public BufferedImage loadImage(String path) {
        try {
        	System.out.println("Loaded image " + path);
            image = ImageIO.read(getClass().getResource(path));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
