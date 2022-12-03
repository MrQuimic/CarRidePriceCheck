package pt.isec.gps.team11.model.fsm;

import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.data.Trip;

import java.util.ArrayList;

public class StateAdapter implements IStates{
    protected StatesContext context;
    protected Data data;

    public StateAdapter(StatesContext context, Data data){
        this.context = context;
        this.data = data;
    }

    protected void setState(States state){
        context.setState(state.createState(context, data));
    }

    @Override
    public States getState(){
        return null;
    }

    @Override
    public boolean goBooking() {
        return false;
    }

    @Override
    public boolean goChooseCar() {
        return false;
    }

    @Override
    public boolean goConfirmBooking() {
        return false;
    }

    @Override
    public boolean goListTrips() {
        return false;
    }

    @Override
    public boolean goMainMenu() {
        return false;
    }

    @Override
    public boolean goTripDetails() {
        return false;
    }

    @Override
    public void setNumberOfPassengers(int numberOfPassengers) {
    }

    @Override
    public ArrayList<Car> getSuitableCars(int lotation, int baggage) {
        return null;
    }

    @Override
    public ArrayList<Trip> getTrips() {
        return null;
    }

    @Override
    public boolean book(boolean oneWay, String date, int extraWaitingTime,
                        int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway) {
        return false;
    }
}
