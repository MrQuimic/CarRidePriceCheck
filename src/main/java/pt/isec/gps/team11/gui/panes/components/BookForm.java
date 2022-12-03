package pt.isec.gps.team11.gui.panes.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.resources.CSSManager;
import pt.isec.gps.team11.gui.resources.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

public class BookForm extends BorderPane {
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    VBox vbAdressesAndOptions, vbAdresses, vbOptions, vbAdressesWithTitle, vbOptionsWithTitle;


    VBox vbStartAdress, vbEndAdress, vbDirections, vbExtraWaitTime, vbPassengers, vbSuitcases, vbDepartureDate, vbDepartureTime, vbTolls, vbDepartureTimeImg;
    Label lbStartAdress, lbEndAdress, lbDirections, lbExtraWaitTime, lbPassengers, lbSuitcases, lbDepartureDate, lbDepartureTime, lbTolls, lbAdressesTitle, lbOptionsTitle;
    HBox hbPassengersSuitcases, hbDepartureDateAndImage, hbDepartureTimeAndImage, submitBtns;
    Button btnSubmit, btnReset;
    TextField tfStartAdress, tfEndAdress, tfExtraWaitTime, tfDepartureTime;
    ChoiceBox cbDirections, cbPassengers, cbSuitcases, cbTolls;
    DatePicker dpDepartureDate;

    public BookForm(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        pcs = new PropertyChangeSupport(this);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        Font font = Font.font("Verdana", FontWeight.BOLD, 12);
        Font fontSmall = Font.font("Verdana", FontWeight.BOLD, 10);
        //VBox of the Addresses and the Options
        vbAdressesAndOptions = new VBox();
        vbAdressesAndOptions.setAlignment(Pos.CENTER_LEFT);

        //Adresses VBox With Title
        vbAdressesWithTitle = new VBox();
        vbAdressesWithTitle.setAlignment(Pos.CENTER);

        lbAdressesTitle = new Label("Adresses");
        lbAdressesTitle.setPadding(new Insets(20,0,5,0));
        lbAdressesTitle.setFont(font);
        //Adresses VBox
        vbAdresses = new VBox();
        vbAdresses.setAlignment(Pos.CENTER);

        //Start Adress
        vbStartAdress = new VBox();
        vbStartAdress.setAlignment(Pos.CENTER_LEFT);

        lbStartAdress = new Label("Start adress");
        lbStartAdress.setAlignment(Pos.CENTER_LEFT);
        lbStartAdress.setPadding(new Insets(10,0,5,0));
        lbStartAdress.setFont(fontSmall);

        tfStartAdress = new TextField();
        tfStartAdress.setPromptText("Introduce start address");


        vbStartAdress.getChildren().addAll(lbStartAdress,tfStartAdress);

        //End Adress
        vbEndAdress = new VBox();
        vbEndAdress.setAlignment(Pos.CENTER_LEFT);

        lbEndAdress = new Label("End adress");
        lbEndAdress.setAlignment(Pos.CENTER_LEFT);
        lbEndAdress.setPadding(new Insets(10,0,5,0));
        lbEndAdress.setFont(fontSmall);

        tfEndAdress = new TextField();
        tfEndAdress.setPromptText("Introduce end address");

        vbEndAdress.getChildren().addAll(lbEndAdress,tfEndAdress);

        tfStartAdress.setText("Coimbra, Portugal");
        tfEndAdress.setText("Porto, Portugal");

        vbAdresses.getChildren().addAll(vbStartAdress, vbEndAdress);

        vbAdressesWithTitle.getChildren().addAll(lbAdressesTitle,vbAdresses);



        //Options VBox With Title
        vbOptionsWithTitle = new VBox();
        vbOptionsWithTitle.setAlignment(Pos.CENTER);

        lbOptionsTitle = new Label("Options");
        lbOptionsTitle.setPadding(new Insets(20,0,5,0));
        lbOptionsTitle.setFont(font);
        //Options VBox
        vbOptions = new VBox();
        vbOptions.setAlignment(Pos.CENTER);

        //Directions
        vbDirections = new VBox();
        vbDirections.setAlignment(Pos.CENTER_LEFT);

        lbDirections = new Label("Directions");
        lbDirections.setPadding(new Insets(0,0,5,0));
        lbDirections.setFont(fontSmall);
        lbDirections.setAlignment(Pos.CENTER_LEFT);
        cbDirections = new ChoiceBox();
        cbDirections.getItems().addAll("One Way", "Return");
        cbDirections.setValue("One Way");

        vbDirections.getChildren().addAll(lbDirections,cbDirections);

        //Extra Waiting Time
        vbExtraWaitTime = new VBox();
        vbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);

