package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.data.Trip;
import pt.isec.gps.team11.model.fsm.States;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingListPane extends BorderPane {
    CRPCManager crpcManager;
    private ArrayList<Trip> trips;
    private HBox[] tripsInfos;
    private VBox vbFirst, vbox1, vbox2, vbox3;
    public BookingListPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
     createViews();
        registerHandlers();
        update();
    }

    private void createViews() {


        this.trips = crpcManager.getTripsMap();
        this.tripsInfos = new HBox[trips.size()];
        System.out.println("Tam: " + trips.size());
        Label lbOurServices = new Label("Booking List");
        lbOurServices.setStyle("-fx-text-fill: rgba(35,34,34,0.51); -fx-font-size: 20px; -fx-font-weight: 700");
        vbFirst = new VBox(new Separator(), lbOurServices, new Separator());

        createLists();


        this.setTop(vbFirst);
    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void configAdapter() {
    }

    private void createLists(){

        for(int i=0; i<trips.size(); i++){
            tripsInfos[i] = new HBox();
            vbox1 = new VBox();
            vbox2 = new VBox();
            vbox3 = new VBox();
            Trip currentTrip = trips.get(i);

            /* vbox1                                                       */
            Label car = new Label();
            car.setText("Car: "+ currentTrip.getCar().getBrand() + " " + currentTrip.getCar().getModel());
            Label licensePlate = new Label();
            licensePlate.setText("License plate: " + currentTrip.getCar().getLicensePlate());
            Label directions = new Label();
            String label3String = currentTrip.isOneWay() ? "One way" : "Comeback";
            directions.setText("Directions: " + label3String);
            vbox1.getChildren().addAll(car, licensePlate, directions);
            /*                                      vbox2                                                       */
            Label startAddress = new Label();
            startAddress.setText("Start address: " + currentTrip.getOrigin());
            Label endAddress = new Label();
            endAddress.setText("End address: " + currentTrip.getDestination());
            Label departureTime = new Label();
            departureTime.setText("Departure Time: " + currentTrip.getStringDate() + " " + currentTrip.getStringTime());
            Label waitingTime = new Label();
            waitingTime.setText("Waiting time: " + currentTrip.getExtraWaitingTime());
            vbox2.getChildren().addAll(startAddress, endAddress, departureTime, waitingTime);
            /*                                      vbox3                                                       */
            Label kilometers = new Label();
            kilometers.setText("kilometers: " + currentTrip.getDistance());
            Label price = new Label();
            endAddress.setText("Price: " + currentTrip.getPrice());
            Label passengers = new Label();
            passengers.setText("Passengers: " + currentTrip.getNumberOfPassengers() );
            Label suitcases = new Label();
            suitcases.setText("Suitcases: " + currentTrip.getNumberOfLuggage());
            vbox3.getChildren().addAll(kilometers, price, passengers, suitcases);

            tripsInfos[i].getChildren().addAll(vbox1, vbox2, vbox3);
            vbFirst.getChildren().add(tripsInfos[i]);
            tripsInfos[i].setPadding(new Insets(20,0,0,30));
            tripsInfos[i].setSpacing(20);

        }

    }
    private void update() {

        if (crpcManager.getMenuOpt() == MenuOpt.BOOKINGLIST) {
            this.trips = crpcManager.getTripsMap();

            this.tripsInfos = new HBox[trips.size()];
            vbFirst.getChildren().clear();

            createLists();

            configAdapter();
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }


}