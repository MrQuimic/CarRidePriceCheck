package pt.isec.gps.team11.model.fsm;

import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.data.Trip;

import java.util.ArrayList;

public interface IStates {
    States getState();
    boolean goIdle();
    boolean goMainMenu();
    boolean goBooking();
    boolean goChooseCar();
    boolean goListTrips();
    boolean goConfirmBooking();
    boolean goTripDetails();
    void setNumberOfPassengers(int numberOfPassengers);
    ArrayList<Car> getSuitableCars(int lotation, int baggage);
    ArrayList<Trip> getTrips();
    boolean book(boolean oneWay, String date, int extraWaitingTime,
                        int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway);
}