        lbExtraWaitTime = new Label("Extra Waiting Time (minutes)");
        lbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);
        lbExtraWaitTime.setPadding(new Insets(0,0,5,0));
        lbExtraWaitTime.setFont(fontSmall);
        tfExtraWaitTime = new TextField();
        tfExtraWaitTime.setPromptText("0 minutes");

        vbExtraWaitTime.getChildren().addAll(lbExtraWaitTime,tfExtraWaitTime);

        //HBox for Passengers and Suitcases
        hbPassengersSuitcases = new HBox();
        hbPassengersSuitcases.setAlignment(Pos.CENTER_LEFT);

        //Passengers
        vbPassengers = new VBox();
        vbPassengers.setAlignment(Pos.CENTER);

        lbPassengers = new Label("Passengers");
        lbPassengers.setAlignment(Pos.CENTER_LEFT);
        lbPassengers.setPadding(new Insets(0,0,5,0));
        lbPassengers.setFont(fontSmall);
        cbPassengers = new ChoiceBox();
        cbPassengers.getItems().addAll("1", "2", "3", "4","5", "6", "7");
        cbPassengers.setValue("1");
        vbPassengers.getChildren().addAll(lbPassengers,cbPassengers);

        //Suitcases
        vbSuitcases = new VBox();
        vbSuitcases.setAlignment(Pos.CENTER);

        lbSuitcases = new Label("Suitcases");
        lbSuitcases.setAlignment(Pos.CENTER_RIGHT);
        lbSuitcases.setPadding(new Insets(0,0,5,0));
        lbSuitcases.setFont(fontSmall);
        cbSuitcases = new ChoiceBox();
        cbSuitcases.getItems().addAll("0", "1", "2", "3", "4","5", "6", "7");
        cbSuitcases.setValue("0");
        vbSuitcases.getChildren().addAll(lbSuitcases,cbSuitcases);

        hbPassengersSuitcases.getChildren().addAll(vbPassengers,vbSuitcases);
        hbPassengersSuitcases.setSpacing(10);

        //HBox for Departure Date and Image
        hbDepartureDateAndImage = new HBox();
        hbDepartureDateAndImage.setAlignment(Pos.CENTER_LEFT);

        //Departure Date
        vbDepartureDate = new VBox();
        vbDepartureDate.setAlignment(Pos.CENTER);

        lbDepartureDate = new Label("Departure date");
        lbDepartureDate.setAlignment(Pos.CENTER_LEFT);
        lbDepartureDate.setPadding(new Insets(0,0,5,0));
        lbDepartureDate.setFont(fontSmall);

        dpDepartureDate = new DatePicker();


        dpDepartureDate.setValue(LOCAL_DATE());

        vbDepartureDate.getChildren().addAll(lbDepartureDate,dpDepartureDate);

        //parte de colocar a imagem aqui


        hbDepartureDateAndImage.getChildren().addAll(vbDepartureDate/*,cena da imagem*/);

        //HBox for Departure Time and Image
        hbDepartureTimeAndImage = new HBox();
        hbDepartureTimeAndImage.setAlignment(Pos.CENTER_LEFT);

        //Departure Time
        vbDepartureTime = new VBox();
        vbDepartureTime.setAlignment(Pos.CENTER);

        lbDepartureTime = new Label("Departure Time");
        lbDepartureTime.setAlignment(Pos.CENTER_LEFT);
        lbDepartureTime.setPadding(new Insets(0,0,5,0));
        lbDepartureTime.setFont(fontSmall);
        tfDepartureTime = new TextField();
        tfDepartureTime.setPromptText("Time");

        vbDepartureTime.getChildren().addAll(lbDepartureTime,tfDepartureTime);

        Lighting lightingBlue = new Lighting(new Light.Distant(45, 90, Color.rgb(40,69,98)));
        ColorAdjust brightBlue = new ColorAdjust(1, 1, 1, 1);
        lightingBlue.setContentInput(brightBlue);
        lightingBlue.setSurfaceScale(0);
        Image img2 = ImageManager.getImage("icons\\icon_clock-o.png");

        //png use of images
        ImageView imgView2 = new ImageView(img2);
        imgView2.setId("calendar");

        imgView2.setEffect(lightingBlue);
        imgView2.setFitHeight(22);
        imgView2.setPreserveRatio(true);
        vbDepartureTimeImg= new VBox(imgView2);
        vbDepartureTimeImg.setAlignment(Pos.BOTTOM_CENTER);


        hbDepartureTimeAndImage.getChildren().addAll(vbDepartureTime,vbDepartureTimeImg);
        hbDepartureTimeAndImage.setSpacing(2);

        //Tolls
        vbTolls = new VBox();
        vbTolls.setAlignment(Pos.CENTER);

        lbTolls = new Label("Tolls:");
        lbTolls.setAlignment(Pos.CENTER_LEFT);
        cbTolls = new ChoiceBox();
        cbTolls.getItems().addAll("Yes","No");

        vbOptions.getChildren().addAll(vbDirections,vbExtraWaitTime,hbPassengersSuitcases,hbDepartureDateAndImage,hbDepartureTimeAndImage,vbTolls);
        vbOptions.setSpacing(15);

        vbOptionsWithTitle.getChildren().addAll(lbOptionsTitle,vbOptions);

        submitBtns= new HBox();
        btnSubmit = new Button("Submit");
        btnReset = new Button("Reset");
        btnSubmit.setId("mbtnSubmit");
        btnReset.setId("mbtnReset");
        submitBtns.getChildren().addAll(btnReset, btnSubmit);
        submitBtns.setPadding(new Insets(10,0,0,40));
        submitBtns.setSpacing(10);

        vbAdressesAndOptions.getChildren().addAll(vbAdressesWithTitle,vbOptionsWithTitle, submitBtns);



        this.setTop(vbAdressesAndOptions);
    }
    public static final LocalDate LOCAL_DATE (){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());

        String dataString = formatter.format(date);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dataString, formatter2);

        return localDate;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private void registerHandlers() {

        btnReset.setOnAction(actionEvent -> {

            crpcManager.setMenuOpt(MenuOpt.MAIN_MENU);

        });

        btnSubmit.setOnAction(actionEvent -> {
            int extraWaitTime;
            Date departureTime = new Date();
            Date departureDate = new Date();
            boolean directions;
            int nrPassengers = 1;
            int nrSuitcases = 0;
            boolean tolls;
            boolean flag = true;

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date systemDate = new Date();


            if(tfExtraWaitTime.getText().isBlank()) {
                extraWaitTime = 0;
            } else {
                extraWaitTime = Integer.parseInt(tfExtraWaitTime.getText());
            }

            if(tfDepartureTime.getText().isBlank() /*|| formatter.format(tfDepartureTime.getText()).isBefore(formatter.format(systemDate))*/) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("The departure time is invalid");
                alert.showAndWait();
                flag = false;
                return;
            }
            //departureTime = tfDepartureTime.getValue();

            /*if(dpDepartureDate.getValue().isBefore( (ChronoLocalDate) systemDate)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("The departure time is invalid");
                alert.showAndWait();
                flag = false;
                return;
            }*/
            //departureDate = dpDepartureDate.getValue();

            if(cbDirections.getValue().equals("One Way")) {
                directions = true;
            }else
                directions = false;//return trip

            if(cbPassengers.getValue() != null) {
                String s = cbPassengers.getValue().toString();
                nrPassengers = Integer.parseInt(s);
            }

            if(cbSuitcases.getValue() != null) {
                String s = cbSuitcases.getValue().toString();
                nrSuitcases = Integer.parseInt(s);
            }

            if(cbTolls.getValue().equals("Yes"))
                tolls = true;
            else
                tolls = false;
/*
            if(flag) {
                crpcManager.book(directions,departureDate,extraWaitTime,nrSuitcases, nrPassengers,departureTime,tolls);
            }*/

            crpcManager.setMenuOpt(MenuOpt.MAIN_MENU);

        });





        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void update() {

    }

}