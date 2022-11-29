package pt.isec.gps.team11.gui.panes.utils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.model.CRPCManager;

public class RegistrationForm extends BorderPane {
    CRPCManager crpcManager;
    VBox vbox;
    HBox hbox1;
    HBox hBox2;
    HBox hBox3;
    HBox hBox4;
    TextField email;
    TextField phoneNumber;
    TextField firstName;
    TextField lastName;
    TextField address;
    TextField nif;
    PasswordField password;

    PasswordField confirmPassword;
    Button btnRegister;

    public RegistrationForm(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        createViews();
        registerHandlers();
    }

    private void registerHandlers() {
        btnRegister.setOnAction(actionEvent -> {

        });
    }

    private void createViews() {
        Font timesNewRoman = Font.font("TimesRoman", FontWeight.BOLD, 20);
        Label registerLabel = new Label("Register your account");
        registerLabel.setFont(timesNewRoman);
        registerLabel.setTextFill(Color.BLACK);

        btnRegister = new Button("Register and book");
        btnRegister.setId("btnRegLogin");

        email = new TextField("Your email here...");
        phoneNumber = new TextField("+351 000 000 000");
        hbox1 = new HBox(email, phoneNumber);
        hbox1.setSpacing(10);
        firstName = new TextField("Insert first name...");
        lastName = new TextField("Insert last name...");
        hBox2 = new HBox(firstName, lastName);
        hBox2.setSpacing(10);

        address = new TextField("Insert your address...");
        nif = new TextField("Insert your nif...");
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

}
