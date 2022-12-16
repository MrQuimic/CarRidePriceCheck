package pt.isec.gps.team11.gui.panes.components;

import com.google.maps.model.AddressComponentType;
import com.google.maps.model.PlaceDetails;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.utils.AutoCompleteAddressField;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookForm extends BorderPane {
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    VBox vbAdressesAndOptions, vbAdresses, vbOptions, vbAdressesWithTitle, vbOptionsWithTitle;
    DatePicker checkInDatePicker;

    VBox vbStartAdress, vbEndAdress, vbDirections, vbExtraWaitTime, vbPassengers, vbSuitcases, vbDepartureDate, vbDepartureTime, vbTolls, vbDepartureTimeHour, vbDepartureTimeMinute;
    Label lbStartAdress, lbEndAdress, lbDirections, lbExtraWaitTime, lbPassengers, lbSuitcases, lbDepartureDate, lbDepartureTime, lbTolls, lbAdressesTitle, lbOptionsTitle, lbHour, lbMinute;
    HBox hbPassengersSuitcases, hbDepartureDateAndImage, hbDepartureTimeHourMinutes, submitBtns, hDirectTolls;
    Button btnSubmit, btnReset, btnMapUpdate;
    TextField tfExtraWaitTime;
    ChoiceBox cbDirections, cbPassengers;
    ChoiceBox<String> cbSuitcases;
    ChoiceBox<String> cbTolls;
    DatePicker dpDepartureDate;
    ComboBox<String> cbHour;
    ComboBox<String> cbMinute;

    AutoCompleteAddressField originA = new AutoCompleteAddressField();
    AutoCompleteAddressField destinA = new AutoCompleteAddressField();
    TextField Destin1 = new TextField();
    TextField Origin1 = new TextField();

    MyBrowser myBrowser;

    public BookForm(CRPCManager crpcManager, MyBrowser myBrowser) {
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;
        pcs = new PropertyChangeSupport(this);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this, "styles.css");
        Font font = Font.font("Verdana", FontWeight.BOLD, 12);
        Font fontSmall = Font.font("Verdana", FontWeight.BOLD, 10);
        //VBox of the Addresses and the Options
        vbAdressesAndOptions = new VBox();
        vbAdressesAndOptions.setAlignment(Pos.CENTER_LEFT);

        //Adresses VBox With Title
        vbAdressesWithTitle = new VBox();
        vbAdressesWithTitle.setAlignment(Pos.CENTER);

        lbAdressesTitle = new Label("Adresses");
        lbAdressesTitle.setPadding(new Insets(20, 0, 5, 0));
        lbAdressesTitle.setFont(font);
        //Adresses VBox
        vbAdresses = new VBox();
        vbAdresses.setAlignment(Pos.CENTER);

        //Start Adress
        vbStartAdress = new VBox();
        vbStartAdress.setAlignment(Pos.CENTER_LEFT);

        lbStartAdress = new Label("Start address");
        lbStartAdress.setAlignment(Pos.CENTER_LEFT);
        lbStartAdress.setPadding(new Insets(10, 0, 5, 0));
        lbStartAdress.setFont(fontSmall);


        originA.setId("originA");
        destinA.setId("destinA");
        originA.setMaxWidth(250);
        destinA.setMaxWidth(250);

        Origin1.setPromptText("Origin1");
        Destin1.setPromptText("Destin1");

        if (originA.getText().equals("")) {
            originA.setText("Coimbra, Portugal");
            destinA.setText("Porto, Portugal");
        }

        vbStartAdress.getChildren().addAll(lbStartAdress, originA);

        //End Adress
        vbEndAdress = new VBox();
        vbEndAdress.setAlignment(Pos.CENTER_LEFT);

        lbEndAdress = new Label("End address");
        lbEndAdress.setAlignment(Pos.CENTER_LEFT);
        lbEndAdress.setPadding(new Insets(10, 0, 5, 0));
        lbEndAdress.setFont(fontSmall);

        vbEndAdress.getChildren().addAll(lbEndAdress, destinA);

        vbAdresses.getChildren().addAll(vbStartAdress, vbEndAdress);

        vbAdressesWithTitle.getChildren().addAll(lbAdressesTitle, vbAdresses);


        //Options VBox With Title
        vbOptionsWithTitle = new VBox();
        vbOptionsWithTitle.setAlignment(Pos.CENTER);

        lbOptionsTitle = new Label("Options");
        lbOptionsTitle.setPadding(new Insets(20, 0, 5, 0));
        lbOptionsTitle.setFont(font);
        //Options VBox
        vbOptions = new VBox();
        vbOptions.setAlignment(Pos.CENTER);

        //Tolls
        vbTolls = new VBox();
        vbTolls.setAlignment(Pos.CENTER_LEFT);

        lbTolls = new Label("Tolls:");
        lbTolls.setAlignment(Pos.CENTER_LEFT);
        cbTolls = new ChoiceBox<>();
        cbTolls.getItems().addAll("Yes", "No");
        cbTolls.setValue("Yes");
        vbTolls.getChildren().addAll(lbTolls, cbTolls);

        //Directions
        vbDirections = new VBox();
        vbDirections.setAlignment(Pos.CENTER_LEFT);

        lbDirections = new Label("Directions");
        lbDirections.setPadding(new Insets(0, 0, 5, 0));
        lbDirections.setFont(fontSmall);
        lbDirections.setAlignment(Pos.CENTER_LEFT);
        cbDirections = new ChoiceBox<>();
        cbDirections.getItems().addAll("One Way", "Return");
        cbDirections.setValue("One Way");

        vbDirections.getChildren().addAll(lbDirections, cbDirections);
        hDirectTolls = new HBox();
        hDirectTolls.getChildren().addAll(vbDirections, vbTolls);
        hDirectTolls.setAlignment(Pos.CENTER);
        hDirectTolls.setSpacing(40);
        //Extra Waiting Time
        vbExtraWaitTime = new VBox();
        vbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);

        lbExtraWaitTime = new Label("Extra Waiting Time (minutes)");
        lbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);
        lbExtraWaitTime.setPadding(new Insets(0, 0, 5, 0));
        lbExtraWaitTime.setFont(fontSmall);
        tfExtraWaitTime = new TextField();
        tfExtraWaitTime.setPromptText("0 minutes");

        vbExtraWaitTime.getChildren().addAll(lbExtraWaitTime, tfExtraWaitTime);

        //HBox for Passengers and Suitcases
        hbPassengersSuitcases = new HBox();
        hbPassengersSuitcases.setAlignment(Pos.CENTER);

        //Passengers
        vbPassengers = new VBox();
        vbPassengers.setAlignment(Pos.CENTER);

        lbPassengers = new Label("Passengers");
        lbPassengers.setAlignment(Pos.CENTER);
        lbPassengers.setPadding(new Insets(0, 0, 5, 0));
        lbPassengers.setFont(fontSmall);
        cbPassengers = new ChoiceBox<>();
        cbPassengers.getItems().addAll("1", "2", "3", "4", "5", "6", "7");
        cbPassengers.setValue("1");
        vbPassengers.getChildren().addAll(lbPassengers, cbPassengers);

        //Suitcases
        vbSuitcases = new VBox();
        vbSuitcases.setAlignment(Pos.CENTER);

        lbSuitcases = new Label("Suitcases");
        lbSuitcases.setAlignment(Pos.CENTER_RIGHT);
        lbSuitcases.setPadding(new Insets(0, 0, 5, 0));
        lbSuitcases.setFont(fontSmall);
        cbSuitcases = new ChoiceBox<>();
        cbSuitcases.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7");
        cbSuitcases.setValue("0");
        vbSuitcases.getChildren().addAll(lbSuitcases, cbSuitcases);


        //HBox for Departure Date and Image
        hbDepartureDateAndImage = new HBox();
        hbDepartureDateAndImage.setAlignment(Pos.CENTER_LEFT);

        //Passengers
        vbPassengers = new VBox();
        vbPassengers.setAlignment(Pos.CENTER);

        lbPassengers = new Label("Passengers");
        lbPassengers.setAlignment(Pos.CENTER_LEFT);
        lbPassengers.setPadding(new Insets(0, 0, 5, 0));
        lbPassengers.setFont(fontSmall);
        cbPassengers = new ChoiceBox();
        cbPassengers.getItems().addAll("1", "2", "3", "4", "5");
        cbPassengers.setValue("1");
        vbPassengers.getChildren().addAll(lbPassengers, cbPassengers);

        //Suitcases
        vbSuitcases = new VBox();
        vbSuitcases.setAlignment(Pos.CENTER);

        lbSuitcases = new Label("Suitcases");
        lbSuitcases.setAlignment(Pos.CENTER_RIGHT);
        lbSuitcases.setPadding(new Insets(0, 0, 5, 0));
        lbSuitcases.setFont(fontSmall);
        cbSuitcases = new ChoiceBox<>();
        cbSuitcases.getItems().addAll("0", "1", "2", "3", "4", "5");
        cbSuitcases.setValue("0");
        vbSuitcases.getChildren().addAll(lbSuitcases, cbSuitcases);


        //Departure Date

        ////////////////////////////////////////////////////////////////////////////////////////////////
        dpDepartureDate = new DatePicker();
        checkInDatePicker = new DatePicker();
        checkInDatePicker.setValue(LocalDate.now());
        dpDepartureDate.getEditor().setOpacity(1);
        dpDepartureDate.getEditor().setDisable(true);
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(checkInDatePicker.getValue().plusDays(0))) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        dpDepartureDate.setDayCellFactory(dayCellFactory);
        dpDepartureDate.setValue(checkInDatePicker.getValue().plusDays(0));

        ////////////////////////////////////////////////////////////////////////////////////////////////
        hbPassengersSuitcases.getChildren().addAll(vbPassengers, vbSuitcases);
        hbPassengersSuitcases.setSpacing(40);
        vbDepartureDate = new VBox();
        vbDepartureDate.setAlignment(Pos.CENTER);

        lbDepartureDate = new Label("Departure date");
        lbDepartureDate.setAlignment(Pos.CENTER_LEFT);
        lbDepartureDate.setPadding(new Insets(0, 0, 5, 0));
        lbDepartureDate.setFont(fontSmall);

        vbDepartureDate.getChildren().addAll(lbDepartureDate, dpDepartureDate);

        //parte de colocar a imagem aqui


        hbDepartureDateAndImage.getChildren().addAll(vbDepartureDate/*,cena da imagem*/);

        //HBox for Departure Time and Image
        hbDepartureTimeHourMinutes = new HBox();
        hbDepartureTimeHourMinutes.setAlignment(Pos.CENTER_LEFT);

        //Departure Time

        vbDepartureTime = new VBox();
        vbDepartureTime.setAlignment(Pos.CENTER);

        lbDepartureTime = new Label("Departure Time");
        lbDepartureTime.setAlignment(Pos.CENTER_LEFT);
        lbDepartureTime.setPadding(new Insets(0, 0, 5, 0));
        lbDepartureTime.setFont(fontSmall);

        //HBox for DepartureDateHour and DepartureDateMinutes
        hbDepartureTimeHourMinutes = new HBox();
        hbDepartureTimeHourMinutes.setAlignment(Pos.CENTER_LEFT);
        lbDepartureDate = new Label("Departure time");

        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        //calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        //calendar.get(Calendar.HOUR);        // gets hour in 12h format
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        //Hour
        vbDepartureTimeHour = new VBox();
        vbDepartureTimeHour.setAlignment(Pos.CENTER);


        lbHour = new Label("Hour");
        lbHour.setAlignment(Pos.CENTER_LEFT);
        lbHour.setPadding(new Insets(0, 0, 5, 0));
        lbHour.setFont(fontSmall);
        cbHour = new ComboBox<>();
        int hourStart = 0;

        if (dpDepartureDate.getValue().toString().equals(year + "-" + month + "-" + (dayOfMonth))) {


            if (calendar.get(Calendar.HOUR_OF_DAY) < 23) {
                hourStart = calendar.get(Calendar.HOUR_OF_DAY) + 1;
                cbHour.setValue(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY) + 1));
            } else {
                dpDepartureDate.setValue(checkInDatePicker.getValue().plusDays(1));
                cbHour.setValue(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
            }
        }


        for (int i = hourStart; i < 24; i++)
            cbHour.getItems().add(String.valueOf(i));


        vbDepartureTimeHour.getChildren().addAll(lbHour, cbHour);

        //Minutes
        vbDepartureTimeMinute = new VBox();
        vbDepartureTimeMinute.setAlignment(Pos.CENTER);

        lbMinute = new Label("Minutes");
        lbMinute.setAlignment(Pos.CENTER_RIGHT);
        lbMinute.setPadding(new Insets(0, 0, 5, 0));
        lbMinute.setFont(fontSmall);
        cbMinute = new ComboBox<String>();
        for (int i = 0; i < 59; i++)
            cbMinute.getItems().add(String.valueOf(i));

        cbMinute.setValue("0");
        cbMinute.setValue(String.valueOf(calendar.get(Calendar.MINUTE)));
        vbDepartureTimeMinute.getChildren().addAll(lbMinute, cbMinute);

        hbDepartureTimeHourMinutes.getChildren().addAll(vbDepartureTimeHour, vbDepartureTimeMinute);

        hbDepartureTimeHourMinutes.setAlignment(Pos.CENTER);
        hbDepartureTimeHourMinutes.setSpacing(40);
        vbDepartureTime.getChildren().addAll(lbDepartureTime, hbDepartureTimeHourMinutes);


        vbOptions.getChildren().addAll(hDirectTolls, vbExtraWaitTime, hbPassengersSuitcases, hbDepartureDateAndImage, vbDepartureTime);
        vbOptions.setSpacing(15);

        vbOptionsWithTitle.getChildren().addAll(lbOptionsTitle, vbOptions);

        submitBtns = new HBox();
        btnSubmit = new Button("Submit");
        btnMapUpdate = new Button("Map Update");
        btnReset = new Button("Reset");
        btnSubmit.setId("mbtnSubmit");
        btnMapUpdate.setId("mbtnSubmit");
        btnReset.setId("mbtnReset");
        submitBtns.getChildren().addAll(btnReset, btnMapUpdate, btnSubmit);
        submitBtns.setPadding(new Insets(20, 0, 0, 0));
        submitBtns.setSpacing(10);

        vbAdressesAndOptions.getChildren().addAll(vbAdressesWithTitle, vbOptionsWithTitle, submitBtns);


        this.setTop(vbAdressesAndOptions);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private void registerHandlers() {

        btnReset.setOnAction(actionEvent -> {
            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm());

            crpcManager.resetTripResults();

            clearForm();

            originA.setText("Coimbra, Portugal");
            destinA.setText("Porto, Portugal");
            checkInDatePicker.setValue(LocalDate.now());
        });
        btnMapUpdate.setOnAction(actionEvent -> {


            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm() + "?origin=" + originA.getText() + "&destin=" + destinA.getText() + "&style=" + "A" + "&tolls=" + cbTolls.getValue() + "&returnTrip=" + cbDirections.getValue());

        });

        btnSubmit.setOnAction(actionEvent -> {

                    int extraWaitTime;
                    String departureTime = null;
                    String departureDate = null;
                    boolean directions;
                    int nrPassengers = 1;
                    int nrSuitcases = 0;
                    boolean tolls;
                    String hour;
                    String minute;

                    if (tfExtraWaitTime.getText().isBlank()) {
                        extraWaitTime = 0;
                    } else {
                        try {
                            extraWaitTime = Integer.parseInt(tfExtraWaitTime.getText());
                        } catch (Exception e) {
                            alert("Insert a valid extra waiting minute!");
                            tfExtraWaitTime.setText("0");
                            return;
                        }
                    }

                    if (dpDepartureDate.getValue() == null) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Alert");
                        alert.setHeaderText(null);
                        alert.setContentText("The departure time is invalid");
                        alert.showAndWait();
                        clearForm();
                        return;
                    }

                    departureDate = dpDepartureDate.getValue().toString();

                    if (cbHour.getValue() != null) {
                        hour = cbHour.getValue().toString();
                        if (cbMinute.getValue() != null) {
                            minute = cbMinute.getValue().toString();
                            departureTime = hour + ":" + minute;
                        }
                    }

                    //return trip
                    directions = cbDirections.getValue().equals("One Way");

                    if (cbPassengers.getValue() != null) {
                        String s = cbPassengers.getValue().toString();
                        nrPassengers = Integer.parseInt(s);
                    }

                    if (cbSuitcases.getValue() != null) {
                        String s = cbSuitcases.getValue().toString();
                        nrSuitcases = Integer.parseInt(s);
                    }

                    AtomicBoolean exit = new AtomicBoolean(false);

                    tolls = cbTolls.getValue().equals("Yes");

                    crpcManager.book(directions, departureDate, extraWaitTime, nrSuitcases, nrPassengers, departureTime, tolls);

                    myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm() + "?origin=" + originA.getText() + "&destin=" + destinA.getText() + "&style=" + "A" + "&tolls=" + cbTolls.getValue() + "&returnTrip=" + cbDirections.getValue());
                    //String returnValue = (String) webEngine.executeScript("getRectArea()");

                    myBrowser.webEngine.getLoadWorker().stateProperty().addListener(
                            new ChangeListener() {
                                @Override
                                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                    if (newValue != Worker.State.SUCCEEDED) {
                                        return;
                                    }

                                    String returnValue = (String) myBrowser.webEngine.executeScript("results()");

                                    try {
                                        crpcManager.saveTripResults(returnValue);

                                        crpcManager.setTripOrigin(originA.getText());
                                        crpcManager.setTripDestination(destinA.getText());

                                        crpcManager.goChooseCAr();


                                    } catch (ArrayIndexOutOfBoundsException e) {

                                        originA.setText("Coimbra, Portugal");
                                        destinA.setText("Porto, Portugal");
                                        alert("Set a valid origin/destination");

                                    }

                                    myBrowser.webEngine.getLoadWorker().stateProperty().removeListener(this);
                                }
                            }
                    );
                }
        );


        originA.getEntryMenu().setOnAction((ActionEvent e) ->
        {

            ((MenuItem) e.getTarget()).addEventHandler(Event.ANY, (Event event) ->
            {
                if (originA.getLastSelectedObject() != null) {
                    originA.setText(originA.getLastSelectedObject().toString());
                    PlaceDetails place = AutoCompleteAddressField.getPlace((AutoCompleteAddressField.AddressPrediction) originA.getLastSelectedObject());
                    if (place != null) {
                        StringUtils StringUtils = null;
                        Destin1.setText(
                                StringUtils.join(
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.STREET_NUMBER),
                                        " ",
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.ROUTE))
                        );

                    } else {

                        Destin1.setText("Porto, Portugal");

                    }
                }
            });
        });

        destinA.getEntryMenu().setOnAction((ActionEvent e) ->
        {

            ((MenuItem) e.getTarget()).addEventHandler(Event.ANY, (Event event) ->
            {
                if (destinA.getLastSelectedObject() != null) {
                    destinA.setText(destinA.getLastSelectedObject().toString());
                    PlaceDetails place = AutoCompleteAddressField.getPlace((AutoCompleteAddressField.AddressPrediction) destinA.getLastSelectedObject());
                    if (place != null) {
                        StringUtils StringUtils = null;
                        Origin1.setText(
                                StringUtils.join(
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.STREET_NUMBER),
                                        " ",
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.ROUTE))
                        );

                    } else {
                        Origin1.setText("Coimbra, Portugal");

                    }
                }
            });
        });


        cbDirections.setOnAction(actionEvent -> {
            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm() + "?origin=" + originA.getText() + "&destin=" + destinA.getText() + "&style=" + "A" + "&tolls=" + cbTolls.getValue() + "&returnTrip=" + cbDirections.getValue());

        });
        cbTolls.setOnAction(actionEvent -> {
            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm() + "?origin=" + originA.getText() + "&destin=" + destinA.getText() + "&style=" + "A" + "&tolls=" + cbTolls.getValue() + "&returnTrip=" + cbDirections.getValue());

        });

        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    void clearForm() {
        cbDirections.setValue("One way");
        tfExtraWaitTime.setText("0");
        cbPassengers.setValue(1);
        cbDirections.setValue(0);
        cbTolls.setValue("Yes");
    }


    private void update() {

    }

    private void alert(String erro) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(erro);
        alert.show();
        //clearForm();
    }


}
