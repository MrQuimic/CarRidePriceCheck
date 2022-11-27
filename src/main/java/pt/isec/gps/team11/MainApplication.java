package pt.isec.gps.team11;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;

import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class MainApplication extends Application {

    private Scene scene;
    MyBrowser myBrowser;
    TextField tf_origin = new TextField();
    TextField tf_destination = new TextField();
    Button b = new Button("Submit");
    Button reset = new Button("Reset");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Car Ride Price Check");

        myBrowser = new MyBrowser();

        SplitPane splitPane = new SplitPane();


        Label Origin = new Label("Origin");
        Label Destination = new Label("Destination");

        tf_origin.setText("Coimbra, Portugal");
        tf_destination.setText("Porto, Portugal");

        VBox leftControl = new VBox(new Label("--"), Origin, tf_origin, Destination, tf_destination, b, reset);
        VBox rightControl = new VBox(new Label("MAP"), myBrowser);
        splitPane.getItems().addAll(leftControl, rightControl);
        splitPane.setDividerPositions(0.15f, 0.85f); //Important for zoom

        scene = new Scene(splitPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    class MyBrowser extends Region {

        HBox toolbar;

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        public MyBrowser() {

            tf_origin.getText();
            final URL urlGoogleMaps = getClass().getResource("googlemaps.html");
            ;
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setJavaScriptEnabled(true);

            //webEngine.executeScript("ExibirGoogleMaps()");


            b.setOnAction(actionEvent -> {
                webView.getEngine().load(null);
                webEngine.load(urlGoogleMaps.toExternalForm() + "?origin=" + tf_origin.getText() + "&destin=" + tf_destination.getText());
                //System.out.println(tf_origin.getText());
                //System.out.println(tf_destination.getText());
                //webEngine.reload();
                //webEngine.executeScript("initMap()");


            });
            reset.setOnAction(actionEvent -> {
                webView.getEngine().load(null);
                webEngine.load(urlGoogleMaps.toExternalForm());

                // System.out.println(tf_origin.getText());
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

    }

}


