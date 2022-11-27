package pt.isec.gps.team11.model.fsm;

import pt.isec.gps.team11.model.data.Data;
import pt.isec.gps.team11.model.fsm.implementation.MainMenuState;

public class StatesContext {
    private final Data data;
    private IStates state;

    public StatesContext(){
        this.data = new Data();
        this.state = new MainMenuState(this, data);
    }

    public States getState(){
        return this.state.getState();
    }

    public void setState(IStates state){
        this.state = state;
    }
}
