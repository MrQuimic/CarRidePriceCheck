package pt.isec.gps.team11.model.fsm.implementation;

import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.data.Trip;
import pt.isec.gps.team11.model.fsm.StateAdapter;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

import java.util.ArrayList;

public class ListTripsState extends StateAdapter {
    public ListTripsState(StatesContext context, Data data){
        super(context, data);
    }

    @Override
    public States getState() {
        return States.LIST_TRIPS;
    }

    @Override
    public boolean goMainMenu() {
        setState(States.MAIN_MENU);
        return true;
    }

    @Override
    public ArrayList<Trip> getTrips() {
        //return data.getTrips();
        return null;
    }
}
