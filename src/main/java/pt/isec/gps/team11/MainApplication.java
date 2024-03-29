package pt.isec.gps.team11;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.paint.Color;

import javafx.stage.Stage;
import pt.isec.gps.team11.gui.panes.RootPane;
import pt.isec.gps.team11.model.CRPCManager;


/**
 * Google MAPS API KEYS: AIzaSyDP1feUTnivFyTntFVr722y7SwOxsit7VQ
 * <p>
 * Google MAPS Second API (backup key): AIzaSyBhp6LYSLWg_iX3W2st1zv85KTbghrl04g
 * <p>
 * Google MAPS Third API backup key (backup key):  AIzaSyDEL0TWQ57CRpo64I7p-RjlXsuBqG_tGBw
 */

public class MainApplication extends Application {

    CRPCManager crpcManager;


    @Override
    public void init() throws Exception {
        super.init();
        crpcManager = new CRPCManager(); // here or in the constructor

    }


    @Override
    public void start(Stage primaryStage) {
        RootPane root = new RootPane(crpcManager);
        root.getChildren().add(new Label("Project GPS"));
        root.getChildren().add(new Label("Car Ride Price Check"));

        Scene scene = new Scene(root, 1250, 700, Color.INDIGO);
        primaryStage.setScene(scene);

        primaryStage.setTitle("DEIS-ISEC");
        primaryStage.setMinWidth(880);
        primaryStage.setWidth(1250);
        primaryStage.setMinHeight(700);
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.setMaximized(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(evt -> {
            Platform.exit();
        });
    }
    @Override
    public void stop() throws Exception {
        super.stop();
    }

}


