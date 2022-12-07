package pt.isec.gps.team11.model.fsm.implementation;

import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.fsm.StateAdapter;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

public class IdleState extends StateAdapter {
    public IdleState(StatesContext context, Data data) {
        super(context, data);
    }

    @Override
    public States getState() {
        return States.IDLE;
    }

    @Override
    public boolean goMainMenu() {
        setState(States.MAIN_MENU);
        return true;
    }

    @Override
    public boolean goBooking() {
        setState(States.BOOKING);
        return true;
    }

    @Override
    public boolean goChooseCar() {
        setState(States.CHOOSE_CAR);
        return true;
    }

    @Override
    public boolean goConfirmBooking() {
        setState(States.CONFIRM_BOOKING);
        return true;
    }

    @Override
    public boolean goListTrips() {
        setState(States.LIST_TRIPS);
        return true;
    }

    @Override
    public boolean goTripDetails() {
        setState(States.TRIP_DETAILS);
        return true;
    }
}
