package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.BookForm;
import pt.isec.gps.team11.gui.panes.utils.MenuTop;
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
    HBox submitBtns;
    MenuTop bp;

    MenuOpt menuOpt;
    public BookingPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        this.menuOpt = menuOpt;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {


        myBrowser = new MyBrowser();
        VBox vBox= new VBox();
        VBox vBox2= new VBox();
        submitBtns= new HBox();
        SplitPane splitPane = new SplitPane();


        Label Origin = new Label("Origin");
        Label Destination = new Label("Destination");

        BookForm bookForm = new BookForm(crpcManager);
        HBox hBox = new HBox(bookForm);

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        tf_origin.setText("Coimbra, Portugal");
        tf_destination.setText("Porto, Portugal");
        btnSubmit.setId("mbtnSubmit");
        btnReset.setId("mbtnReset");
        submitBtns.getChildren().addAll(btnReset, btnSubmit);
        submitBtns.setPadding(new Insets(10,0,0,40));
        submitBtns.setSpacing(10);
        leftControl = new VBox(new Label( " "), Origin, tf_origin, Destination, tf_destination, submitBtns);
        leftControl.setMaxWidth(200);
        leftControl.setSpacing(10);
        vBox2.getChildren().addAll(hBox, leftControl);
        vBox2.setAlignment(Pos.TOP_CENTER);
        rightControl = new VBox(new Label(" "), myBrowser);
        splitPane.getItems().addAll(vBox2, rightControl);
        splitPane.setDividerPositions(0.20f, 0.75f); //Important for zoom


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
