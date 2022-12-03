package pt.isec.gps.team11;

import com.google.maps.model.AddressComponentType;
import com.google.maps.model.PlaceDetails;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.utils.AutoCompleteAddressField;

import org.apache.commons.lang3.StringUtils;
import pt.isec.gps.team11.utils.AutoCompleteTextField;

import java.beans.PropertyChangeSupport;
import java.net.URL;

public class MyBrowser extends Region {

    private static final String API_KEY = "AIzaSyDP1feUTnivFyTntFVr722y7SwOxsit7VQ";
    TextField tf_origin = new TextField();
    TextField tf_destination = new TextField();
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    Button btnSubmit = new Button("Submit");

    Button btnReset = new Button("Reset");
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    String initiate, returnValue, origin, destin;

    public MyBrowser(CRPCManager crpcManager) {

            this.crpcManager = crpcManager;
            pcs = new PropertyChangeSupport(this);

            createViews();
            registerHandlers();
            update();
        }



    private void createViews() {
        tf_origin.getText();
        final URL urlGoogleMaps = getClass().getResource("googlemaps.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        webEngine.setJavaScriptEnabled(true);

        AutoCompleteAddressField originA = new AutoCompleteAddressField();
        AutoCompleteAddressField destinA = new AutoCompleteAddressField();
        originA.setId("originA");
        destinA.setId("destinA");
        originA.setMaxWidth(250);
        destinA.setMaxWidth(250);
        TextField tf_origin = new TextField();
        TextField tf_destination = new TextField();
        btnSubmit = new Button("Submit");
        btnReset = new Button("Reset");
        btnSubmit.setId("mbtnSubmit");
        btnReset.setId("mbtnReset");
        //webEngine.executeScript("ExibirGoogleMaps()");
        Label Origin = new Label("Origin:");
        Label Destination = new Label("Destination:");

        TextField Origin1 = new TextField();
        Origin1.setPromptText("Origin1");
        TextField Destin1 = new TextField();
        Destin1.setPromptText("Destin1");
        if (originA.getText() == ""){
            originA.setText("Coimbra, Portugal");
            destinA.setText("Porto, Portugal");
        }




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


        //scene = new Scene(myBrowser, 640, 480);
        HBox hControl  = new HBox(new Label("--"), btnSubmit, btnReset);
        hControl.setSpacing(10);

        btnSubmit.setOnAction(actionEvent -> {
            //webView.getEngine().load(null);

            webEngine.load(urlGoogleMaps.toExternalForm() + "?origin=" + originA.getText() + "&destin=" + destinA.getText());
            //String returnValue = (String) webEngine.executeScript("getRectArea()");
            webEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (newValue != Worker.State.SUCCEEDED) { return; }

                            String returnValue = (String) webEngine.executeScript("results()");
                            System.out.println(" " + originA.getText()  + "  --  " + destinA.getText() + "\n");
                            System.out.println(returnValue);
                            //webEngine.executeScript("setdata()");

                        }
                    }

            );

        });

        btnReset.setOnAction(actionEvent -> {
            webEngine.load(urlGoogleMaps.toExternalForm());
        });


        webView.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent e) -> {
            if (e.getCode() == KeyCode.ADD || e.getCode() == KeyCode.EQUALS
                    || e.getCode() == KeyCode.PLUS) {
                System.out.println("+");
                webView.setZoom(webView.getZoom() * 1.1);
            } else if (e.getCode() == KeyCode.SUBTRACT || e.getCode() == KeyCode.MINUS) {
                System.out.println("-");
                webView.setZoom(webView.getZoom() / 1.1);
            }
        });

        webView.addEventFilter(ScrollEvent.SCROLL, (ScrollEvent e) -> {
            double deltaY = e.getDeltaY();
            if (e.isControlDown() && deltaY > 0) {
                webView.setZoom(webView.getZoom() * 1.1);
                e.consume();
            } else if (e.isControlDown() && deltaY < 0) {
                webView.setZoom(webView.getZoom() / 1.1);
                e.consume();
            }
        });
        HBox hboxInputs = new HBox(originA,destinA, hControl);
        hboxInputs.setSpacing(20);
        VBox vboxAll = new VBox(hboxInputs, webView);
        getChildren().addAll(vboxAll);




        //String returnValue = (String) webEngine.executeScript("myFunction()");

        //https://jenkov.com/tutorials/javafx/webview.html

        //webEngine.executeScript("myFunction()"); //to execute function
       //String returnValue = (String) webEngine.executeScript("myFunction()"); //get values to use from javascript
       //https://www.tabnine.com/code/java/methods/javafx.scene.web.WebEngine/getLoadWorker
       /* webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        System.out.println("oldValue: " + oldValue);
                        System.out.println("newValue: " + newValue);

                        if (newValue == Worker.State.SUCCEEDED) {
                            //document finished loading
                        }
                    }
                }
        );
        */




    }

    private void registerHandlers() {}
    private void update() {}

}