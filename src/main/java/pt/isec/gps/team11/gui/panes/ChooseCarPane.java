package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.components.BookForm;
import pt.isec.gps.team11.gui.panes.components.BookInfos;
import pt.isec.gps.team11.gui.panes.components.MapDisplay;
import pt.isec.gps.team11.gui.panes.components.MenuTop;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import javafx.scene.layout.*;

import java.util.Objects;

public class ChooseCarPane extends BorderPane {

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
    public ChooseCarPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;

        this.myBrowser = new MyBrowser(crpcManager);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        VBox vBox= new VBox();

        CSSManager.applyCSS(this,"mystyle.css");
        vBox = new VBox();



        Label lbOurServices = new Label("Choose CAR");
        VBox vbFirst = new VBox(new Separator(), lbOurServices, new Separator());

        Image imgFirst = ImageManager.getImage("cars/Audi_A3.png");
        ImageView img1 = new ImageView(imgFirst);
        Image imgSecond = ImageManager.getImage("cars/Renault_Space.png");
        ImageView img2 = new ImageView(imgSecond);
        Image imgThird = ImageManager.getImage("cars/Seat_Ibiza.png");
        ImageView img3 = new ImageView(imgThird);
        Image imgFourth = ImageManager.getImage("cars/Tesla_Y.png");
        ImageView img4 = new ImageView(imgFourth);

        img1.setFitHeight(150);
        img1.setPreserveRatio(true);
        img2.setFitHeight(100);
        img2.setPreserveRatio(true);
        img3.setFitHeight(100);
        img3.setPreserveRatio(true);
        img4.setFitHeight(150);
        img4.setPreserveRatio(true);


        HBox hbSecond= new HBox(new Separator(), new Separator());
        hbSecond.getChildren().addAll(img1, img2, img3, img4);
        hbSecond.setSpacing(35);


        Label label = new Label("<--------- CHOOSE CAR ------->");
        vBox.getChildren().addAll(vbFirst, hbSecond, label);
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

        if (crpcManager.getMenuOpt() == MenuOpt.ChooseCar) {
            configAdapter();

            this.setVisible(true);
            return;
        }else{
            this.setVisible(false);
        }
    }




}
