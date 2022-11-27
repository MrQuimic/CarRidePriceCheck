package pt.isec.gps.team11.model;

import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.fsm.IStates;
import pt.isec.gps.team11.model.fsm.States;
import pt.isec.gps.team11.model.fsm.StatesContext;

import java.beans.PropertyChangeListener;
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
        pcs = new PropertyChangeSupport(this);
       this.menuOpt = MenuOpt.BOOKING;
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

    private void configAdapter() {

    }
    /**
     * Add property change listener.
     *
     * @param listener the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void setMenuOpt(MenuOpt menuOpt) {
        this.menuOpt = menuOpt;
        System.out.println(menuOpt);

        pcs.firePropertyChange(null, null, null);
    }

}