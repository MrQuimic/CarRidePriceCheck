package pt.isec.gps.team11.gui.panes.utils;

import javafx.scene.text.Font;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Font manager.
 */
public class FontManager {
    private FontManager() {
    }

    /**
     * Load font font.
     *
     * @param filename the filename
     * @param size     the size
     * @return the font
     */
    public static Font loadFont(String filename, int size) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        try (InputStream inputStreamFont =
                     FontManager.class.getResourceAsStream(s + "\\src\\main\\java\\pt\\isec\\gps\\team11\\gui\\resources\\fonts\\" + filename)) {
            return Font.loadFont(inputStreamFont, size);
        } catch (Exception e) {
            return null;
        }
    }
}