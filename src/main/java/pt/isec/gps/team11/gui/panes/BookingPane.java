package pt.isec.gps.team11.gui.panes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

public class BookingPane extends BorderPane {

    StackPane stackPane;
    CRPCManager crpcManager;

    private Scene scene;
    MyBrowser myBrowser;
    TextField tf_origin = new TextField();
    TextField tf_destination = new TextField();
    Button btnSubmit = new Button("Submit");
    Button btnReset = new Button("Reset");

    VBox vbox, leftControl, rightControl;

    public BookingPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {



        myBrowser = new MyBrowser();

        SplitPane splitPane = new SplitPane();


        Label Origin = new Label("Origin");
        Label Destination = new Label("Destination");

        tf_origin.setText("Coimbra, Portugal");
        tf_destination.setText("Porto, Portugal");

        leftControl = new VBox(new Label("--"), Origin, tf_origin, Destination, tf_destination, btnSubmit, btnReset);
        rightControl = new VBox(new Label("MAP"), myBrowser);
        splitPane.getItems().addAll(leftControl, rightControl);
        splitPane.setDividerPositions(0.15f, 0.85f); //Important for zoom


        scene = new Scene(splitPane);


        this.setCenter(splitPane);

    }

    private void registerHandlers() {

    }

    private void configAdapter() {
    }

    private void update() {
        if (crpcManager.getMenuOpt() == MenuOpt.IN_STATE && crpcManager.getState() == States.BOOKING) {

            configAdapter();

            this.setVisible(true);
            return;
        }
        this.setVisible(false);
    }




}
