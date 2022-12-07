package pt.isec.gps.team11.model.fsm.implementation;

import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.fsm.StateAdapter;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

import java.util.ArrayList;

public class ConfirmBookingState extends StateAdapter {
    public ConfirmBookingState(StatesContext context, Data data){
        super(context, data);
    }

    @Override
    public States getState() {
        return States.CONFIRM_BOOKING;
    }

    @Override
    public boolean goTripDetails() {
        setState(States.TRIP_DETAILS);
        return true;
    }

    @Override
    public boolean goChooseCar() {
        setState(States.CHOOSE_CAR);
        return true;
    }
    @Override
    public boolean goMainMenu() {
        setState(States.MAIN_MENU);
        return true;
    }
}
