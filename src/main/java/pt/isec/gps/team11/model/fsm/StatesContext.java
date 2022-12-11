package pt.isec.gps.team11.model.fsm;

import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.data.Trip;
import pt.isec.gps.team11.model.fsm.implementation.BookingState;
import pt.isec.gps.team11.model.fsm.implementation.MainMenuState;

import java.util.ArrayList;

public class StatesContext {
    private final Data data;
    private IStates state;

    public StatesContext(){
        this.data = new Data();
        this.state = new MainMenuState(this, data);
    }

    public States getState(){
        return this.state.getState();
    }

    public void setState(IStates state){
        this.state = state;
    }

    public boolean goIdle(){
        return state.goIdle();
    }

    public boolean goMainMenu(){
        return state.goMainMenu();
    }

    public boolean goBooking(){
        return state.goBooking();
    }

    public boolean goChooseCar(){
        return state.goChooseCar();
    }

    public boolean goListTrips(){
        return state.goListTrips();
    }

    public boolean goConfirmBooking(){
        return state.goConfirmBooking();
    }

    public boolean goTripDetails(){
        return state.goTripDetails();
    }

    public boolean confirmLogin(String username, String password){
        return data.confirmLogin(username, password);
    }

    public boolean isLogged(){
        return this.data.getIsLogged();
    }

    public void setIsLogged(){this.data.setIsLogged();}

    public boolean book(boolean oneWay, String date, int extraWaitingTime,int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway){
        return state.book(oneWay,date,extraWaitingTime,numberOfLuggage,numberOfPassengers,departureTime,highway);
    }

    public Trip getCurrentTrip(){
        return data.getCurrentTrip();
    }

    public void logout(){
        data.logout();
    }

    public void saveTripResult(String result){
        data.saveTripsResult(result);
    }

    public void resetTripResults(){
        data.resetTripResults();
    }

    public String getCostOfTrip() {
        return data.getCostOfTrip();
    }

    public String getTimeOfTrip() {
        return data.getTimeOfTrip();
    }

    public String getDistanceOfTrip() {
        return data.getDistanceOfTrip();
    }

    public void setTripOrigin(String tripOrigin) {
        data.setTripOrigin(tripOrigin);
    }

    public void setTripDestination(String tripDestination) {
        data.setTripDestination(tripDestination);
    }

    public String getTripDestination() {
        return data.getTripDestination();
    }

    public String getTripOrigin() {
        return data.getTripOrigin();
    }

    public ArrayList<Car> getSuitableCars(){
        return state.getSuitableCars();
    }

    public ArrayList<Trip> getSuitableTrips(){
        return state.getSuitableTrips();
    }

    public ArrayList<Trip> getTripsMap(){

        ArrayList<Trip> temp = new ArrayList<>(data.getTrips().values());
        return temp;
    }

    public void saveCurrentCar(Car car){
        state.saveCurrentCar(car);
    }

    public void confirmTrip(){
        state.confirmTrip();
    }
    
    public Car getCurrentTripCar(){
        return data.getCurrentTripCar();
    }
}
