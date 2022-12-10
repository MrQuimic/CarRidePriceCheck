package pt.isec.gps.team11.gui.panes;

//import com.almasb.fxgl.core.collection.Array;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.components.*;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import javafx.scene.layout.*;
import pt.isec.gps.team11.model.fsm.States;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Objects;

public class ChooseCarPane extends BorderPane {

    CRPCManager crpcManager;
    MyBrowser myBrowser;
    VBox form;
    ArrayList<String> carNames;
    ImageView[] carViews;
    Label carChoosen;
    Label startAddress = new Label();
    Label endAddress = new Label();
    Label directions = new Label();
    Label passengers = new Label();
    Label suitcases = new Label();
    Label departureDate = new Label();
    Label departureTime = new Label();
    Label waitingTime = new Label();
    Label kilometers = new Label();
    Label price = new Label();

    RegistrationForm registrationForm;

    LoginForm loginForm;
    Alert chooseCarAlert;

    Alert loggedIn;
    Button btnConfirm;

    public ChooseCarPane(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        this.myBrowser = new MyBrowser(crpcManager);
        carNames = new ArrayList<>();
        carViews = new ImageView[4];
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Font timesNewRoman = Font.font("TimesRoman", FontWeight.BOLD, 20);

        Label chooseCarLabel = new Label("Choose the car you desire");
        chooseCarLabel.setFont(timesNewRoman);
        chooseCarLabel.setTextFill(Color.BLACK);

        registrationForm = new RegistrationForm(crpcManager);
        loginForm = new LoginForm(crpcManager);
        loginForm.setPadding(new Insets(20, 0,0,0));

        loggedIn = new Alert(Alert.AlertType.ERROR);
        loggedIn.setTitle("User not logged in");
        loggedIn.setContentText("You must be logged in order to procede.");

        chooseCarAlert = new Alert(Alert.AlertType.ERROR);
        chooseCarAlert.setTitle("Choose car error");
        chooseCarAlert.setContentText("Its mandatory to choose a car.");

        carChoosen = new Label();
        carChoosen.setFont(timesNewRoman);
        carChoosen.setTextFill(Color.BLACK);
        carChoosen.setText("Choosen car: ");

        CSSManager.applyCSS(this,"mystyle.css");

        form = new VBox();
        form.getChildren().add(chooseCarLabel);
        form.setSpacing(35);

        HBox cars = new HBox();

        carNames.add("cars/Audi_A3.png");
        carNames.add("cars/Renault_Space.png");
        carNames.add("cars/Seat_Ibiza.png");
        carNames.add("cars/Tesla_Y.png");


        cars.setSpacing(35);

        int counter = 0;
        for(String carName : carNames)
        {
            carViews[counter] = new ImageView(ImageManager.getImage(carName));
            carViews[counter].setId("carView");
            carViews[counter].setFitHeight(110);
            carViews[counter].setPreserveRatio(true);
            cars.getChildren().add(carViews[counter]);
            counter++;
        }

        form.getChildren().addAll(cars);

        VBox tripInfo = new VBox();

        Label tripsInfos = new Label();
        tripsInfos.setText("Trip information");
        tripsInfos.setFont(timesNewRoman);
        tripsInfos.setTextFill(Color.BLACK);

        startAddress.setFont(timesNewRoman);
        startAddress.setTextFill(Color.BLACK);

        endAddress.setFont(timesNewRoman);
        endAddress.setTextFill(Color.BLACK);

        directions.setFont(timesNewRoman);
        directions.setTextFill(Color.BLACK);

        passengers.setFont(timesNewRoman);
        passengers.setTextFill(Color.BLACK);

        suitcases.setFont(timesNewRoman);
        suitcases.setTextFill(Color.BLACK);

        departureDate.setFont(timesNewRoman);
        departureDate.setTextFill(Color.BLACK);

        departureTime.setFont(timesNewRoman);
        departureTime.setTextFill(Color.BLACK);

        waitingTime.setFont(timesNewRoman);
        waitingTime.setTextFill(Color.BLACK);

        tripInfo.getChildren().addAll(tripsInfos, startAddress, endAddress,carChoosen, directions, passengers, suitcases, departureDate, departureTime, waitingTime);

        VBox tripPriceInfo = new VBox();

        kilometers.setFont(timesNewRoman);
        kilometers.setTextFill(Color.BLACK);


        price.setFont(timesNewRoman);
        price.setTextFill(Color.BLACK);

        tripPriceInfo.getChildren().addAll(kilometers, price, loginForm);

        VBox authenticationForms = new VBox();
        authenticationForms.getChildren().add(registrationForm);

        HBox hForm = new HBox();
        hForm.setSpacing(100);
        hForm.getChildren().addAll(tripInfo, tripPriceInfo, authenticationForms);


        startAddress.setText("Start Address: ");
        endAddress.setText("End Address: ");
        directions.setText("Directions: ");
        passengers.setText("Passengers: ");
        suitcases.setText("Suitcases: ");
        departureDate.setText("Departure Date");
        departureTime.setText("Departure Time: " );
        waitingTime.setText("Waiting time: ");
        kilometers.setText("Distance: ");
        price.setText("Price: ");

        btnConfirm = new Button("Confirm");

        form.setPadding(new Insets(0,0,0,40));
        form.getChildren().addAll(hForm, btnConfirm);
        this.setCenter(form);
    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });

        carViews[0].setOnMouseClicked(actionEvent ->{
            setCarChoosen(0);
        });

        carViews[1].setOnMouseClicked(actionEvent ->{
            setCarChoosen(1);
        });

        carViews[2].setOnMouseClicked(actionEvent ->{
            setCarChoosen(2);
        });

        carViews[3].setOnMouseClicked(actionEvent ->{
            setCarChoosen(3);
        });

        btnConfirm.setOnAction(actionEvent -> {
            if(carChoosen.getText().length() == 13){
                chooseCarAlert.show();
            }
            if(!crpcManager.isLogged()){
                loggedIn.show();
            }
            if(carChoosen.getText().length() != 13 && crpcManager.isLogged())
                crpcManager.goConfirmBooking();
        });

    }

    private void configAdapter() {
    }

    private void update() {
        if (crpcManager.getState() == States.CHOOSE_CAR) {
            startAddress.setText("Start Address: " + crpcManager.getTripOrigin());
            endAddress.setText("End Address: " + crpcManager.getTripDestination());
            directions.setText("Directions: " + crpcManager.getCurrentTrip().isOneWay());
            passengers.setText("Passengers: " + crpcManager.getCurrentTrip().getNumberOfPassengers());
            suitcases.setText("Suitcases: " + crpcManager.getCurrentTrip().getNumberOfLuggage());
            departureDate.setText(crpcManager.getCurrentTrip().getDate() == null ? "Departure date: not defined" : "Departure date: " + crpcManager.getCurrentTrip().getDate());
            departureTime.setText(crpcManager.getCurrentTrip().getDepartureTime() == null ? "Departure time: not defined" : "Departure time: " + crpcManager.getCurrentTrip().getDepartureTime());
            waitingTime.setText("Waiting time: " + crpcManager.getCurrentTrip().getExtraWaitingTime());
            kilometers.setText("Distance: " + crpcManager.getDistanceOfTrip());
            price.setText("Price: " + crpcManager.getCostOfTrip());
            configAdapter();
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }

    private void setCarChoosen(int index){
        carChoosen.setText("Choosen car: " + carNames.get(index).split("/")[1].split("\\.")[0]);
    }

}
