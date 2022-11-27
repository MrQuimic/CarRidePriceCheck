package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pt.isec.gps.team11.gui.panes.utils.MenuTop;
import pt.isec.gps.team11.gui.resources.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;


/**
 * The type Root pane.
 */
public class RootPane extends BorderPane {
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
     * The Predef table width.
     */
    int predefTableWidth = 1050;

    /**
     * The Canvas pane.
     */
    ScrollPane canvasPane;




    public RootPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        createViews();
        registerHandler();
        update();

    }

    private void createViews() {
        vBox = new VBox();
//        CSSManager.applyCSS(this,"styles.css");
//        CSSManager.applyCSS(this,"tableViewBase.css");

        MenuTop bp = new MenuTop(crpcManager);


        vBox.setBackground(new Background(new BackgroundImage(
                ImageManager.getImage("bg1.jpg"),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));


        canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTCORAL.brighter());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFont(new Font("Times New Roman", 24));
        gc.setFill(Color.WHITE);
        gc.fillText("Advanced Programming", 50, 50);
        gc.setStroke(Color.BLACK);
        gc.strokeText("Advanced Programming", 50, 100);
        gc.fillText("Advanced Programming", 50, 150);
        gc.strokeText("Advanced Programming", 50, 100);

        gc.drawImage(ImageManager.getImage("logoCRPC.png"),150,150,200,100);

        StackPane stackPane;
        stackPane = new StackPane(

        );

        stackPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));





        HBox hBox = new HBox(bp, stackPane);

        vBox.getChildren().addAll(hBox, canvas);


        this.setCenter(vBox);
    }

    private void registerHandler() {


    }

    private void update() {

/*        switch (crpcManager.getState()){

            case MAIN_MENU:
            case LIST_TRIPS:
            case BOOKING:
            case CHOOSE_CAR:
            case CONFIRM_BOOKING:
            case TRIP_DETAILS:
            default:
                break;
        }*/

    }

}