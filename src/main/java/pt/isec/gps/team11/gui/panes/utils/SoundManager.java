package pt.isec.gps.team11.gui.panes.utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * The type Sound manager.
 */
public class SoundManager {
    private SoundManager() {
    }

    private static MediaPlayer mp;

    /**
     * Stop boolean.
     *
     * @param filename the filename
     * @return the boolean
     */
    public static boolean stop(String filename) {


        var url = SoundManager.class.getResource("sounds/" + filename);
        if (url == null)
            return false;

        String path = url.toExternalForm();
        Media music = new Media(path);


        mp.stop();
        mp = new MediaPlayer(music);

        mp.setStartTime(Duration.ZERO);
        mp.setStopTime(music.getDuration());
        mp.setAutoPlay(false);
        return true;
    }

    /**
     * Play boolean.
     *
     * @param filename the filename
     * @return the boolean
     */
    public static boolean play(String filename) {
        try {
            var url = SoundManager.class.getResource("sounds/" + filename);
            if (url == null) return false;
            String path = url.toExternalForm();
            Media music = new Media(path);
            if (mp != null && mp.getStatus() == MediaPlayer.Status.PLAYING)
                mp.stop();
            mp = new MediaPlayer(music);

            mp.setStartTime(Duration.ZERO);
            mp.volumeProperty().set(0.04);

            mp.setStopTime(Duration.millis(7000));
            mp.setAutoPlay(true);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(8),
                            new KeyValue(mp.volumeProperty(), 0)));
            timeline.play();

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}