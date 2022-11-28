package pt.isec.gps.team11.gui.panes.utils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.isec.gps.team11.model.CRPCManager;

public class LoginForm extends BorderPane {
    CRPCManager crpcManager;
    VBox vBox;
    TextField email;
    TextField password;
    Button btnLogin;

    public LoginForm(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        createViews();
        registerHandlers();
    }

    private void registerHandlers() {
        btnLogin.setOnAction(actionEvent -> {

        });
    }

    private void createViews() {
        Font timesNewRoman = new Font("TimesRoman", 20);
        Label loginLabel = new Label("Login into your account:");
        loginLabel.setFont(timesNewRoman);
        loginLabel.setTextFill(Color.BLACK);

        btnLogin = new Button("Login and Book");
        btnLogin.setFont(timesNewRoman);
        email = new TextField("Enter your email");
        email.setMaxWidth(200);
        password = new TextField("Enter your password");
        password.setMaxWidth(200);
        vBox = new VBox(loginLabel, email, password, btnLogin);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }
}
