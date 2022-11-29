package pt.isec.gps.team11.gui.panes.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.resources.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MapDisplay extends BorderPane {

    CRPCManager crpcManager;

    private Scene scene;
    MyBrowser myBrowser;
    TextField tf_origin = new TextField();
    TextField tf_destination = new TextField();
    Label origin, destination;

    VBox vbox, leftControl, rightControl;
    HBox submitBtns;
    MenuTop bp;

    public MapDisplay(CRPCManager crpcManager){
        this.crpcManager = crpcManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {


        VBox vBox= new VBox();
        VBox vBox2= new VBox();

        SplitPane splitPane = new SplitPane();


        origin = new Label("Origin");
        destination = new Label("Destination");



        myBrowser = new MyBrowser(crpcManager);

        rightControl = new VBox(new Label(" "), myBrowser);
        vBox2.getChildren().addAll(rightControl);
        vBox2.setAlignment(Pos.TOP_CENTER);




        this.setCenter(vBox2);
    }

    private void registerHandlers() {

    }

    private void configAdapter() {
    }

    private void update() {

    }

}
