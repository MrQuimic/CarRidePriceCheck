package pt.isec.gps.team11.model;

import pt.isec.gps.team11.gui.MenuOpt;
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

    /**
     * Instantiates a new Gpe manager.
     */
    public CRPCManager() {
        this.fsm = new StatesContext();
        pcs = new PropertyChangeSupport(this);
       this.menuOpt = MenuOpt.BOOKING;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public States getState() {
        return fsm.getState();
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
    public void addPropertyChangeListener(String property, PropertyChangeListener listener){
        pcs.addPropertyChangeListener(property, listener);
    }
    public void setMenuOpt(MenuOpt menuOpt) {
        this.menuOpt = menuOpt;
        System.out.println(menuOpt);

        pcs.firePropertyChange(null, null, null);
    }

    public boolean goMainMenu(){
        if(this.fsm.goMainMenu()){
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goBooking(){
        if(this.fsm.goBooking()){
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }
    
    public boolean goChooseCAr(){
        if(this.fsm.goChooseCar()){
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goListTrips(){
        if(this.fsm.goListTrips()){
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goConfirmBooking(){
        if(this.fsm.goConfirmBooking()){
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean goTripDetails(){
        if(this.fsm.goTripDetails()){
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    public boolean confirmLogin(String username, String password){
        return this.fsm.confirmLogin(username, password);
    }

    public boolean isLogged(){
        return this.fsm.isLogged();
    }

    public String getUsername() {
        return username;
    }

    public void setLogin(String username) {
        this.username = username;
        pcs.firePropertyChange(null, null, null);
    }

    public boolean book(boolean oneWay, String date, int extraWaitingTime, int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway){
        return fsm.book(oneWay, date, extraWaitingTime, numberOfLuggage, numberOfPassengers, departureTime, highway);
    }

    public Trip getCurrentTrip(){
        return fsm.getCurrentTrip();
    }

    public void logout(){
        pcs.firePropertyChange(null, null, null);
        fsm.logout();
    }

    public void saveTripResults(String result){
        fsm.saveTripResult(result);
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
}