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
    public WebView webView = new WebView();
    public WebEngine webEngine = webView.getEngine();

    String initiate, returnValue, origin, destin;

    public final URL urlGoogleMaps = getClass().getResource("googlemaps.html");

    public MyBrowser(CRPCManager crpcManager) {

            this.crpcManager = crpcManager;
            pcs = new PropertyChangeSupport(this);

            createViews();
            registerHandlers();
            update();
    }

    private void createViews() {
        tf_origin.getText();
        webEngine.load(urlGoogleMaps.toExternalForm());
        webEngine.setJavaScriptEnabled(true);


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

        VBox vboxAll = new VBox( webView);
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