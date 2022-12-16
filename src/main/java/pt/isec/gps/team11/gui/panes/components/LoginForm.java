package pt.isec.gps.team11.gui.panes.components;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;

import java.beans.PropertyChangeSupport;

public class LoginForm extends BorderPane {
    CRPCManager crpcManager;
    VBox vBox, vBox2;
    TextField email;
    PasswordField password;
    Button btnLogin;
    Alert loginAlert;
    PropertyChangeSupport pcs;

    public LoginForm(CRPCManager crpcManager) {
        this.crpcManager = crpcManager;
        pcs = new PropertyChangeSupport(this);
        createViews();
        registerHandlers();
    }

    private void registerHandlers() {

        btnLogin.setOnAction(actionEvent -> {
            String emailAux;
            String passwordAux;
            emailAux = email.getText();
            passwordAux = password.getText();

            if (crpcManager.confirmLogin(emailAux, passwordAux)) {
                crpcManager.setLogin(emailAux);

                if (crpcManager.getPreviousState() == States.IDLE) {
                    crpcManager.goMainMenu();

                } else {
                    crpcManager.goToPreviousState();
                }

            } else {
                loginAlert.show();
            }
        });

        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void createViews() {
        Font timesNewRoman = Font.font("TimesRoman", FontWeight.BOLD, 20);
        Label loginLabel = new Label("Login into your account:");
        Text loginUsers = new Text("Admin user: admin@gps \nClient user: client@gps\n");
        loginLabel.setFont(timesNewRoman);
        loginLabel.setTextFill(Color.BLACK);
        loginAlert = new Alert(Alert.AlertType.ERROR);
        loginAlert.setTitle("Login Error");
        loginAlert.setContentText("Please insert both valid username and password!");

        btnLogin = new Button("Login and Book");
        btnLogin.setId("btnRegLogin");
        email = new TextField();
        email.setPromptText("Enter your email");
        email.setMaxWidth(200);
        password = new PasswordField();
        password.setPromptText("Enter your password");
        password.setMaxWidth(200);
        vBox = new VBox(loginLabel, email, password, btnLogin);
        vBox.setSpacing(10);
        vBox2 = new VBox(vBox, loginUsers);
        vBox2.setSpacing(50);
        this.setCenter(vBox2);
    }

    private void update() {

        this.setVisible(!crpcManager.isLogged());
    }
}
