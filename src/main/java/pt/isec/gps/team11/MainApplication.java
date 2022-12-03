package pt.isec.gps.team11;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.Label;

import javafx.scene.paint.Color;

import javafx.stage.Stage;
import pt.isec.gps.team11.gui.panes.RootPane;
import pt.isec.gps.team11.model.CRPCManager;


import java.io.File;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class MainApplication extends Application {

    CRPCManager crpcManager;


    @Override
    public void init() throws Exception {
        super.init();
        crpcManager = new CRPCManager(); // here or in the constructor
        //gpeManager.f1_students();
       // crpcManager.load();
    }


    /*public static void main(String[] args) {
        launch(args);
    }*/
    @Override
    public void start(Stage primaryStage) {
        RootPane root = new RootPane(crpcManager);
        root.getChildren().add(new Label("Project GPS"));
        root.getChildren().add(new Label("Car Ride Price Check"));

        Scene scene = new Scene(root, 1100, 700, Color.INDIGO);
        primaryStage.setScene(scene);

        primaryStage.setTitle("DEIS-ISEC");
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(600);
        primaryStage.setMaxHeight(900);
        primaryStage.setMaxWidth(1600);
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


