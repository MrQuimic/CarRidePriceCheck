package pt.isec.gps.team11;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import pt.isec.gps.team11.model.CRPCManager;


import java.beans.PropertyChangeSupport;
import java.net.URL;

public class MyBrowser extends Region {
    TextField tf_origin = new TextField();
    TextField tf_destination = new TextField();
    PropertyChangeSupport pcs;
    CRPCManager crpcManager;
    Button btnSubmit = new Button("Submit");

    Button btnReset = new Button("Reset");
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();


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

        //webEngine.executeScript("ExibirGoogleMaps()");


        btnSubmit.setOnAction(actionEvent -> {
            webView.getEngine().load(null);
            webEngine.load(urlGoogleMaps.toExternalForm() + "?origin=" + tf_origin.getText() + "&destin=" + tf_destination.getText());
            //System.out.println(tf_origin.getText());
            //System.out.println(tf_destination.getText());
            //webEngine.reload();
            //webEngine.executeScript("initMap()");


        });
        btnReset.setOnAction(actionEvent -> {
            webView.getEngine().load(null);
            webEngine.load(urlGoogleMaps.toExternalForm());

            System.out.println(tf_origin.getText());
            // System.out.println(tf_destination.getText());
            //webEngine.executeScript("ExibirGoogleMaps()");

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

        getChildren().add(webView);


        //https://jenkov.com/tutorials/javafx/webview.html

        //webEngine.executeScript("myFunction()"); //to execute function
        //String returnValue = (String) webEngine.executeScript("myFunction()"); //get values to use from javascript


    }
    private void registerHandlers() {}
    private void update() {}

}