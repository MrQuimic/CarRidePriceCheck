package pt.isec.gps.team11.gui.panes.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import pt.isec.gps.team11.gui.resources.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;

public class MenuTop extends BorderPane {
    private CRPCManager manager;
    private Button btnHome;
    private Button btnBooking;
    private Button btnOurTeam;
    private Button btnContacts;
    private Button btnAboutUs;
    private HBox hBox;


    public MenuTop(CRPCManager manager){
        this.manager = manager;
        
        createViews();
        registerHandlers();
    }

    private void registerHandlers() {
        btnHome.setOnAction(actionEvent -> {

        });

        btnBooking.setOnAction(actionEvent -> {

        });

        btnOurTeam.setOnAction(actionEvent -> {

        });

        btnContacts.setOnAction(actionEvent -> {

        });

        btnAboutUs.setOnAction(actionEvent -> {

        });
    }

    private void createViews() {
        btnHome = new Button("Home");
        btnBooking = new Button("Booking");
        btnOurTeam = new Button("Our Team");
        btnContacts = new Button("Contacts");
        btnAboutUs = new Button("About us");

        Image img = ImageManager.getImage("logoCRPC.png");
        ImageView imgView = new ImageView(img);

        hBox = new HBox(imgView, new Label("TopMenu"), btnHome, btnBooking,
                                        btnOurTeam, btnContacts, btnAboutUs);

        hBox.setSpacing(2.0);
        hBox.setHgrow(this, Priority.ALWAYS);
        hBox.setAlignment(Pos.TOP_CENTER);

        this.setCenter(hBox);
    }
}
