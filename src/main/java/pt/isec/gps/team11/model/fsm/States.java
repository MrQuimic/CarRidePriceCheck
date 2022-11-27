package pt.isec.gps.team11.model.fsm;

import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.fsm.implementation.*;

public enum States {
    MAIN_MENU,
    LIST_TRIPS,
    BOOKING, CHOOSE_CAR, CONFIRM_BOOKING, TRIP_DETAILS;

    public IStates createState(StatesContext context, Data data){
        return switch(this){
            case MAIN_MENU -> new MainMenuState(context, data);
            case LIST_TRIPS -> new ListTripsState(context, data);
            case BOOKING -> new BookingState(context, data);
            case CHOOSE_CAR -> new ChooseCarState(context, data);
            case CONFIRM_BOOKING -> new ConfirmBookingState(context, data);
            case TRIP_DETAILS -> new TripDetailsState(context, data);
        };
    }
}
