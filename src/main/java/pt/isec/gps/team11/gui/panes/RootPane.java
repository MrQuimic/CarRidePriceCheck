package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.panes.components.MenuTop;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.model.CRPCManager;




/**
 * The type Root pane.
 */
public class RootPane extends BorderPane {
    CRPCManager crpcManager;

    /**
     * The Btns.
     */
    String username;

    VBox vBox;


    public RootPane(CRPCManager crpcManager) {
        this.crpcManager = crpcManager;

        createViews();
        registerHandler();
        update();

    }

    private void createViews() {
        CSSManager.applyCSS(this, "styles.css");
        vBox = new VBox();


        MenuTop bp = new MenuTop(crpcManager, username);
        MyBrowser myBrowser = new MyBrowser(crpcManager);

        StackPane stackPane;
        stackPane = new StackPane(
                new Contacts(crpcManager),
                new AboutUs(crpcManager),
                new BookingPane(crpcManager, myBrowser),
                new MainPagePane(crpcManager),
                new AuthenticationPane(crpcManager),
                new ConfirmPane(crpcManager, myBrowser),
                new ChooseCarPane(crpcManager),
                new BookingListPane(crpcManager)
        );

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