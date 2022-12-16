package pt.isec.gps.team11.gui.panes.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.fsm.States;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BookInfos extends BorderPane {
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    VBox vbAdressesAndOptions, vbAdresses, vbOptions, vbAdressesWithTitle, vbOptionsWithTitle;

    String returnGoogleStr = "";
    VBox vbDepartureDate, vbDepartureTime, form;
    Label lbStartAdress, tfExtraWaitTime, tfDepartureTime, lbEndAdress, lbDirections, lbExtraWaitTime, lbPassengers, lbSuitcases, lbDepartureDate, lbDepartureTime, lbTolls, lbAdressesTitle, lbOptionsTitle;
    HBox hbPassengersSuitcases, hbStartAdress, hbEndAdress, hbDepartureDateAndImage, hbDepartureTimeAndImage, hDirectTolls, hbOptions, hbExtraWaitTime, hbPassengers, hbSuitcases, hbDirections, hbTolls;

    Label cbDirections, cbPassengers, cbSuitcases, cbTolls, labelResultGoogle;
    DatePicker dpDepartureDate;

    Label originA, destinA;


    TextField Destin1 = new TextField();
    TextField Origin1 = new TextField();
    MyBrowser myBrowser;
    HBox cars;
    ImageView car;
    boolean alreadyGotTheCars;

    Car currentCar;

    public BookInfos(CRPCManager crpcManager, MyBrowser myBrowser) {
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;
        pcs = new PropertyChangeSupport(this);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this, "styles.css");
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

        lbAdressesTitle = new Label("Addresses");
        lbAdressesTitle.setPadding(new Insets(20, 0, 5, 0));
        lbAdressesTitle.setFont(font);
        //Adresses VBox
        vbAdresses = new VBox();
        vbAdresses.setAlignment(Pos.CENTER);

        //Start Adress
        hbStartAdress = new HBox();
        hbStartAdress.setAlignment(Pos.CENTER_LEFT);

        lbStartAdress = new Label("Start address: ");
        lbStartAdress.setAlignment(Pos.CENTER_LEFT);
        lbStartAdress.setFont(fontSmall);


        originA.setId("originA");
        destinA.setId("destinA");
        originA.setMaxWidth(250);
        destinA.setMaxWidth(250);


        String returnGoogleStr = "";

        Origin1.setPromptText("Origin1");
        Destin1.setPromptText("Destin1");

        if (originA.getText().equals("")) {
            originA.setText("Coimbra, Portugal");
            destinA.setText("Porto, Portugal");
        }

        hbStartAdress.getChildren().addAll(lbStartAdress, originA);

        //End Adress
        hbEndAdress = new HBox();
        hbEndAdress.setAlignment(Pos.CENTER_LEFT);

        lbEndAdress = new Label("End address: ");
        lbEndAdress.setAlignment(Pos.CENTER_LEFT);
        lbEndAdress.setPadding(new Insets(10, 0, 5, 0));
        lbEndAdress.setFont(fontSmall);

        hbEndAdress.getChildren().addAll(lbEndAdress, destinA);

        vbAdresses.getChildren().addAll(hbStartAdress, hbEndAdress);

        vbAdressesWithTitle.getChildren().addAll(lbAdressesTitle, vbAdresses);


        //Options VBox With Title
        vbOptionsWithTitle = new VBox();
        vbOptionsWithTitle.setAlignment(Pos.CENTER);

        lbOptionsTitle = new Label("");
        lbOptionsTitle.setPadding(new Insets(0, 0, 10, 0));
        lbOptionsTitle.setFont(font);
        //Options VBox
        vbOptions = new VBox();
        vbOptions.setAlignment(Pos.CENTER);

        //Directions
        hbDirections = new HBox();
        hbDirections.setAlignment(Pos.CENTER_LEFT);

        lbDirections = new Label("Directions: ");
        lbDirections.setPadding(new Insets(0, 0, 0, 0));
        lbDirections.setFont(fontSmall);
        lbDirections.setAlignment(Pos.CENTER_LEFT);
        cbDirections = new Label();
        cbDirections.setText("One Way");
        //Tolls
        hbTolls = new HBox();
        hbTolls.setAlignment(Pos.CENTER_LEFT);

        lbTolls = new Label("Tolls: ");
        lbTolls.setFont(fontSmall);
        lbTolls.setAlignment(Pos.CENTER_LEFT);
        cbTolls = new Label();
        cbTolls.setText("Yes");
        hbTolls.getChildren().addAll(lbTolls, cbTolls);
        hDirectTolls = new HBox();
        hDirectTolls.getChildren().addAll(hbDirections, hbTolls);
        hDirectTolls.setAlignment(Pos.CENTER_LEFT);
        hDirectTolls.setSpacing(20);
        hbDirections.getChildren().addAll(lbDirections, cbDirections);

        //Extra Waiting Time
        hbExtraWaitTime = new HBox();
        hbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);

        lbExtraWaitTime = new Label("Extra Waiting Time (minutes): ");
        lbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);
        lbExtraWaitTime.setFont(fontSmall);
        tfExtraWaitTime = new Label();

        if (tfExtraWaitTime.getText().equals(""))
            tfExtraWaitTime.setText("0");

        hbExtraWaitTime.getChildren().addAll(lbExtraWaitTime, tfExtraWaitTime);

        //HBox for Passengers and Suitcases
        hbPassengersSuitcases = new HBox();
        hbPassengersSuitcases.setAlignment(Pos.CENTER_LEFT);

        //Passengers
        hbPassengers = new HBox();
        hbPassengers.setAlignment(Pos.CENTER);

        lbPassengers = new Label("Passengers: ");
        lbPassengers.setAlignment(Pos.CENTER_LEFT);
        lbPassengers.setFont(fontSmall);
        cbPassengers = new Label();
        cbPassengers.setText("1");
        hbPassengers.getChildren().addAll(lbPassengers, cbPassengers);

        //Suitcases
        hbSuitcases = new HBox();
        hbSuitcases.setAlignment(Pos.CENTER);

        lbSuitcases = new Label("Suitcases: ");
        lbSuitcases.setAlignment(Pos.CENTER_RIGHT);
        lbSuitcases.setFont(fontSmall);
        cbSuitcases = new Label();
        cbSuitcases.setText("0");
        hbSuitcases.getChildren().addAll(lbSuitcases, cbSuitcases);

        hbPassengersSuitcases.getChildren().addAll(hbPassengers, hbSuitcases);
        hbPassengersSuitcases.setSpacing(10);

        //HBox for Departure Date and Image
        hbDepartureDateAndImage = new HBox();
        hbDepartureDateAndImage.setAlignment(Pos.CENTER_LEFT);

        //Departure Date
        vbDepartureDate = new VBox();
        vbDepartureDate.setAlignment(Pos.CENTER);

        lbDepartureDate = new Label("Departure date");
        lbDepartureDate.setAlignment(Pos.CENTER_LEFT);
        lbDepartureDate.setPadding(new Insets(0, 0, 5, 0));
        lbDepartureDate.setFont(fontSmall);
        Label lbDepartureDate2 = new Label("");
        lbDepartureDate2.setText(LOCAL_DATE().toString());


        vbDepartureDate.getChildren().addAll(lbDepartureDate, lbDepartureDate2);

        //parte de colocar a imagem aqui


        hbDepartureDateAndImage.getChildren().addAll(vbDepartureDate/*,cena da imagem*/);

        //HBox for Departure Time and Image
        hbDepartureTimeAndImage = new HBox();
        hbDepartureTimeAndImage.setAlignment(Pos.CENTER_LEFT);


        vbOptions.getChildren().addAll(hDirectTolls, hbExtraWaitTime, hbPassengersSuitcases, hbDepartureDateAndImage);
        vbOptions.setSpacing(15);
        vbOptionsWithTitle.getChildren().addAll(lbOptionsTitle, vbOptions);


        cars = new HBox();

        car = new ImageView();
        car.setId("carsImages");
        car.setFitHeight(110);
        car.setPreserveRatio(true);
        cars.getChildren().add(car);

        cars.setSpacing(35);
        cars.setPadding(new Insets(10, 0, 0, 50));

        labelResultGoogle = new Label();
        labelResultGoogle.setPadding(new Insets(10, 0, 0, 0));
        //Adresses VBox
        vbAdressesAndOptions.setSpacing(10);
        vbAdressesAndOptions.getChildren().addAll(vbAdressesWithTitle, vbOptionsWithTitle, labelResultGoogle, cars);

        hbOptions = new HBox(vbAdressesAndOptions);

        this.setTop(hbOptions);
    }

    public static final LocalDate LOCAL_DATE() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
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
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    void clearForm() {
        tfExtraWaitTime.setText("0");
        dpDepartureDate.setValue(null);
        tfDepartureTime.setText("Time");
    }

    private void update() {

        if (crpcManager.getState() == States.CONFIRM_BOOKING) {
            car.setImage(ImageManager.getImage(crpcManager.getCurrentTripCar().getImage()));

            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm() + "?origin="
                    + crpcManager.getTripOrigin() + "&destin=" + crpcManager.getTripDestination() + "&style=" + "macDivSmaller" + "&tolls=" + cbTolls.getText() + "&returnTrip=" + cbDirections.getText());

            labelResultGoogle.setText("PRICE: " + crpcManager.getCostOfTrip() + "\nDURATION: " + crpcManager.getTimeOfTrip() + "\nDISTANCE : " + crpcManager.getDistanceOfTrip());
            //System.out.println("Results: " + crpcManager.getGoogleReturn());
        }
    }

}
