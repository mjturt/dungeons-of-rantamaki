bpackage gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/* This class is used to load images to buffer so its easy to load images whenever needs to */

public class ImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
