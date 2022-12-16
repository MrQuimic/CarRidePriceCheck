package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.components.LoginForm;
import pt.isec.gps.team11.gui.panes.components.RegistrationForm;
import pt.isec.gps.team11.model.CRPCManager;

public class AuthenticationPane extends BorderPane {
    CRPCManager crpcManager;
    LoginForm loginForm;
    RegistrationForm registrationForm;
    HBox hbox;


    public AuthenticationPane(CRPCManager crpcManager) {
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
        hbox.setPadding(new Insets(30, 0, 0, 0));
        hbox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        hbox.setStyle("-fx-background-color: rgba(73,69,69,0.08);-fx-font-family: 'Courrier New'; -fx-font-size: 16;");
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

        if (crpcManager.isLogged()) {
            configAdapter();
            this.setVisible(false);
        }

        if (crpcManager.getMenuOpt() == MenuOpt.AUTHENTICATION) {
            configAdapter();
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }

    }
}
