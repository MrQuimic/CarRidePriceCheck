package pt.isec.gps.team11.model.fsm;

import pt.isec.gps.team11.model.data.Data;
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

    public boolean book(boolean oneWay, String date, int extraWaitingTime,
                        int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway){
        return false;
    }

    public void logout(){
        data.logout();
    }
}
