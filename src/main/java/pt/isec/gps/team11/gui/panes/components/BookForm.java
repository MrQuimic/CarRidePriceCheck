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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.apache.commons.lang3.StringUtils;
import pt.isec.gps.team11.MyBrowser;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.gui.panes.utils.ImageManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.utils.AutoCompleteAddressField;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BookForm extends BorderPane {
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    VBox vbAdressesAndOptions, vbAdresses, vbOptions, vbAdressesWithTitle, vbOptionsWithTitle;


    VBox vbStartAdress, vbEndAdress, vbDirections, vbExtraWaitTime, vbPassengers, vbSuitcases, vbDepartureDate, vbDepartureTime, vbTolls, vbDepartureTimeImg;
    Label lbStartAdress, lbEndAdress, lbDirections, lbExtraWaitTime, lbPassengers, lbSuitcases, lbDepartureDate, lbDepartureTime, lbTolls, lbAdressesTitle, lbOptionsTitle;
    HBox hbPassengersSuitcases, hbDepartureDateAndImage, hbDepartureTimeAndImage, submitBtns;
    Button btnSubmit, btnReset;
    TextField tfExtraWaitTime, tfDepartureTime;
    ChoiceBox cbDirections, cbPassengers, cbSuitcases, cbTolls;
    DatePicker dpDepartureDate;

    AutoCompleteAddressField originA = new AutoCompleteAddressField();
    AutoCompleteAddressField destinA = new AutoCompleteAddressField();
    TextField Destin1 = new TextField();
    TextField Origin1 = new TextField();

    MyBrowser myBrowser;

    public BookForm(CRPCManager crpcManager, MyBrowser myBrowser){
        this.crpcManager = crpcManager;
        this.myBrowser = myBrowser;
        pcs = new PropertyChangeSupport(this);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        Font font = Font.font("Verdana", FontWeight.BOLD, 12);
        Font fontSmall = Font.font("Verdana", FontWeight.BOLD, 10);
        //VBox of the Addresses and the Options
        vbAdressesAndOptions = new VBox();
        vbAdressesAndOptions.setAlignment(Pos.CENTER_LEFT);

        //Adresses VBox With Title
        vbAdressesWithTitle = new VBox();
        vbAdressesWithTitle.setAlignment(Pos.CENTER);

        lbAdressesTitle = new Label("Adresses");
        lbAdressesTitle.setPadding(new Insets(20,0,5,0));
        lbAdressesTitle.setFont(font);
        //Adresses VBox
        vbAdresses = new VBox();
        vbAdresses.setAlignment(Pos.CENTER);

        //Start Adress
        vbStartAdress = new VBox();
        vbStartAdress.setAlignment(Pos.CENTER_LEFT);

        lbStartAdress = new Label("Start adress");
        lbStartAdress.setAlignment(Pos.CENTER_LEFT);
        lbStartAdress.setPadding(new Insets(10,0,5,0));
        lbStartAdress.setFont(fontSmall);


        originA.setId("originA");
        destinA.setId("destinA");
        originA.setMaxWidth(250);
        destinA.setMaxWidth(250);

        Origin1.setPromptText("Origin1");
        Destin1.setPromptText("Destin1");

        if (originA.getText().equals("")){
            originA.setText("Coimbra, Portugal");
            destinA.setText("Porto, Portugal");
        }

        vbStartAdress.getChildren().addAll(lbStartAdress,originA);

        //End Adress
        vbEndAdress = new VBox();
        vbEndAdress.setAlignment(Pos.CENTER_LEFT);

        lbEndAdress = new Label("End adress");
        lbEndAdress.setAlignment(Pos.CENTER_LEFT);
        lbEndAdress.setPadding(new Insets(10,0,5,0));
        lbEndAdress.setFont(fontSmall);

        vbEndAdress.getChildren().addAll(lbEndAdress,destinA);

        vbAdresses.getChildren().addAll(vbStartAdress, vbEndAdress);

        vbAdressesWithTitle.getChildren().addAll(lbAdressesTitle,vbAdresses);



        //Options VBox With Title
        vbOptionsWithTitle = new VBox();
        vbOptionsWithTitle.setAlignment(Pos.CENTER);

        lbOptionsTitle = new Label("Options");
        lbOptionsTitle.setPadding(new Insets(20,0,5,0));
        lbOptionsTitle.setFont(font);
        //Options VBox
        vbOptions = new VBox();
        vbOptions.setAlignment(Pos.CENTER);

        //Directions
        vbDirections = new VBox();
        vbDirections.setAlignment(Pos.CENTER_LEFT);

        lbDirections = new Label("Directions");
        lbDirections.setPadding(new Insets(0,0,5,0));
        lbDirections.setFont(fontSmall);
        lbDirections.setAlignment(Pos.CENTER_LEFT);
        cbDirections = new ChoiceBox();
        cbDirections.getItems().addAll("One Way", "Return");
        cbDirections.setValue("One Way");

        vbDirections.getChildren().addAll(lbDirections,cbDirections);

        //Extra Waiting Time
        vbExtraWaitTime = new VBox();
        vbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);

        lbExtraWaitTime = new Label("Extra Waiting Time (minutes)");
        lbExtraWaitTime.setAlignment(Pos.CENTER_LEFT);
        lbExtraWaitTime.setPadding(new Insets(0,0,5,0));
        lbExtraWaitTime.setFont(fontSmall);
        tfExtraWaitTime = new TextField();
        tfExtraWaitTime.setPromptText("0 minutes");

        vbExtraWaitTime.getChildren().addAll(lbExtraWaitTime,tfExtraWaitTime);

        //HBox for Passengers and Suitcases
        hbPassengersSuitcases = new HBox();
        hbPassengersSuitcases.setAlignment(Pos.CENTER_LEFT);

        //Passengers
        vbPassengers = new VBox();
        vbPassengers.setAlignment(Pos.CENTER);

        lbPassengers = new Label("Passengers");
        lbPassengers.setAlignment(Pos.CENTER_LEFT);
        lbPassengers.setPadding(new Insets(0,0,5,0));
        lbPassengers.setFont(fontSmall);
        cbPassengers = new ChoiceBox();
        cbPassengers.getItems().addAll("1", "2", "3", "4","5", "6", "7");
        cbPassengers.setValue("1");
        vbPassengers.getChildren().addAll(lbPassengers,cbPassengers);

        //Suitcases
        vbSuitcases = new VBox();
        vbSuitcases.setAlignment(Pos.CENTER);

        lbSuitcases = new Label("Suitcases");
        lbSuitcases.setAlignment(Pos.CENTER_RIGHT);
        lbSuitcases.setPadding(new Insets(0,0,5,0));
        lbSuitcases.setFont(fontSmall);
        cbSuitcases = new ChoiceBox();
        cbSuitcases.getItems().addAll("0", "1", "2", "3", "4","5", "6", "7");
        cbSuitcases.setValue("0");
        vbSuitcases.getChildren().addAll(lbSuitcases,cbSuitcases);

        hbPassengersSuitcases.getChildren().addAll(vbPassengers,vbSuitcases);
        hbPassengersSuitcases.setSpacing(10);

        //HBox for Departure Date and Image
        hbDepartureDateAndImage = new HBox();
        hbDepartureDateAndImage.setAlignment(Pos.CENTER_LEFT);

        //Departure Date
        vbDepartureDate = new VBox();
        vbDepartureDate.setAlignment(Pos.CENTER);

        lbDepartureDate = new Label("Departure date");
        lbDepartureDate.setAlignment(Pos.CENTER_LEFT);
        lbDepartureDate.setPadding(new Insets(0,0,5,0));
        lbDepartureDate.setFont(fontSmall);

        dpDepartureDate = new DatePicker();


        dpDepartureDate.setValue(LOCAL_DATE());

        vbDepartureDate.getChildren().addAll(lbDepartureDate,dpDepartureDate);

        //parte de colocar a imagem aqui


        hbDepartureDateAndImage.getChildren().addAll(vbDepartureDate/*,cena da imagem*/);

        //HBox for Departure Time and Image
        hbDepartureTimeAndImage = new HBox();
        hbDepartureTimeAndImage.setAlignment(Pos.CENTER_LEFT);

        //Departure Time
        vbDepartureTime = new VBox();
        vbDepartureTime.setAlignment(Pos.CENTER);

        lbDepartureTime = new Label("Departure Time");
        lbDepartureTime.setAlignment(Pos.CENTER_LEFT);
        lbDepartureTime.setPadding(new Insets(0,0,5,0));
        lbDepartureTime.setFont(fontSmall);
        tfDepartureTime = new TextField();
        tfDepartureTime.setPromptText("Time");

        vbDepartureTime.getChildren().addAll(lbDepartureTime,tfDepartureTime);

        Lighting lightingBlue = new Lighting(new Light.Distant(45, 90, Color.rgb(40,69,98)));
        ColorAdjust brightBlue = new ColorAdjust(1, 1, 1, 1);
        lightingBlue.setContentInput(brightBlue);
        lightingBlue.setSurfaceScale(0);
        Image img2 = ImageManager.getImage("icons\\icon_clock-o.png");

        //png use of images
        ImageView imgView2 = new ImageView(img2);
        imgView2.setId("calendar");

        imgView2.setEffect(lightingBlue);
        imgView2.setFitHeight(22);
        imgView2.setPreserveRatio(true);
        vbDepartureTimeImg= new VBox(imgView2);
        vbDepartureTimeImg.setAlignment(Pos.BOTTOM_CENTER);


        hbDepartureTimeAndImage.getChildren().addAll(vbDepartureTime,vbDepartureTimeImg);
        hbDepartureTimeAndImage.setSpacing(2);

        //Tolls
        vbTolls = new VBox();
        vbTolls.setAlignment(Pos.CENTER_LEFT);

        lbTolls = new Label("Tolls:");
        lbTolls.setAlignment(Pos.CENTER_LEFT);
        cbTolls = new ChoiceBox();
        cbTolls.getItems().addAll("Yes","No");
        cbTolls.setValue("Yes");
        vbTolls.getChildren().addAll(lbTolls,cbTolls);

        vbOptions.getChildren().addAll(vbDirections,vbExtraWaitTime,hbPassengersSuitcases,hbDepartureDateAndImage,hbDepartureTimeAndImage,vbTolls);
        vbOptions.setSpacing(15);

        vbOptionsWithTitle.getChildren().addAll(lbOptionsTitle,vbOptions);

        submitBtns= new HBox();
        btnSubmit = new Button("Submit");
        btnReset = new Button("Reset");
        btnSubmit.setId("mbtnSubmit");
        btnReset.setId("mbtnReset");
        submitBtns.getChildren().addAll(btnReset, btnSubmit);
        submitBtns.setPadding(new Insets(10,0,0,40));
        submitBtns.setSpacing(10);

        vbAdressesAndOptions.getChildren().addAll(vbAdressesWithTitle,vbOptionsWithTitle, submitBtns);



        this.setTop(vbAdressesAndOptions);
    }
    public static final LocalDate LOCAL_DATE (){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());

        String dataString = formatter.format(date);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dataString, formatter2);

        return localDate;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    private void registerHandlers() {

        btnReset.setOnAction(actionEvent -> {

            crpcManager.setMenuOpt(MenuOpt.MAIN_MENU);

        });

        btnSubmit.setOnAction(actionEvent -> {
            int extraWaitTime;
            String departureTime;
            String departureDate;
            boolean directions;
            int nrPassengers = 1;
            int nrSuitcases = 0;
            boolean tolls;
            boolean flag = true;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdfMonthYear = new SimpleDateFormat("dd-MM-yyyy");
            /*
            Calendar cal = Calendar.getInstance();
            ServerTime st = new ServerTime(
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    cal.get(Calendar.SECOND)
            );
*/

            if(tfExtraWaitTime.getText().isBlank()) {
                extraWaitTime = 0;
            } else {
                extraWaitTime = Integer.parseInt(tfExtraWaitTime.getText());
            }

            if(tfDepartureTime.getText().isBlank()/* || sdf.format(tfDepartureTime.getText()). curTime*/) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("The departure time is invalid");
                alert.showAndWait();
                flag = false;
                clearForm();
                return;
            }
            departureTime = /*sdf.format(*/tfDepartureTime.getText()/*)*/;

            /*if(sdfMonthYear.format(dpDepartureDate.getValue()) < sdfMonthYear.format(System.date())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("The departure time is invalid");
                alert.showAndWait();
                flag = false;
                clearForm();
                return;
            }*/
            departureDate = dpDepartureDate.getValue().toString();

            if(cbDirections.getValue().equals("One Way")) {
                directions = true;
            }else
                directions = false;//return trip

            if(cbPassengers.getValue() != null) {
                String s = cbPassengers.getValue().toString();
                nrPassengers = Integer.parseInt(s);
            }

            if(cbSuitcases.getValue() != null) {
                String s = cbSuitcases.getValue().toString();
                nrSuitcases = Integer.parseInt(s);
            }

            if(cbTolls.getValue().equals("Yes"))
                tolls = true;
            else
                tolls = false;

            if(flag) {
                crpcManager.book(directions,departureDate,extraWaitTime,nrSuitcases, nrPassengers,departureTime,tolls);
            }
            clearForm();
            crpcManager.setMenuOpt(MenuOpt.MAIN_MENU);

        });


        originA.getEntryMenu().setOnAction((ActionEvent e) ->
        {
            ((MenuItem) e.getTarget()).addEventHandler(Event.ANY, (Event event) ->
            {
                if (originA.getLastSelectedObject() != null)
                {
                    originA.setText(originA.getLastSelectedObject().toString());
                    PlaceDetails place = AutoCompleteAddressField.getPlace((AutoCompleteAddressField.AddressPrediction) originA.getLastSelectedObject());
                    if (place != null)
                    {
                        StringUtils StringUtils = null;
                        Destin1.setText(
                                StringUtils.join(
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.STREET_NUMBER),
                                        " ",
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.ROUTE))
                        );

                    } else
                    {
                        Destin1.clear();

                    }
                }
            });
        });

        destinA.getEntryMenu().setOnAction((ActionEvent e) ->
        {
            ((MenuItem) e.getTarget()).addEventHandler(Event.ANY, (Event event) ->
            {
                if (destinA.getLastSelectedObject() != null)
                {
                    destinA.setText(destinA.getLastSelectedObject().toString());
                    PlaceDetails place = AutoCompleteAddressField.getPlace((AutoCompleteAddressField.AddressPrediction) destinA.getLastSelectedObject());
                    if (place != null)
                    {
                        StringUtils StringUtils = null;
                        Origin1.setText(
                                StringUtils.join(
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.STREET_NUMBER),
                                        " ",
                                        AutoCompleteAddressField.getComponentLongName(place.addressComponents, AddressComponentType.ROUTE))
                        );

                    } else
                    {
                        Origin1.clear();

                    }
                }
            });
        });

        btnSubmit.setOnAction(actionEvent -> {
            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm() + "?origin=" + originA.getText() + "&destin=" + destinA.getText());
            //String returnValue = (String) webEngine.executeScript("getRectArea()");
            myBrowser.webEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (newValue != Worker.State.SUCCEEDED) { return; }

                            String returnValue = (String) myBrowser.webEngine.executeScript("results()");

                            crpcManager.setGoogleReturn(returnValue);
                            //System.out.println(returnValue);
                            crpcManager.setMenuOpt(MenuOpt.CONFIRMBOOKING);
                        }
                    }

            );
        });


        btnReset.setOnAction(actionEvent -> {
            myBrowser.webEngine.load(myBrowser.urlGoogleMaps.toExternalForm());
        });


        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    void clearForm(){
        cbDirections.setValue("One way");
        tfExtraWaitTime.setText("0");
        cbPassengers.setValue(1);
        cbDirections.setValue(0);
        dpDepartureDate.setValue(null);
        tfDepartureTime.setText("Time");
        cbTolls.setValue("Yes");
    }

    private void update() {

    }

}
