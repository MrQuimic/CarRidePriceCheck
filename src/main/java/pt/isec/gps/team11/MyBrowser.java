package pt.isec.gps.team11;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import pt.isec.gps.team11.model.CRPCManager;


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
        assert urlGoogleMaps != null;
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

        VBox vboxAll = new VBox(webView);
        getChildren().addAll(vboxAll);



    }

    private void registerHandlers() {}
    private void update() {}

}