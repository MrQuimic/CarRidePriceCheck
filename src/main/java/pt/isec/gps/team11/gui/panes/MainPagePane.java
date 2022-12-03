package pt.isec.gps.team11.gui.panes;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;

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


        vBox.setBackground(new Background(new BackgroundImage(
                Objects.requireNonNull(ImageManager.getImage("bg1.jpg")),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));





        Label lbOurServices = new Label("Our Services");
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
        vBox.getChildren().addAll(vbFirst, hbSecond);
        vBox.setBackground(new Background(new BackgroundImage(
                Objects.requireNonNull(ImageManager.getImage("bg1.jpg")),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));

        this.setCenter(vBox);
    }

    private void registerHandler() {

        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }
    private void configAdapter() {
    }
    private void update() {


            if (crpcManager.getMenuOpt() == MenuOpt.MAIN_MENU) {

                configAdapter();
                this.setVisible(true);
                return;
            }else{
                this.setVisible(false);
            }
}
}