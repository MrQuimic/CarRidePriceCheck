package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.panes.components.BookInfos;
import pt.isec.gps.team11.gui.panes.components.MapDisplay;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.model.CRPCManager;
import javafx.scene.layout.*;
import pt.isec.gps.team11.model.fsm.States;

public class ConfirmPane extends BorderPane {

    CRPCManager crpcManager;
    HBox submitBtns;

    Label lbInfoLabel;
    TextArea lbInfo;
    Button btnConfirm;
    MyBrowser myBrowser;

    public ConfirmPane(CRPCManager crpcManager, MyBrowser myBrowser) {
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;
        this.myBrowser = new MyBrowser(crpcManager);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this, "styles.css");
        Font font = Font.font("Verdana", FontWeight.BOLD, 16);
        Font fontSmall = Font.font("Verdana", FontWeight.BOLD, 14);
        VBox vBox = new VBox();
        submitBtns = new HBox();
        SplitPane splitPane = new SplitPane();


        BookInfos bookInfos = new BookInfos(crpcManager, myBrowser);
        HBox hBox = new HBox(bookInfos);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(50);
        bookInfos.setPadding(new Insets(0, 0, 0, 50));

        lbInfoLabel = new Label("Trip Information");
        lbInfoLabel.setPadding(new Insets(20, 0, 5, 0));
        lbInfoLabel.setFont(font);
        lbInfoLabel.setAlignment(Pos.CENTER);
        lbInfo = new TextArea();
        lbInfo.setMaxWidth(600);
        lbInfo.setMaxHeight(50);

        MapDisplay mapDisplay = new MapDisplay(crpcManager, myBrowser);
        btnConfirm = new Button("Confirm Trip");
        btnConfirm.setId("mbtnSubmit");


        VBox vBoxMap = new VBox(lbInfoLabel, lbInfo, btnConfirm, mapDisplay);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(50);
        vBoxMap.setSpacing(20);


        hBox.setAlignment(Pos.CENTER_LEFT);
        vBoxMap.setAlignment(Pos.CENTER_LEFT);

        splitPane.getItems().addAll(hBox, vBoxMap);
        splitPane.setDividerPositions(0.40f, 0.55f); //Important for zoom
        splitPane.getDividers().get(0).positionProperty().addListener((observable, oldValue, newValue) -> {
            splitPane.setDividerPositions(0.40f, 0.55f);
        });

        splitPane.setId("splitPaneContent");


        vBox.getChildren().addAll(splitPane);

        this.setCenter(vBox);

    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });


        btnConfirm.setOnAction(actionEvent -> {
            crpcManager.confirmTrip();
            crpcManager.goMainMenu();
            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.setTitle("Trip Status");
            confirm.setContentText("Your Trip has been scheduled!\nThank you for your preference");
            confirm.show();
        });
    }

    private void configAdapter() {
    }

    private void update() {
        if (crpcManager.getState() == States.CONFIRM_BOOKING) {
            configAdapter();

            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }


}
