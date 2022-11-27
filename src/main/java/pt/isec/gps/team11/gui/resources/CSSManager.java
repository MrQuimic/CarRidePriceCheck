package pt.isec.gps.team11.gui.resources;

import javafx.scene.Parent;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Css manager.
 */
public class CSSManager {
    private CSSManager() { }

    /**
     * Apply css.
     *
     * @param parent   the parent
     * @param filename the filename
     */
    public static void applyCSS(Parent parent, String filename) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        var url = CSSManager.class.getResource(s+"\\src\\main\\java\\pt\\isec\\gps\\team11\\gui\\resources\\css\\" +filename);
        if (url == null)
            return;
        String fileCSS = url.toExternalForm();
        parent.getStylesheets().add(fileCSS);
    }

}

