package pt.isec.gps.team11.model.fsm.implementation;

import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.fsm.StateAdapter;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

public class BookingState extends StateAdapter {
    public BookingState(StatesContext context, Data data){
        super(context, data);
    }

    @Override
    public States getState() {
        return States.BOOKING;
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
