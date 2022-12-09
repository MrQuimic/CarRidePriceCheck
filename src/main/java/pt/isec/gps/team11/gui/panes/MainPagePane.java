package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;

import java.util.Objects;

public class MainPagePane extends BorderPane {
    CRPCManager crpcManager;

    /**
     * The background color of the main window.
     * <p>
     * The Background.
     */
    /**
     * The Background.
     */
    Color background = Color.LIGHTGRAY;

    /**
     * The T main menu.
     */
    Text tMainMenu;
    /**
     * The Btns.
     */
    Button[] btns;

    private Button btnBook;
    private static final int NRB = 7;
    private static final int BW = 7;
    private static final int BH = 7;
    /**
     * The Canvas.
     */
    Canvas canvas;
    VBox vBox;


    /**
     * The Canvas pane.
     */
    ScrollPane canvasPane;

    MenuOpt menuOpt;


    public MainPagePane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        this.menuOpt = menuOpt;
        createViews();
        registerHandler();
        update();

    }

    private void createViews() {
        CSSManager.applyCSS(this,"mystyle.css");
        vBox = new VBox();

/*
        vBox.setBackground(new Background(new BackgroundImage(
                Objects.requireNonNull(ImageManager.getImage("bg1.jpg")),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));
*/
        Label lbOurServices = new Label("Our Services");
        Label lbServiceDescription = new Label("    It is our privilege to be a part of the journy" +
                " and taling you where you need to be\n");
        //lbOurServices.setFont(new Font(30));
        //lbOurServices.set
        lbOurServices.setStyle("-fx-text-fill: #335ebe; -fx-font-size: 24px; -fx-font-weight: 700");
        lbServiceDescription.setStyle("-fx-text-fill: grey; -fx-font-size: 15px;");

        lbOurServices.setTextAlignment(TextAlignment.CENTER);
        VBox vbFirst = new VBox(new Separator(), lbOurServices,lbServiceDescription, new Separator());
        vbFirst.setAlignment(Pos.CENTER);

        Image imgFirst = ImageManager.getImage("cars/Audi_A3.png");
        ImageView img1 = new ImageView(imgFirst);
        Image imgSecond = ImageManager.getImage("cars/Renault_Space.png");
        ImageView img2 = new ImageView(imgSecond);
        Image imgThird = ImageManager.getImage("cars/Seat_Ibiza.png");
        ImageView img3 = new ImageView(imgThird);
        Image imgFourth = ImageManager.getImage("cars/Tesla_Y.png");
        ImageView img4 = new ImageView(imgFourth);

        String cssBordering = "-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 10px;";

        img1.setFitHeight(100);
        img1.setPreserveRatio(true);
        img2.setFitHeight(100);
        img2.setPreserveRatio(true);
        img3.setFitHeight(100);
        img3.setPreserveRatio(true);
        img4.setFitHeight(100);
        img4.setPreserveRatio(true);
        img1.setStyle(cssBordering);

        HBox hbSecond= new HBox(new Separator(), new Separator());
        hbSecond.getChildren().addAll(img1, img2, img3, img4);
        hbSecond.setSpacing(65);

        HBox hbThird = new HBox();
        HBox hbFourth = new HBox();
        VBox secondFrag = new VBox();
        HBox hbMiddleText = new HBox();

        VBox [] hbInfo = new VBox[4];
        Label [] lbTitles = new Label[4];
        Label [] lbBodys = new Label[4];

        lbTitles[0] = new Label("Rapid City Secure");
        lbTitles[1] =  new Label("Rapid City Transfer");
        lbTitles[2] =  new Label("Baggage Transport");
        lbTitles[3] = new Label("Privacy");

        lbBodys[0] = new Label("Driving in the city may be hard to navigate, let \nus help you" +
                "to get from A to B safely");
        lbBodys[1] = new Label("Driving in the city may be hard to navigate, let \nus help you" +
                "to get from A to B safely");
        lbBodys[2] = new Label("Driving in the city may be hard to navigate, let \nus help you" +
                "to get from A to B safely");
        lbBodys[3] = new Label("Complete privacy, our clients will be always\n protected and can " +
                "count on our full\n description");

        for(int i = 0; i < hbInfo.length; i++){
            hbInfo[i] = new VBox();
            hbInfo[i].getChildren().addAll(lbTitles[i],lbBodys[i]);
            hbInfo[i].setAlignment(Pos.CENTER);
            lbTitles[i].setStyle("-fx-font-size: 18px");
            lbBodys[i].setStyle("-fx-font-size: 12px");
        }

        hbFourth.getChildren().addAll(hbInfo);
        hbFourth.setSpacing(35);
        hbFourth.setAlignment(Pos.CENTER);

        btnBook = new Button("Book Now");
        btnBook.setStyle("-fx-background-color: #749f65;");
        btnBook.setFont(new Font(18));
        btnBook.setPrefSize(120,40);
        secondFrag.getChildren().add(btnBook);
        secondFrag.setAlignment(Pos.CENTER);
        btnBook.setId("btnBook");


        Label lbMiddleOne = new Label("\"Save your time with this incredible solution\"\t\t\t\t\t");
        Label lbMiddleSecond = new Label("\"The best way to get wherever you are going\"");
        lbMiddleOne.setStyle("-fx-text-fill: grey; -fx-font-size: 21px;");
        lbMiddleSecond.setStyle("-fx-text-fill: grey; -fx-font-size: 21px;");
        hbMiddleText.setAlignment(Pos.CENTER);
        hbMiddleText.getChildren().addAll(lbMiddleOne,lbMiddleSecond);

        vBox.setSpacing(50);
        vBox.getChildren().addAll(vbFirst, hbSecond,secondFrag,hbThird,hbMiddleText,hbFourth);
        /*
        vBox.setBackground(new Background(new BackgroundImage(
                Objects.requireNonNull(ImageManager.getImage("bg1.jpg")),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));*/
        this.setCenter(vBox);
    }

    private void registerHandler() {

        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });

        btnBook.setOnAction(actionEvent -> {
            crpcManager.goBooking();
            crpcManager.setMenuOpt(null);
        });
    }
    private void configAdapter() {
    }
    private void update() {


            if (crpcManager.getState() == States.MAIN_MENU) {

                configAdapter();
                this.setVisible(true);
                return;
            }else{
                this.setVisible(false);
            }
}
}