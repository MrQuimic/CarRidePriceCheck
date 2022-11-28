package pt.isec.gps.team11.gui.panes.utils;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.gps.team11.model.CRPCManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BookForm extends BorderPane {
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    VBox vbAdressesAndOptions, vbAdresses, vbOptions, vbAdressesWithTitle, vbOptionsWithTitle;
    Label lbAdressesTitle, lbOptionsTitle;

    VBox vbStartAdress, vbEndAdress, vbDirections, vbExtraWaitTime, vbPassengers, vbSuitcases, vbDepartureDate, vbDepartureTime, vbTolls;
    Label lbStartAdress, lbEndAdress, lbDirections, lbExtraWaitTime, lbPassengers, lbSuitcases, lbDepartureDate, lbDepartureTime, lbTolls;

    HBox hbPassengersSuitcases, hbDepartureDateAndImage, hbDepartureTimeAndImage;

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
        //VBox of the Addresses and the Options
        vbAdressesAndOptions = new VBox();
        vbAdressesAndOptions.setAlignment(Pos.CENTER_LEFT);

        //Adresses VBox With Title
        vbAdressesWithTitle = new VBox();
        vbAdressesWithTitle.setAlignment(Pos.CENTER);

        lbAdressesTitle = new Label("Adresses");

        //Adresses VBox
        vbAdresses = new VBox();
        vbAdresses.setAlignment(Pos.CENTER);

        //Start Adress
        vbStartAdress = new VBox();
        vbStartAdress.setAlignment(Pos.CENTER_LEFT);

        lbStartAdress = new Label("Start adress");
        lbStartAdress.setAlignment(Pos.CENTER_LEFT);
        tfStartAdress = new TextField();
        tfStartAdress.setPromptText("Introduce start address");

        vbStartAdress.getChildren().addAll(lbStartAdress,tfStartAdress);

        //End Adress
        vbEndAdress = new VBox();
        vbEndAdress.setAlignment(Pos.CENTER_LEFT);

        lbEndAdress = new Label("Start adress");
        lbEndAdress.setAlignment(Pos.CENTER_LEFT);
        tfEndAdress = new TextField();
        tfEndAdress.setPromptText("Introduce end address");

        vbEndAdress.getChildren().addAll(lbEndAdress,tfEndAdress);


        vbAdresses.getChildren().addAll(vbStartAdress, vbEndAdress);

        vbAdressesWithTitle.getChildren().addAll(lbAdressesTitle,vbAdresses);

        //Options VBox With Title
        vbOptionsWithTitle = new VBox();
        vbOptionsWithTitle.setAlignment(Pos.CENTER);

        lbOptionsTitle = new Label("Options");

        //Options VBox
        vbOptions = new VBox();
        vbOptions.setAlignment(Pos.CENTER);

        //Directions
        vbDirections = new VBox();
        vbDirections.setAlignment(Pos.CENTER_LEFT);

        lbDirections = new Label("Directions");
        lbDirections.setAlignment(Pos.CENTER_LEFT);
        cbDirections = new ChoiceBox();
        //add items to choiceBox

        vbDirections.getChildren().addAll(lbDirections,cbDirections);

        //Extra Waiting Time
        vbExtraWaitTime = new VBox();
        vbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);

        lbExtraWaitTime = new Label("Extra Waiting Time (minutes)");
        lbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);
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
        cbPassengers = new ChoiceBox();
        //add items to choiceBox

        vbPassengers.getChildren().addAll(lbPassengers,cbPassengers);

        //Suitcases
        vbSuitcases = new VBox();
        vbSuitcases.setAlignment(Pos.CENTER);

        lbSuitcases = new Label("Suitcases");
        lbSuitcases.setAlignment(Pos.CENTER_RIGHT);
        cbSuitcases = new ChoiceBox();
        //add items to choiceBox

        vbSuitcases.getChildren().addAll(lbSuitcases,cbSuitcases);

        hbPassengersSuitcases.getChildren().addAll(vbPassengers,vbSuitcases);

        //HBox for Departure Date and Image
        hbDepartureDateAndImage = new HBox();
        hbDepartureDateAndImage.setAlignment(Pos.CENTER_LEFT);

        //Departure Date
        vbDepartureDate = new VBox();
        vbDepartureDate.setAlignment(Pos.CENTER);

        lbDepartureDate = new Label("Departure date");
        lbDepartureDate.setAlignment(Pos.CENTER_LEFT);
        dpDepartureDate = new DatePicker();


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
        tfDepartureTime = new TextField();
        tfDepartureTime.setPromptText("Time");

        vbDepartureTime.getChildren().addAll(lbDepartureTime,tfDepartureTime);

        //parte de colocar a imagem aqui


        hbDepartureTimeAndImage.getChildren().addAll(vbDepartureTime/*,cena da imagem*/);

        //Tolls
        vbTolls = new VBox();
        vbTolls.setAlignment(Pos.CENTER);

        lbTolls = new Label("Tolls:");
        lbTolls.setAlignment(Pos.CENTER_LEFT);
        cbTolls = new ChoiceBox();
        //add items to choiceBox

        vbOptions.getChildren().addAll(vbDirections,vbExtraWaitTime,hbPassengersSuitcases,hbDepartureDateAndImage,hbDepartureTimeAndImage,vbTolls);

        vbOptionsWithTitle.getChildren().addAll(lbOptionsTitle,vbOptions);

        vbAdressesAndOptions.getChildren().addAll(vbAdressesWithTitle,vbOptionsWithTitle);

        this.setCenter(vbAdressesAndOptions);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void configAdapter() {
    }

    private void update() {

    }

}
