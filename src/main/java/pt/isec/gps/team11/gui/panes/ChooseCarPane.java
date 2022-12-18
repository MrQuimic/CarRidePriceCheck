package pt.isec.gps.team11.gui.panes;

//import com.almasb.fxgl.core.collection.Array;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.panes.components.*;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import javafx.scene.layout.*;
import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.fsm.States;

import java.util.ArrayList;

public class ChooseCarPane extends BorderPane {

    CRPCManager crpcManager;
    MyBrowser myBrowser;
    VBox form;
    ArrayList<Car> carNames;
    ArrayList<ImageView> carViews;
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
    Button btnConfirm,btnBack;

    HBox cars,hboxButtons;

    Car currentCar;

    boolean alreadyGotTheCars;

    public ChooseCarPane(CRPCManager crpcManager) {
        this.crpcManager = crpcManager;
        this.myBrowser = new MyBrowser(crpcManager);
        carNames = new ArrayList<>();
        carViews = new ArrayList<>();
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Font timesNewRoman = Font.font("TimesRoman", FontWeight.BOLD, 16);
        Font timesNewRomanNormal = Font.font("TimesRoman", FontWeight.NORMAL, 14);
        Label chooseCarLabel = new Label("Choose the car you desire");
        chooseCarLabel.setFont(timesNewRoman);
        chooseCarLabel.setTextFill(Color.BLACK);

        registrationForm = new RegistrationForm(crpcManager);
        loginForm = new LoginForm(crpcManager);

        loginForm.setPadding(new Insets(20, 0, 0, 0));

        loggedIn = new Alert(Alert.AlertType.ERROR);
        loggedIn.setTitle("User not logged in");
        loggedIn.setContentText("You must be logged in order to proceed.");

        chooseCarAlert = new Alert(Alert.AlertType.ERROR);
        chooseCarAlert.setTitle("Choose car error");
        chooseCarAlert.setContentText("Its mandatory to choose a car.");

        carChoosen = new Label();
        carChoosen.setFont(timesNewRoman);
        carChoosen.setTextFill(Color.BLACK);
        carChoosen.setText("Chosen car: ");

        CSSManager.applyCSS(this, "mystyle.css");

        form = new VBox();
        form.getChildren().add(chooseCarLabel);
        form.setSpacing(35);

        cars = new HBox();

        cars.setSpacing(35);

        form.getChildren().addAll(cars);

        VBox tripInfo = new VBox();

        Label tripsInfos = new Label();
        tripsInfos.setText("Trip information");
        tripsInfos.setFont(timesNewRoman);
        tripsInfos.setTextFill(Color.BLACK);

        startAddress.setFont(timesNewRomanNormal);
        startAddress.setTextFill(Color.BLACK);

        endAddress.setFont(timesNewRomanNormal);
        endAddress.setTextFill(Color.BLACK);

        directions.setFont(timesNewRomanNormal);
        directions.setTextFill(Color.BLACK);

        passengers.setFont(timesNewRomanNormal);
        passengers.setTextFill(Color.BLACK);

        suitcases.setFont(timesNewRomanNormal);
        suitcases.setTextFill(Color.BLACK);

        departureDate.setFont(timesNewRomanNormal);
        departureDate.setTextFill(Color.BLACK);

        departureTime.setFont(timesNewRomanNormal);
        departureTime.setTextFill(Color.BLACK);

        waitingTime.setFont(timesNewRomanNormal);
        waitingTime.setTextFill(Color.BLACK);
        waitingTime.setPadding(new Insets(0, 0, 30, 0));
        btnConfirm = new Button("Submit Car");
        btnConfirm.setId("mbtnSubmit");

        tripInfo.getChildren().addAll(tripsInfos, startAddress, endAddress, carChoosen, directions, passengers, suitcases, departureDate, departureTime, waitingTime, btnConfirm);

        VBox tripPriceInfo = new VBox();

        kilometers.setFont(timesNewRomanNormal);
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
        departureTime.setText("Departure Time: ");
        waitingTime.setText("Waiting time: ");
        kilometers.setText("Distance: ");
        price.setText("Price: ");


        form.setPadding(new Insets(0, 0, 0, 40));
        form.getChildren().addAll(hForm);
        this.setCenter(form);
    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });

        btnConfirm.setOnAction(actionEvent -> {
            if (carChoosen.getText().length() <= 13) {
                chooseCarAlert.show();
            }
            if (!crpcManager.isLogged()) {
                loggedIn.show();
            }
            if (carChoosen.getText().length() >= 13 && crpcManager.isLogged()) {
                crpcManager.saveCar(currentCar);
                crpcManager.goConfirmBooking();
            }
        });

    }

    private void configAdapter() {
    }

    private void update() {
        if (crpcManager.getState() == States.CHOOSE_CAR) {
            if (!alreadyGotTheCars) {
                carNames = crpcManager.getSuitableCars();

                for (Car carName : carNames) {
                    ImageView car = new ImageView(ImageManager.getImage(carName.getImage()));
                    car.setId("carView");
                    car.setFitHeight(110);
                    car.setPreserveRatio(true);
                    carViews.add(car);
                    cars.getChildren().add(car);
                }

                for (int i = 0; i < carViews.size(); ++i) {
                    int finalI = i;
                    carViews.get(i).setOnMouseClicked(actionEvent -> {
                        setCarChosen(finalI);
                    });
                }

                alreadyGotTheCars = true;
            }


            startAddress.setText("Start Address: " + crpcManager.getTripOrigin());
            endAddress.setText("End Address: " + crpcManager.getTripDestination());
            if (crpcManager.getCurrentTrip().isOneWay())
                directions.setText("Directions: One Way");
            else
                directions.setText("Directions: Return Trip");
            passengers.setText("Passengers: " + crpcManager.getCurrentTrip().getNumberOfPassengers());
            suitcases.setText("Suitcases: " + crpcManager.getCurrentTrip().getNumberOfLuggage());
            departureDate.setText(crpcManager.getCurrentTrip().getStringDate() == null ? "Departure date: not defined" : "Departure date: " + crpcManager.getCurrentTrip().getStringDate());
            departureTime.setText(crpcManager.getCurrentTrip().getStringTime() == null ? "Departure time: not defined" : "Departure time: " + crpcManager.getCurrentTrip().getStringTime());
            waitingTime.setText("Waiting time: " + crpcManager.getCurrentTrip().getExtraWaitingTime());
            kilometers.setText("Distance: " + crpcManager.getDistanceOfTrip());
            price.setText("Price: " + crpcManager.getCostOfTrip());
            configAdapter();
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }

    private void setCarChosen(int index) {
        String car = carNames.get(index).getBrand() + " " + carNames.get(index).getModel();
        carChoosen.setText("Chosen car: " + car);
        currentCar = new Car(carNames.get(index));
    }

}
