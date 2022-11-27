package pt.isec.gps.team11.model;

import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

import java.beans.PropertyChangeSupport;

/**
 * The type Gpe manager.
 */
public class CRPCManager {
    private StatesContext fsm;

    PropertyChangeSupport pcs;

    MenuOpt menuOpt;



    /**
     * Instantiates a new Gpe manager.
     */
    public CRPCManager() {
        this.fsm = new StatesContext();
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



    public void setMenuOpt(MenuOpt menuOpt) {
        this.menuOpt = menuOpt;
        pcs.firePropertyChange(null, null, null);
    }

}