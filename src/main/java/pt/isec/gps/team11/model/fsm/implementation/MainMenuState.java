package pt.isec.gps.team11.model.fsm.implementation;

import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.fsm.StateAdapter;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

public class MainMenuState extends StateAdapter {
    public MainMenuState(StatesContext context, Data data){
        super(context, data);
    }

    @Override
    public States getState() {
        return States.MAIN_MENU;
    }

    @Override
    public boolean goBooking() {
        setState(States.BOOKING);
        return true;
    }

    @Override
    public boolean goListTrips() {
        setState(States.LIST_TRIPS);
        return true;
    }
}
