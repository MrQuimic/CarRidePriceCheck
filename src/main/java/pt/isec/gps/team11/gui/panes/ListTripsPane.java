package pt.isec.gps.team11.gui.panes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;

public class ListTripsPane extends BorderPane {
    private CRPCManager crpcManager;
    private Button btnBooking;

    public ListTripsPane(CRPCManager crpcManager) {
        this.crpcManager = crpcManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Label lbOurServices = new Label("Our Services");
        VBox vbFirst = new VBox(new Separator(), lbOurServices, new Separator());

        Image imgFirst = ImageManager.getImage("cars/Audi_A3.png");
        Image imgSecond = ImageManager.getImage("cars/Renault_Space.png");
        Image imgThird = ImageManager.getImage("cars/Seat_Ibiza.png");
        Image imgFourth = ImageManager.getImage("cars/Tesla_Y.png");


    }

    private void registerHandlers() {

    }

    private void update() {

        if (crpcManager.getMenuOpt() == MenuOpt.IN_STATE && crpcManager.getState() == States.BOOKING) {

            this.setVisible(crpcManager != null && crpcManager.getMenuOpt() == MenuOpt.IN_STATE && crpcManager.getState() == States.MAIN_MENU);
            this.setVisible(true);
            return;
        }
        this.setVisible(false);


    }
}
