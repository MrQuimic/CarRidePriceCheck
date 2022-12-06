package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.components.BookForm;
import pt.isec.gps.team11.gui.panes.components.MapDisplay;
import pt.isec.gps.team11.gui.panes.components.MenuTop;
import pt.isec.gps.team11.model.CRPCManager;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

public class BookingPane extends BorderPane {

    CRPCManager crpcManager;

    private Scene scene;

    MyBrowser myBrowser;

    public BookingPane(CRPCManager crpcManager, MyBrowser myBrowser){
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        VBox vBox= new VBox();
        VBox vBox2= new VBox();
        SplitPane splitPane = new SplitPane();



        BookForm bookForm = new BookForm(crpcManager, myBrowser);
        HBox hBox = new HBox(bookForm);
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


        if (crpcManager.getMenuOpt() == MenuOpt.BOOKING) {
            configAdapter();
            this.setVisible(true);
            return;
        }else{
            this.setVisible(false);
        }

    }
}
