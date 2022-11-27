package pt.isec.gps.team11.model.fsm;

public interface IStates {
    States getState();
    boolean goMainMenu();
    boolean goBooking();
    boolean goChooseCar();
    boolean goListTrips();
    boolean goConfirmBooking();
    boolean goTripDetails();
}
