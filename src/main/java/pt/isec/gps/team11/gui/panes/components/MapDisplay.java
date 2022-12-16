package pt.isec.gps.team11.gui.panes.components;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.model.CRPCManager;

public class MapDisplay extends BorderPane {

    CRPCManager crpcManager;
    MyBrowser myBrowser;
    Label origin, destination;

    VBox rightControl;

    public MapDisplay(CRPCManager crpcManager, MyBrowser myBrowser) {
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        VBox vBox2 = new VBox();

        origin = new Label("Origin");
        destination = new Label("Destination");

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
