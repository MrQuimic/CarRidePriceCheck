package pt.isec.gps.team11.gui.panes.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.resources.CSSManager;
import pt.isec.gps.team11.gui.resources.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.IStates;
import pt.isec.gps.team11.model.fsm.States;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuTop extends BorderPane {
    private CRPCManager manager;
    private Button btnHome, btnBooking, btnOurTeam, btnContacts, btnAboutUs, mSave;
    private HBox hBox, hBoxLogo;
    Text tMainMenu;
    MenuOpt menuOpt;
    PropertyChangeSupport pcs;

    public MenuTop(CRPCManager manager){
        this.manager = manager;
        pcs = new PropertyChangeSupport(this);
        createViews();
        registerHandlers();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    private void registerHandlers() {
        btnHome.setOnAction(actionEvent -> {

            manager.setMenuOpt(MenuOpt.MAIN_MENU);

        });

        btnBooking.setOnAction(actionEvent -> {

            manager.setMenuOpt(MenuOpt.BOOKING);

        });

        btnOurTeam.setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.CREDITS);
        });

        btnContacts.setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.STARTBOOKING);
        });

        btnAboutUs.setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.CREDITS);

        });
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        btnHome = new Button("Home");
        btnBooking = new Button("Booking");
        btnOurTeam = new Button("Our Team");
        btnContacts = new Button("StartBooking/Contacts");
        btnAboutUs = new Button("About us");
        btnAboutUs.setId("button");

        mSave = new Button("Save");
        mSave.setId("mSave");
        mSave.setMinWidth(55);


        //style lighting blue

        Lighting lighting = new Lighting(new Light.Distant(45, 90, Color.rgb(51,102,153)));
        ColorAdjust bright = new ColorAdjust(1, 1, 1, 1);
        lighting.setContentInput(bright);
        lighting.setSurfaceScale(0);

        //style lighting blue

        Lighting lightingBlue = new Lighting(new Light.Distant(45, 90, Color.rgb(51,102,153)));
        ColorAdjust brightBlue = new ColorAdjust(1, 1, 1, 1);
        lightingBlue.setContentInput(brightBlue);
        lightingBlue.setSurfaceScale(0);

        //style lighting Black

        Lighting lightingBlack = new Lighting(new Light.Distant(45, 90, Color.rgb(0,0,0)));
        ColorAdjust brightBlack = new ColorAdjust(1, 1, 1, 1);
        lightingBlack.setContentInput(brightBlack);
        lightingBlack.setSurfaceScale(0);

        //style lighting gray

        Lighting lightingGray = new Lighting(new Light.Distant(45, 90, Color.rgb(99,115,121)));
        ColorAdjust brightGray = new ColorAdjust(1, 1, 1, 1);
        lightingGray.setContentInput(brightGray);
        lightingGray.setSurfaceScale(0);

        Image img = ImageManager.getImage("logoNoBkg.png");
        ImageView imgView = new ImageView(img);
        //imgView.setEffect(lightingBlack);
        imgView.setFitHeight(100);
        imgView.setPreserveRatio(true);


        Image img2 = ImageManager.getImage("icons\\icon_clock-o.png");

        //png use of images
        ImageView imgView2 = new ImageView(img2);
        imgView2.setId("calendar");

        imgView2.setEffect(lightingBlue);
        imgView2.setFitHeight(30);
        imgView2.setPreserveRatio(true);


        tMainMenu = new Text();
        tMainMenu.setText("⧉Top Menu");

        tMainMenu.setFont(new Font("TimesRoman", 20));

        hBoxLogo = new HBox(imgView, imgView2, tMainMenu);
        hBoxLogo.setSpacing(40.0);
        hBox = new HBox(hBoxLogo, btnHome, btnBooking,
                                        btnOurTeam, btnContacts, btnAboutUs,imgView2, mSave);

        hBox.setSpacing(5.0);
        hBox.setHgrow(this, Priority.ALWAYS);
        hBox.setAlignment(Pos.TOP_LEFT);

        this.setCenter(hBox);

    }
}
