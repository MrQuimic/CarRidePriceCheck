package pt.isec.gps.team11.gui.panes.utils;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * The type Image manager.
 */
public class ImageManager {
    private ImageManager() {
    }

    private static final HashMap<String, Image> images = new HashMap<>();
    public static Image getImage(String filename) {
        Image image = images.get(filename);
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String imageFile = s + "\\src\\main\\resources\\images\\" + filename;

        if (image == null)
            try {
                image = new Image(imageFile);
                images.put(filename, image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }

    public static Image getImageA(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try (InputStream is = ImageManager.class.getResourceAsStream("/images/" + filename)) {
                image = new Image(is);
                images.put(filename, image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }


    /**
     * Gets external image.
     *
     * @param filename the filename
     * @return the external image
     */
    public static Image getExternalImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try {
                image = new Image(filename);
                images.put(filename, image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }

    /**
     * Purge image.
     *
     * @param filename the filename
     */
    public static void purgeImage(String filename) {
        images.remove(filename);
    }

}