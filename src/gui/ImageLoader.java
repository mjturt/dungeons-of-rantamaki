package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * This class is used to load images to buffer
 *
 * @author Maks Turtiainen
 */
public class ImageLoader {
    private BufferedImage image;
    public ImageLoader () {
        System.out.println("Created imageloader");
    }

    /**
     * Load image safe way
     *
     * @param path
     * @return image
     */
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
