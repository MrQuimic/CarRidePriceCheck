package pt.isec.gps.team11.gui.panes.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.model.CRPCManager;

import java.beans.PropertyChangeSupport;

public class LoginForm extends BorderPane {
    CRPCManager crpcManager;
    VBox vBox;
    TextField email;
    TextField password;
    Button btnLogin;
    Alert loginAlert;
    PropertyChangeSupport pcs;

    public LoginForm(CRPCManager crpcManager){
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

            if(crpcManager.confirmLogin(emailAux, passwordAux)) {
                crpcManager.setLogin(emailAux);
            }
            else{
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
        loginLabel.setFont(timesNewRoman);
        loginLabel.setTextFill(Color.BLACK);
        loginAlert = new Alert(Alert.AlertType.ERROR);
        loginAlert.setTitle("Login Error");
        loginAlert.setContentText("Please insert both valid username and password!");

        btnLogin = new Button("Login and Book");
        btnLogin.setFont(timesNewRoman);
        btnLogin.setId("btnRegLogin");
        email = new TextField();
        email.setPromptText("Enter your email");
        email.setMaxWidth(200);
        password = new TextField();
        password.setPromptText("Enter your password");
        password.setMaxWidth(200);
        vBox = new VBox(loginLabel, email, password, btnLogin);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }

    private void update(){
        if(crpcManager.isLogged())
            this.setVisible(false);
    }
}
