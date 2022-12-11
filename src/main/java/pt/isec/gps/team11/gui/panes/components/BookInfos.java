package pt.isec.gps.team11.gui.panes.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class BookInfos extends BorderPane {
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    VBox vbAdressesAndOptions, vbAdresses, vbOptions, vbAdressesWithTitle, vbOptionsWithTitle;

    String returnGoogleStr = "";
    VBox vbStartAdress, vbEndAdress, vbDirections, vbExtraWaitTime, vbPassengers, vbSuitcases, vbDepartureDate, vbDepartureTime, vbTolls;
    Label lbStartAdress,  tfExtraWaitTime, tfDepartureTime, lbEndAdress, lbDirections, lbExtraWaitTime, lbPassengers, lbSuitcases, lbDepartureDate, lbDepartureTime, lbTolls, lbAdressesTitle, lbOptionsTitle, lbInfoLabel;
    HBox hbPassengersSuitcases, hbDepartureDateAndImage, hbDepartureTimeAndImage, submitBtns;
    Button btnConfirm, btnReset;
    Label cbDirections, cbPassengers, cbSuitcases, cbTolls, labelResultGoogle;
    DatePicker dpDepartureDate;

    Label originA, destinA;

    TextArea lbInfo;
    TextField Destin1 = new TextField();
    TextField Origin1 = new TextField();

    MyBrowser myBrowser;

    public BookInfos(CRPCManager crpcManager, MyBrowser myBrowser){
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;
        pcs = new PropertyChangeSupport(this);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        Font font = Font.font("Verdana", FontWeight.BOLD, 16);
        Font fontSmall = Font.font("Verdana", FontWeight.BOLD, 10);
        //VBox of the Addresses and the Options
        originA = new Label();
        destinA = new Label();

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


        originA.setId("originA");
        destinA.setId("destinA");
        originA.setMaxWidth(250);
        destinA.setMaxWidth(250);


        String returnGoogleStr = "";

        Origin1.setPromptText("Origin1");
        Destin1.setPromptText("Destin1");

        if (originA.getText().equals("")){
            originA.setText("Coimbra, Portugal");
            destinA.setText("Porto, Portugal");
        }

        vbStartAdress.getChildren().addAll(lbStartAdress,originA);

        //End Adress
        vbEndAdress = new VBox();
        vbEndAdress.setAlignment(Pos.CENTER_LEFT);

        lbEndAdress = new Label("End adress");
        lbEndAdress.setAlignment(Pos.CENTER_LEFT);
        lbEndAdress.setPadding(new Insets(10,0,5,0));
        lbEndAdress.setFont(fontSmall);

        vbEndAdress.getChildren().addAll(lbEndAdress,destinA);

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
        cbDirections = new Label();
        cbDirections.setText("One Way");

        vbDirections.getChildren().addAll(lbDirections,cbDirections);

        //Extra Waiting Time
        vbExtraWaitTime = new VBox();
        vbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);

        lbExtraWaitTime = new Label("Extra Waiting Time (minutes)");
        lbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);
        lbExtraWaitTime.setPadding(new Insets(0,0,5,0));
        lbExtraWaitTime.setFont(fontSmall);
        tfExtraWaitTime = new Label();

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
        cbPassengers = new Label();
        cbPassengers.setText("1");
        vbPassengers.getChildren().addAll(lbPassengers,cbPassengers);

        //Suitcases
        vbSuitcases = new VBox();
        vbSuitcases.setAlignment(Pos.CENTER);

        lbSuitcases = new Label("Suitcases");
        lbSuitcases.setAlignment(Pos.CENTER_RIGHT);
        lbSuitcases.setPadding(new Insets(0,0,5,0));
        lbSuitcases.setFont(fontSmall);
        cbSuitcases = new Label();
        cbSuitcases.setText("0");
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
        Label lbDepartureDate2 = new Label("");
        lbDepartureDate2.setText(LOCAL_DATE().toString());


        vbDepartureDate.getChildren().addAll(lbDepartureDate, lbDepartureDate2);

        //parte de colocar a imagem aqui


        hbDepartureDateAndImage.getChildren().addAll(vbDepartureDate/*,cena da imagem*/);

        //HBox for Departure Time and Image
        hbDepartureTimeAndImage = new HBox();
        hbDepartureTimeAndImage.setAlignment(Pos.CENTER_LEFT);


        //Tolls
        vbTolls = new VBox();
        vbTolls.setAlignment(Pos.CENTER_LEFT);

        lbTolls = new Label("Tolls:");
        lbTolls.setFont(font);
        lbTolls.setAlignment(Pos.CENTER_LEFT);
        cbTolls = new Label();
        cbTolls.setText("Yes");
        vbTolls.getChildren().addAll(lbTolls,cbTolls);
        vbOptions.getChildren().addAll(vbDirections,vbExtraWaitTime,hbPassengersSuitcases,hbDepartureDateAndImage,vbTolls);
        vbOptions.setSpacing(15);

        vbOptionsWithTitle.getChildren().addAll(lbOptionsTitle,vbOptions);

        submitBtns= new HBox();
        btnConfirm = new Button("Confirm");
        btnReset = new Button("Reset");
        btnConfirm.setId("mbtnSubmit");
        btnReset.setId("mbtnReset");
        submitBtns.getChildren().addAll(btnReset, btnConfirm);
        submitBtns.setPadding(new Insets(10,0,0,40));
        submitBtns.setSpacing(10);

        labelResultGoogle = new Label();

        lbInfoLabel = new Label("Trip Information");
        lbInfoLabel.setPadding(new Insets(20,0,5,0));
        lbInfoLabel.setFont(font);
        //Adresses VBox

        lbInfoLabel.setAlignment(Pos.CENTER);
        lbInfo = new TextArea();
        lbInfo.setPrefColumnCount(7);
        lbInfo.setPrefRowCount(2);
        vbAdressesAndOptions.setSpacing(10);
        vbAdressesAndOptions.getChildren().addAll(vbAdressesWithTitle,vbOptionsWithTitle, labelResultGoogle, lbInfoLabel, lbInfo,btnConfirm);



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

        btnConfirm.setOnAction(actionEvent -> {
            crpcManager.goChooseCAr();

        });

        btnReset.setOnAction(actionEvent -> {
            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm());
            crpcManager.goMainMenu();
        });


        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    void clearForm(){
        tfExtraWaitTime.setText("0");
        dpDepartureDate.setValue(null);
        tfDepartureTime.setText("Time");
    }

    private void update() {
        if (crpcManager.getState() == States.CONFIRM_BOOKING) {
            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm() + "?origin="
                    + crpcManager.getTripOrigin() + "&destin=" + crpcManager.getTripDestination() +"&style=" +"macDivSmaller" + "&tolls" + cbTolls.getText());

            labelResultGoogle.setText("Price: " + crpcManager.getCostOfTrip() + "\nDuration: " +crpcManager.getTimeOfTrip() + "\nDistance : " + crpcManager.getDistanceOfTrip());
            System.out.println("Results: " + crpcManager.getGoogleReturn());
        }
    }

}
