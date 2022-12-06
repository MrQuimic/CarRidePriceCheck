package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.components.BookForm;
import pt.isec.gps.team11.gui.panes.components.BookInfos;
import pt.isec.gps.team11.gui.panes.components.MapDisplay;
import pt.isec.gps.team11.gui.panes.components.MenuTop;
import pt.isec.gps.team11.model.CRPCManager;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

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
        HBox hBox = new HBox(bookInfos);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);

        MapDisplay mapDisplay = new MapDisplay(crpcManager, myBrowser);
        HBox hBoxMap = new HBox(mapDisplay);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);


        hBox.setAlignment(Pos.CENTER);
        hBoxMap.setAlignment(Pos.CENTER);

        splitPane.getItems().addAll(hBox, hBoxMap);
        splitPane.setDividerPositions(0.25f, 0.75f); //Important for zoom
        splitPane.getDividers().get(0).positionProperty().addListener((observable,oldValue,newValue) -> {
            splitPane.setDividerPositions(0.25f, 0.75f);
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

        if (crpcManager.getMenuOpt() == MenuOpt.CONFIRMBOOKING) {



            configAdapter();

            this.setVisible(true);
            return;
        }else{
            this.setVisible(false);
        }
    }




}
