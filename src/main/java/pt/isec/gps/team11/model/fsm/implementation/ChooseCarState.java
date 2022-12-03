package pt.isec.gps.team11.model.fsm.implementation;

import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.fsm.StateAdapter;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

import java.util.ArrayList;

public class ChooseCarState extends StateAdapter {
    public ChooseCarState(StatesContext context, Data data){
        super(context, data);
    }

    @Override
    public States getState() {
        return States.CHOOSE_CAR;
    }

    @Override
    public boolean goConfirmBooking() {
        setState(States.CONFIRM_BOOKING);
        return true;
    }

    @Override
    public boolean goBooking() {
        setState(States.BOOKING);
        return true;
    }

    @Override
    public boolean goMainMenu() {
        setState(States.MAIN_MENU);
        return true;
    }

    @Override
    public ArrayList<Car> getSuitableCars(int lotation, int baggage) {
        return this.data.getSuitableCars(lotation, baggage);
    }
}
