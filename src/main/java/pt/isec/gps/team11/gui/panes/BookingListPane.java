package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.data.Trip;
import pt.isec.gps.team11.model.fsm.States;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingListPane extends StackPane {
    CRPCManager crpcManager;
    private ArrayList<Trip> trips;
    private HBox[] tripsInfos;
    private VBox vbFirst;

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
        vbFirst = new VBox(new Separator(), lbOurServices, new Separator());

        for(int i=0; i<trips.size(); i++){
            tripsInfos[i] = new HBox();
            VBox vbox1 = new VBox();
            VBox vbox2 = new VBox();
            VBox vbox3 = new VBox();
            Trip currentTrip = trips.get(i);

            /*                                      vbox1                                                       */
            Label car = new Label();
            car.setText("Car:\t"+ currentTrip.getCar().getBrand() + " " + currentTrip.getCar().getModel() +'\t');
            Label licensePlate = new Label();
            licensePlate.setText("License plate:\t" + currentTrip.getCar().getLicensePlate()+'\t');
            Label directions = new Label();
            String label3String = currentTrip.isOneWay() ? "One way" : "Comeback";
            directions.setText("Directions:\t" + label3String+'\t');
            vbox1.getChildren().addAll(car, licensePlate, directions);

            /*                                      vbox2                                                       */
            Label startAddress = new Label();
            startAddress.setText("Start address:\t" + currentTrip.getOrigin()+'\t');
            Label endAddress = new Label();
            endAddress.setText("End address:\t" + currentTrip.getDestination()+'\t');
            Label departureTime = new Label();
            departureTime.setText("Departure Time:\t" + currentTrip.getStringDate() + " " + currentTrip.getStringTime()+'\t');
            Label waitingTime = new Label();
            waitingTime.setText("Waiting time:\t" + currentTrip.getExtraWaitingTime()+'\t');
            vbox2.getChildren().addAll(startAddress, endAddress, departureTime, waitingTime);
            /*                                      vbox3                                                       */
            Label kilometers = new Label();
            kilometers.setText("kilometers:\t" + currentTrip.getDistance()+'\t');
            Label price = new Label();
            endAddress.setText("Price:\t" + currentTrip.getPrice()+'\t');
            Label passengers = new Label();
            passengers.setText("Passengers:\t" + currentTrip.getNumberOfPassengers() +'\t');
            Label suitcases = new Label();
            suitcases.setText("Suitcases:\t" + currentTrip.getNumberOfLuggage()+'\t');
            vbox3.getChildren().addAll(kilometers, price, passengers, suitcases);
            suitcases.setTextAlignment(TextAlignment.CENTER);
            tripsInfos[i].getChildren().addAll(vbox1, vbox2, vbox3);
            vbFirst.getChildren().add(tripsInfos[i]);
        }
        this.getChildren().add(vbFirst);
    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void configAdapter() {
    }


    private void update() {

        if (crpcManager.getMenuOpt() == MenuOpt.BOOKINGLIST) {
            configAdapter();
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }
}
