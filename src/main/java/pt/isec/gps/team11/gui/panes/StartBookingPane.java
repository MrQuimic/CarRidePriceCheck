package pt.isec.gps.team11.gui.panes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.MenuTop;
import pt.isec.gps.team11.model.CRPCManager;

public class StartBookingPane extends BorderPane {

    StackPane stackPane;
    CRPCManager crpcManager;

    private Scene scene;
    MyBrowser myBrowser;
    TextField tf_origin = new TextField();
    TextField tf_destination = new TextField();
    Button btnSubmit = new Button("Submit");
    Button btnReset = new Button("Reset");

    VBox vbox, leftControl, rightControl;
    MenuTop bp;

    MenuOpt menuOpt;
    public StartBookingPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        this.menuOpt = menuOpt;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {


        myBrowser = new MyBrowser();
        VBox vBox= new VBox();
        SplitPane splitPane = new SplitPane();


        Label Origin = new Label("Origin");
        Label Destination = new Label("Destination");

        tf_origin.setText("Coimbra, Portugal");
        tf_destination.setText("Porto, Portugal");

        leftControl = new VBox(new Label("--"), Origin, tf_origin, Destination, tf_destination, btnSubmit, btnReset);
        rightControl = new VBox(new Label("MAP"), myBrowser);
        splitPane.getItems().addAll(leftControl);
        splitPane.setDividerPositions(0.15f, 0.85f); //Important for zoom


        scene = new Scene(splitPane);
        vBox.getChildren().addAll(splitPane);

        this.setCenter(vBox);

    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void configAdapter() {
    }

    private void update() {


        if (crpcManager.getMenuOpt() == MenuOpt.STARTBOOKING) {
            configAdapter();
            this.setVisible(true);
            return;
        }else{
            this.setVisible(false);
        }

    }




}
