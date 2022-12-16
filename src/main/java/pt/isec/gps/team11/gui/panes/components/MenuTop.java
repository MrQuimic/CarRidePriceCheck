package pt.isec.gps.team11.gui.panes.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuTop extends BorderPane {
    private CRPCManager manager;

    Button[] btnsMenu;
    Button logout, logUser;
    private HBox hBox, hBoxLogo, hBoxMenuTop, hBoxTextTop, hBoxAuth, hBoxMenuT;
    VBox vBox;
    Text tMainMenu, usernameDisplay;
    MenuOpt menuOpt;
    ImageView authIcon;
    PropertyChangeSupport pcs;

    String username;

    public MenuTop(CRPCManager manager, String username) {
        this.manager = manager;
        //this.username = username;
        pcs = new PropertyChangeSupport(this);
        createViews();
        registerHandlers();
        update();

    }


    private void createViews() {
        //CSSManager.applyCSS(this,"mystyle.css");
        usernameDisplay = new Text("");
        vBox = new VBox();
        vBox.setId("vBox");
        hBoxLogo = new HBox();
        hBoxLogo.setId("hBoxLogoTop");
        hBoxTextTop = new HBox();
        hBoxTextTop.setId("hBoxTextTop");
        hBoxMenuTop = new HBox();
        hBoxMenuTop.setId("hBoxMenuTop");
        hBoxAuth = new HBox();
        hBoxAuth.setId("hBoxMenuTop");
        btnsMenu = new Button[5];

        btnsMenu[0] = new Button(String.format("\uD83C\uDFE0 Home"));
        btnsMenu[1] = new Button(String.format("\uD83D\uDCD5 Booking"));
        btnsMenu[2] = new Button(String.format("\uD83D\uDCCB Contacts"));
        btnsMenu[3] = new Button(String.format("\uD83D\uDC65 About Us"));
        btnsMenu[4] = new Button(String.format("\uD83D\uDCCB Orders"));


        logout = new Button(String.format("Logout"));
        logout.setId("logoutbtn");
        logout.setVisible(false);

        logout.setPrefSize(80, 40);
        logUser = new Button("");
        logUser.setId("logUserbtn");
        logUser.setVisible(false);
        logUser.setPrefSize(95, 40);

        btnsMenu[4].setVisible(logUser.getText().equals("admin@gps"));

        for (int i = 0; i < 5; i++) {
            btnsMenu[i].setPrefSize(100, 40);
            btnsMenu[i].setId("MenuUIBtn");
            // btns[i].setStyle("");
        }

        //style lighting blue

        Lighting lightingBlue = new Lighting(new Light.Distant(45, 90, Color.rgb(51, 102, 153)));
        ColorAdjust brightBlue = new ColorAdjust(1, 1, 1, 1);
        lightingBlue.setContentInput(brightBlue);
        lightingBlue.setSurfaceScale(0);


        //style lighting Black

        Lighting lightingBlack = new Lighting(new Light.Distant(45, 90, Color.rgb(0, 0, 0)));
        ColorAdjust brightBlack = new ColorAdjust(1, 1, 1, 1);
        lightingBlack.setContentInput(brightBlack);
        lightingBlack.setSurfaceScale(0);

        //style lighting gray

        Lighting lightingGray = new Lighting(new Light.Distant(45, 90, Color.rgb(99, 115, 121)));
        ColorAdjust brightGray = new ColorAdjust(1, 1, 1, 1);
        lightingGray.setContentInput(brightGray);
        lightingGray.setSurfaceScale(0);

        Image img = ImageManager.getImage("logoNoBkgHalf.png");
        ImageView imgView = new ImageView(img);
        //imgView.setEffect(lightingBlack);
        imgView.setFitHeight(80);
        imgView.setPreserveRatio(true);
        imgView.setId("logoMenuTop");
        imgView.setStyle("-fx-padding: 0 0 0 10;");


        Image authIconimg = ImageManager.getImage("icons\\icon_user.png");

        //png use of images
        authIcon = new ImageView(authIconimg);
        authIcon.setId("authIcon");
        authIcon.setFitHeight(30);
        authIcon.setPreserveRatio(true);

        hBoxAuth.getChildren().addAll(authIcon, logout, logUser);


        tMainMenu = new Text();
        tMainMenu.setText("⧉Top Menu: ");
        tMainMenu.setId("LabelMenuTop");
        tMainMenu.setFont(new Font("TimesRoman", 20));
        hBoxTextTop.getChildren().addAll(tMainMenu);
        hBoxMenuTop.getChildren().addAll(btnsMenu);

        hBoxLogo.getChildren().addAll(imgView);
        hBoxLogo.setSpacing(5.0);
        //hBoxLogo.setPadding(new Insets(0,0,0,30));
        hBoxLogo.setPadding(new Insets(0, 0, 0, 0));

        AnchorPane anchorPane = new AnchorPane();
        // List should stretch as anchorPane is resized
        AnchorPane.setTopAnchor(hBoxLogo, 10.0);
        AnchorPane.setLeftAnchor(hBoxLogo, 10.0);
        hBoxMenuT = new HBox(hBoxTextTop, hBoxMenuTop, hBoxAuth);
        hBoxMenuT.setSpacing(5.0);
        hBoxMenuT.setHgrow(this, Priority.ALWAYS);

        this.setLeft(hBoxLogo);
        hBoxMenuT.setId("hBoxMenuT");

        hBoxLogo.setAlignment(Pos.CENTER);
        hBoxMenuT.setAlignment(Pos.CENTER);

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(hBoxLogo, hBoxMenuT);
        splitPane.setDividerPositions(0.25f, 0.75f); //Important for zoom


        splitPane.getDividers().get(0).positionProperty().addListener((observable, oldValue, newValue) -> {
            splitPane.setDividerPositions(0.25f, 0.75f);
        });
        splitPane.setId("splitPaneTop");

        this.setCenter(splitPane);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private void registerHandlers() {
        btnsMenu[0].setOnAction(actionEvent -> {
            manager.goMainMenu();
            manager.setMenuOpt(null);
        });

        btnsMenu[1].setOnAction(actionEvent -> {
            manager.goBooking();
            manager.setMenuOpt(null);
        });

        btnsMenu[2].setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.CONTACTS);
            manager.goIdle();
        });

        btnsMenu[3].setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.ABOUTUS);
            manager.goIdle();
        });


        btnsMenu[4].setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.BOOKINGLIST);
            manager.goIdle();
        });

        logout.setOnAction(actionEvent -> {
            manager.logout();
            manager.goMainMenu();
        });

        authIcon.setOnMouseClicked(actionEvent -> {
            manager.setMenuOpt(MenuOpt.AUTHENTICATION);
            manager.goIdle();
        });

        manager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void update() {

        if (manager.isLogged()) {
            authIcon.setVisible(false);
            logout.setVisible(true);
            logUser.setVisible(true);
            logout.setText("Logout: \n");
            logUser.setText(manager.getUsername() + " \n");
            btnsMenu[4].setVisible(logUser.getText().equals("admin@gps" + " \n"));
        } else {
            authIcon.setVisible(true);
            logout.setVisible(false);
            logUser.setVisible(false);
            btnsMenu[4].setVisible(false);
        }
        if (manager.getState() == States.MAIN_MENU) {
            btnsMenu[0].setOpacity(0.8);
            btnsMenu[1].setOpacity(1);
            btnsMenu[2].setOpacity(1);
            btnsMenu[3].setOpacity(1);
            btnsMenu[4].setOpacity(1);
            logout.setOpacity(1);
            authIcon.setOpacity(1);
            btnsMenu[0].setStyle("-fx-font-weight: 700");
            btnsMenu[1].setStyle("-fx-font-weight: 600");
            btnsMenu[2].setStyle("-fx-font-weight: 600");
            btnsMenu[3].setStyle("-fx-font-weight: 600");
            btnsMenu[4].setStyle("-fx-font-weight: 600");
            logout.setStyle("-fx-font-weight: 600");
        } else if (manager.getState() == States.BOOKING) {
            btnsMenu[0].setOpacity(1);
            btnsMenu[1].setOpacity(0.8);
            btnsMenu[2].setOpacity(1);
            btnsMenu[3].setOpacity(1);
            btnsMenu[4].setOpacity(1);
            logout.setOpacity(1);
            authIcon.setOpacity(1);
            btnsMenu[0].setStyle("-fx-font-weight: 600");
            btnsMenu[1].setStyle("-fx-font-weight: 700");
            btnsMenu[2].setStyle("-fx-font-weight: 600");
            btnsMenu[3].setStyle("-fx-font-weight: 600");
            btnsMenu[4].setStyle("-fx-font-weight: 600");
            logout.setStyle("-fx-font-weight: 600");
        } else if (manager.getMenuOpt() == MenuOpt.CONTACTS) {
            btnsMenu[0].setOpacity(1);
            btnsMenu[1].setOpacity(1);
            btnsMenu[2].setOpacity(0.8);
            btnsMenu[3].setOpacity(1);
            btnsMenu[4].setOpacity(1);
            logout.setOpacity(1);
            authIcon.setOpacity(1);
            btnsMenu[0].setStyle("-fx-font-weight: 600");
            btnsMenu[1].setStyle("-fx-font-weight: 600");
            btnsMenu[2].setStyle("-fx-font-weight: 700");
            btnsMenu[3].setStyle("-fx-font-weight: 600");
            btnsMenu[4].setStyle("-fx-font-weight: 600");
            logout.setStyle("-fx-font-weight: 600");
        } else if (manager.getMenuOpt() == MenuOpt.ABOUTUS) {
            btnsMenu[0].setOpacity(1);
            btnsMenu[1].setOpacity(1);
            btnsMenu[2].setOpacity(1);
            btnsMenu[3].setOpacity(0.8);
            btnsMenu[4].setOpacity(1);
            logout.setOpacity(1);

            authIcon.setOpacity(1);
            btnsMenu[0].setStyle("-fx-font-weight: 600");
            btnsMenu[1].setStyle("-fx-font-weight: 600");
            btnsMenu[2].setStyle("-fx-font-weight: 600");
            btnsMenu[3].setStyle("-fx-font-weight: 700");
            logout.setStyle("-fx-font-weight: 600");
        } else if (manager.getMenuOpt() == MenuOpt.BOOKINGLIST) {
            btnsMenu[0].setOpacity(1);
            btnsMenu[1].setOpacity(1);
            btnsMenu[2].setOpacity(1);
            btnsMenu[3].setOpacity(1);
            btnsMenu[4].setOpacity(0.8);
            logout.setOpacity(1);

            authIcon.setOpacity(1);
            btnsMenu[0].setStyle("-fx-font-weight: 600");
            btnsMenu[1].setStyle("-fx-font-weight: 600");
            btnsMenu[2].setStyle("-fx-font-weight: 600");
            btnsMenu[3].setStyle("-fx-font-weight: 700");
            logout.setStyle("-fx-font-weight: 600");
        } else if (manager.getMenuOpt() == MenuOpt.LOGOUT) {
            btnsMenu[0].setOpacity(1);
            btnsMenu[1].setOpacity(1);
            btnsMenu[2].setOpacity(1);
            btnsMenu[3].setOpacity(1);
            logout.setOpacity(0.8);
            authIcon.setOpacity(0.65);
            btnsMenu[0].setStyle("-fx-font-weight: 600");
            btnsMenu[1].setStyle("-fx-font-weight: 600");
            btnsMenu[2].setStyle("-fx-font-weight: 600");
            btnsMenu[3].setStyle("-fx-font-weight: 600");
            logout.setStyle("-fx-font-weight: 700");
        }
    }
}
