package pt.isec.gps.team11.model;

import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.data.Trip;
import pt.isec.gps.team11.model.fsm.IStates;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * The type Gpe manager.
 */
public class CRPCManager {
    private StatesContext fsm;

    PropertyChangeSupport pcs;

    MenuOpt menuOpt;
    private String username;

    private String googleReturnValue;

    public static final String TRIP = "trip";

    private States previousState;

    /**
     * Instantiates a new Gpe manager.
     */
    public CRPCManager() {
        this.fsm = new StatesContext();
        pcs = new PropertyChangeSupport(this);
        this.menuOpt = null;
        this.previousState = null;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public States getState() {
        return fsm.getState();
    }

    public States getPreviousState() {
        return this.previousState;
    }

    public MenuOpt getMenuOpt() {
        return menuOpt;
    }

    private void configAdapter() {

    }

    /**
     * Add property change listener.
     *
     * @param listener the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public void setMenuOpt(MenuOpt menuOpt) {
        this.previousState = fsm.getState();
        this.menuOpt = menuOpt;
        //System.out.println(menuOpt);

        pcs.firePropertyChange(null, null, null);
    }

    public void goToPreviousState() {
        this.menuOpt = null;

        if(getState() == States.IDLE){
            switch (previousState) {
                case BOOKING -> goBooking();
                case MAIN_MENU -> goMainMenu();
                case CHOOSE_CAR -> goChooseCAr();
                case LIST_TRIPS -> goListTrips();
                case TRIP_DETAILS -> goTripDetails();
                case CONFIRM_BOOKING -> goConfirmBooking();
            }
        }

        this.previousState = null;
    }

    public boolean goIdle() {
        if (this.fsm.goIdle()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goMainMenu() {
        if (this.menuOpt != null) {
            goToPreviousState();
        }
        if (this.fsm.goMainMenu()) {
            pcs.firePropertyChange(null, null, null);
            pcs.firePropertyChange("CONFIRM", null, null);
            return true;
        }
        return false;
    }

    public void setIsLogged() {
        this.fsm.setIsLogged();
        pcs.firePropertyChange(null, null, null);
    }

    public boolean goBooking() {
        if (this.menuOpt != null) {
            goToPreviousState();
        }
        if (this.fsm.goBooking()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goChooseCAr() {
        if (this.fsm.goChooseCar()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goListTrips() {
        if (this.fsm.goListTrips()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goConfirmBooking() {
        if (this.fsm.goConfirmBooking()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goTripDetails() {
        if (this.fsm.goTripDetails()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean confirmLogin(String username, String password) {
        return this.fsm.confirmLogin(username, password);
    }

    public boolean isLogged() {
        return this.fsm.isLogged();
    }

    public String getUsername() {
        return username;
    }

    public boolean setLogin(String username) {
        if (username.contains("@")) {
            this.username = username;
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean book(boolean oneWay, String date, int extraWaitingTime, int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway) {
        return fsm.book(oneWay, date, extraWaitingTime, numberOfLuggage, numberOfPassengers, departureTime, highway);
    }

    public Trip getCurrentTrip() {
        return fsm.getCurrentTrip();
    }

    public void logout() {
        pcs.firePropertyChange(null, null, null);
        fsm.logout();
    }

    public void saveTripResults(String result) {
        fsm.saveTripResult(result);
    }

    public void resetTripResults() {
        fsm.resetTripResults();
    }

    public String getCostOfTrip() {
        return fsm.getCostOfTrip();
    }

    public String getTimeOfTrip() {
        return fsm.getTimeOfTrip();
    }

    public String getDistanceOfTrip() {
        return fsm.getDistanceOfTrip();
    }

    public void setTripOrigin(String tripOrigin) {
        fsm.setTripOrigin(tripOrigin);
    }

    public void setTripDestination(String tripDestination) {
        fsm.setTripDestination(tripDestination);
    }

    public String getTripDestination() {
        return fsm.getTripDestination();
    }

    public String getTripOrigin() {
        return fsm.getTripOrigin();
    }

    public void setGoogleReturn(String googleReturnValue) {
        this.googleReturnValue = googleReturnValue;
    }

    public String getGoogleReturn() {
        return googleReturnValue;
    }

    public ArrayList<Car> getSuitableCars() {
        return this.fsm.getSuitableCars();
    }

    public ArrayList<Trip> getSuitableTrips() {
        return this.fsm.getSuitableTrips();
    }

    public ArrayList<Trip> getTripsMap() {
        return fsm.getTripsMap();
    }


    public void saveCar(Car car) {
        this.fsm.saveCurrentCar(car);
    }

    public void confirmTrip() {
        pcs.firePropertyChange("CONFIRM", null, null);
        this.fsm.confirmTrip();
    }

    public Car getCurrentTripCar() {
        return fsm.getCurrentTripCar();
    }
}