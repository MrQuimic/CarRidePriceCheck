package pt.isec.gps.team11.gui.panes.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuTop extends BorderPane {
    private CRPCManager manager;

    Button[] btnsMenu;
    Button logout;
    private HBox hBox, hBoxLogo,hBoxMenuTop, hBoxTextTop, hBoxAuth;
    VBox vBox;
    Text tMainMenu;
    MenuOpt menuOpt;
    ImageView authIcon;
    PropertyChangeSupport pcs;
    Text usernameDisplay;
    String username;

    public MenuTop(CRPCManager manager, String username){
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
        hBoxAuth= new HBox();
        hBoxAuth.setId("hBoxMenuTop");
        btnsMenu = new Button[5];

        btnsMenu[0] = new Button(String.format("\uD83C\uDFE0 Home"));
        btnsMenu[1] = new Button(String.format("\uD83D\uDCD5 Booking"));
        btnsMenu[2] = new Button(String.format("\uD83D\uDCDE Contacts"));
        btnsMenu[3] = new Button(String.format("✨ About us"));
        btnsMenu[4] = new Button(String.format("\uD83D\uDC65 Our Team"));
        logout = new Button(String.format("⏏ Logout"));
        logout.setId("logoutbtn");
        logout.setVisible(false);

        for(int i = 0; i < 5; i++){
            btnsMenu[i].setPrefSize(110,40);
            btnsMenu[i].setId("MenuUIBtn");
            // btns[i].setStyle("");
        }

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

        hBoxAuth.getChildren().addAll(authIcon, logout);



        tMainMenu = new Text();
        tMainMenu.setText("⧉Top Menu: ");
        tMainMenu.setId("LabelMenuTop");
        tMainMenu.setFont(new Font("TimesRoman", 20));
        hBoxTextTop.getChildren().addAll(tMainMenu);
        hBoxMenuTop.getChildren().addAll(btnsMenu);

        hBoxLogo.getChildren().addAll(imgView);
        hBoxLogo.setSpacing(5.0);
        //hBoxLogo.setPadding(new Insets(0,0,0,30));
        hBoxLogo.setPadding(new Insets(0,50,0,0));
        hBox = new HBox(hBoxLogo, hBoxTextTop, hBoxMenuTop, hBoxAuth);

        hBox.setSpacing(5.0);
        hBox.setHgrow(this, Priority.ALWAYS);

        hBox.setId("hBoxMenuT");
        this.setCenter(hBox);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private void registerHandlers() {
        btnsMenu[0].setOnAction(actionEvent -> {

            manager.setMenuOpt(MenuOpt.MAIN_MENU);

        });

        btnsMenu[1].setOnAction(actionEvent -> {

            manager.setMenuOpt(MenuOpt.BOOKING);

        });

        btnsMenu[2].setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.CONTACTS);
        });

        btnsMenu[3].setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.ABOUTUS);

        });

        btnsMenu[4].setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.OURTEAM);

        });

        logout.setOnAction(actionEvent -> {
            manager.setMenuOpt(MenuOpt.LOGOUT);
            manager.logout();

        });

        authIcon.setOnMouseClicked(actionEvent -> {
            manager.setMenuOpt(MenuOpt.AUTHENTICATION);

        });

        manager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void update(){
        logout.setText("⏏Logout: \n" + manager.getUsername());
        if(manager.isLogged()){
            authIcon.setVisible(false);
            logout.setVisible(true);
        }
        if(manager.getMenuOpt()==MenuOpt.MAIN_MENU) {
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
        }
        else if(manager.getMenuOpt()==MenuOpt.BOOKING){
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
        }
        else if(manager.getMenuOpt()==MenuOpt.CONTACTS){
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
        }

        else if(manager.getMenuOpt()==MenuOpt.ABOUTUS){
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
            btnsMenu[4].setStyle("-fx-font-weight: 600");
            logout.setStyle("-fx-font-weight: 600");
        }

        else if(manager.getMenuOpt()==MenuOpt.OURTEAM){
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
            btnsMenu[3].setStyle("-fx-font-weight: 600");
            btnsMenu[4].setStyle("-fx-font-weight: 700");
            logout.setStyle("-fx-font-weight: 600");
        }

        else if(manager.getMenuOpt()==MenuOpt.LOGOUT){
            btnsMenu[0].setOpacity(1);
            btnsMenu[1].setOpacity(1);
            btnsMenu[2].setOpacity(1);
            btnsMenu[3].setOpacity(1);
            btnsMenu[4].setOpacity(1);
            logout.setOpacity(0.8);
            authIcon.setOpacity(0.65);
            btnsMenu[0].setStyle("-fx-font-weight: 600");
            btnsMenu[1].setStyle("-fx-font-weight: 600");
            btnsMenu[2].setStyle("-fx-font-weight: 600");
            btnsMenu[3].setStyle("-fx-font-weight: 600");
            btnsMenu[4].setStyle("-fx-font-weight: 600");
            logout.setStyle("-fx-font-weight: 700");
        }
    }
}
