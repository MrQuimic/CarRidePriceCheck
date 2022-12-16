package pt.isec.gps.team11.gui.panes.utils;

import javafx.scene.Parent;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Css manager.
 */
public class CSSManager {
    private CSSManager() {
    }

    /**
     * Apply css.
     *
     * @param parent   the parent
     * @param filename the filename
     */
    public static void applyCSS(Parent parent, String filename) {
        var url = CSSManager.class.getResource("/css/" + filename);
        //System.out.println(url);
        if (url == null)
            return;
        String fileCSS = url.toExternalForm();
        parent.getStylesheets().add(fileCSS);
    }

    public static void applyCSSPlanB(Parent parent, String filename) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        URL url = null;
        try {
            url = new File(s + "\\src\\main\\java\\pt\\isec\\gps\\team11\\gui\\resources\\css\\mystyle.css").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(url);
        //System.err.println(cssFile);
        if (url == null)
            return;
        String fileCSS = url.toExternalForm();
        parent.getStylesheets().add(fileCSS);
    }
}