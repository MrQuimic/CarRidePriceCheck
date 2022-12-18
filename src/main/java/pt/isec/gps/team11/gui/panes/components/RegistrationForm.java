package pt.isec.gps.team11.gui.panes.components;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.fsm.States;

import java.beans.PropertyChangeSupport;

public class RegistrationForm extends BorderPane {
    CRPCManager crpcManager;
    VBox vbox;
    HBox hbox1, hBox2, hBox3, hBox4;
    TextField email, phoneNumber, firstName, lastName, address, nif;
    PasswordField password;
    Alert registerAlert, numberAlert;
    PropertyChangeSupport pcs;
    PasswordField confirmPassword;
    Button btnRegister;

    public RegistrationForm(CRPCManager crpcManager) {
        this.crpcManager = crpcManager;
        pcs = new PropertyChangeSupport(this);
        createViews();
        registerHandlers();
        update();
    }


    private void createViews() {
        Font timesNewRoman = Font.font("TimesRoman", FontWeight.BOLD, 20);
        Label registerLabel = new Label("Register your account");
        registerLabel.setFont(timesNewRoman);
        registerLabel.setTextFill(Color.BLACK);

        btnRegister = new Button("Register and book");
        btnRegister.setId("btnRegLogin");

        registerAlert = new Alert(Alert.AlertType.ERROR);
        registerAlert.setTitle("Register error");
        registerAlert.setContentText("Please fulfill all the parameters.");

        numberAlert = new Alert(Alert.AlertType.ERROR);
        numberAlert.setTitle("Register error");
        numberAlert.setContentText("Please insert a numeric value for the phone number and nif.");

        email = new TextField();
        email.setPromptText("Your email here...");
        phoneNumber = new TextField();
        phoneNumber.setPromptText("+351 00 000 000");
        hbox1 = new HBox(email, phoneNumber);
        hbox1.setSpacing(10);
        firstName = new TextField();
        firstName.setPromptText("Insert first name...");
        lastName = new TextField();
        lastName.setPromptText("Insert last name...");
        hBox2 = new HBox(firstName, lastName);
        hBox2.setSpacing(10);

        address = new TextField();
        address.setPromptText("Insert your address...");
        nif = new TextField();
        nif.setPromptText("Insert your nif...");
        hBox3 = new HBox(address, nif);
        hBox3.setSpacing(10);


        password = new PasswordField();
        password.setPromptText("Insert your password...");
        confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm your password...");
        hBox4 = new HBox(password, confirmPassword);
        hBox4.setSpacing(10);


        vbox = new VBox(registerLabel, hbox1, hBox2, hBox3, hBox4, btnRegister);
        vbox.setSpacing(20);
        this.setCenter(vbox);
    }

    private void registerHandlers() {

        btnRegister.setOnAction(actionEvent -> {
            if (email.getText().trim().isEmpty() ||
                    !email.getText().contains("@") ||
                    phoneNumber.getText().trim().isEmpty() ||
                    firstName.getText().trim().isEmpty() ||
                    lastName.getText().trim().isEmpty() ||
                    address.getText().trim().isEmpty() ||
                    nif.getText().trim().isEmpty() ||
                    password.getText().trim().isEmpty() || !password.getText().equals(confirmPassword.getText()) ||
                    phoneNumber.getText().length() != 9 || nif.getText().length() != 9)
            {
                registerAlert.show();
            } else {
                try {
                    Integer.parseInt(phoneNumber.getText());
                    Integer.parseInt(nif.getText());

                } catch(NumberFormatException e) {
                    numberAlert.show();
                    return;
                }
                String emailAux = email.getText();
                crpcManager.setLogin(emailAux);
                crpcManager.setIsLogged();
                if (crpcManager.getState() != States.CHOOSE_CAR)
                    crpcManager.goToPreviousState();
            }
        });

        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void update() {
        this.setVisible(!crpcManager.isLogged());
        this.email.clear();
        this.phoneNumber.clear();
        this.firstName.clear();
        this.lastName.clear();
        this.address.clear();
        this.nif.clear();
        this.password.clear();
        this.confirmPassword.clear();
    }
}
