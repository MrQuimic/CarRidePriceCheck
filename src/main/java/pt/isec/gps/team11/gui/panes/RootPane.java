package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.MenuTop;
import pt.isec.gps.team11.gui.resources.CSSManager;
import pt.isec.gps.team11.gui.resources.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;

import java.util.Objects;


/**
 * The type Root pane.
 */
public class RootPane extends BorderPane {
    CRPCManager crpcManager;
    MenuOpt menuOpt;
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
        CSSManager.applyCSS(this,"styles.css");
        vBox = new VBox();


        MenuTop bp = new MenuTop(crpcManager);


        StackPane stackPane;
        stackPane = new StackPane(
                new CreditsPane(crpcManager),
                new BookingPane(crpcManager),
                new MainPagePane(crpcManager),
                new StartBookingPane(crpcManager)
        );

        stackPane.setBackground(new Background(new BackgroundImage(
                Objects.requireNonNull(ImageManager.getImage("bg1.jpg")),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));

        //stackPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));


        HBox hBox = new HBox(bp);


        vBox.getChildren().addAll(hBox, stackPane);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        stackPane.setAlignment(Pos.TOP_CENTER);

        scrollPane.setPannable(true);
        scrollPane.setId("scrollPaneMain");
        this.setCenter(scrollPane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private void registerHandler() {


    }

    private void update() {



    }

}