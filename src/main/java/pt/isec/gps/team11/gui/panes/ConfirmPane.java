package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.components.BookInfos;
import pt.isec.gps.team11.gui.panes.components.MapDisplay;
import pt.isec.gps.team11.gui.panes.components.MenuTop;
import pt.isec.gps.team11.model.CRPCManager;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.fsm.States;

public class ConfirmPane extends BorderPane {

    CRPCManager crpcManager;

    private Scene scene;
    TextField tf_origin = new TextField();
    TextField tf_destination = new TextField();
    Button btnSubmit = new Button("Submit");
    Button btnReset = new Button("Reset");

    VBox vbox, leftControl, rightControl;
    HBox submitBtns, hBoxMap;
    MenuTop bp;

    MenuOpt menuOpt;

    Label price = new Label();
    Label duration = new Label();
    Label distance = new Label();

    Car car;

    HBox hBox;

    MyBrowser myBrowser;
    public ConfirmPane(CRPCManager crpcManager, MyBrowser myBrowser){
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;
        this.myBrowser = new MyBrowser(crpcManager);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        VBox vBox= new VBox();
        VBox vBox2= new VBox();
        submitBtns= new HBox();
        SplitPane splitPane = new SplitPane();

        BookInfos bookInfos = new BookInfos(crpcManager, myBrowser);
        hBox = new HBox(bookInfos);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(50);
        bookInfos.setPadding(new Insets(0,0,0,50));


        MapDisplay mapDisplay = new MapDisplay(crpcManager, myBrowser);

        HBox hboxResults = new HBox(price, duration, distance);
        hboxResults.setAlignment(Pos.CENTER_RIGHT);
        hboxResults.setSpacing(50);

        VBox vboxRight = new VBox(mapDisplay, hboxResults);

        splitPane.getItems().addAll(hBox, vboxRight);
        splitPane.setDividerPositions(0.45f, 0.55f); //Important for zoom
        splitPane.getDividers().get(0).positionProperty().addListener((observable,oldValue,newValue) -> {
            splitPane.setDividerPositions(0.45f, 0.55f);
        });

        splitPane.setId("splitPaneContent");
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

        if (crpcManager.getState() == States.CONFIRM_BOOKING) {
            price.setText("Price: " + crpcManager.getCostOfTrip());
            duration.setText("Duration: " + crpcManager.getTimeOfTrip());
            distance.setText("Distance: " + crpcManager.getDistanceOfTrip());

/*            car = crpcManager.getCurrentTripCar();
            ImageView carIMG = new ImageView(car.getImage());
            hBox.getChildren().add(carIMG);*/


            this.setVisible(true);
            return;
        }else{
            this.setVisible(false);
        }
    }




}
