package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.LoginForm;
import pt.isec.gps.team11.gui.panes.utils.MenuTop;
import pt.isec.gps.team11.gui.panes.utils.RegistrationForm;
import pt.isec.gps.team11.model.CRPCManager;

public class AuthenticationPane extends BorderPane {
    CRPCManager crpcManager;
    LoginForm loginForm;
    RegistrationForm registrationForm;
    HBox hbox;


    public AuthenticationPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        loginForm = new LoginForm(crpcManager);
        registrationForm = new RegistrationForm(crpcManager);
        hbox = new HBox(loginForm, registrationForm);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setSpacing(100);
        this.setCenter(hbox);
    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void configAdapter() {
    }

    private void update() {
        if (crpcManager.getMenuOpt() == MenuOpt.AUTHENTICATION) {
            configAdapter();
            this.setVisible(true);
            return;
        }else{
            this.setVisible(false);
        }

    }
}
