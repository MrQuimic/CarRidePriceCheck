package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.CRPCManager;

public class AboutUs extends BorderPane {
    CRPCManager crpcManager;

    private Label lbStatus2;


    public AboutUs(CRPCManager crpcManager) {
        this.crpcManager = crpcManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {


        lbStatus2 = new Label();

        lbStatus2.setPrefWidth(Integer.MAX_VALUE);

        lbStatus2.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        lbStatus2.setStyle("-fx-background-color: rgba(73,69,69,0.08);-fx-font-family: 'Courrier New'; -fx-font-size: 16;");
        lbStatus2.setPadding(new Insets(0, 0, 0, 50));
        this.setTop(lbStatus2);
    }

    private void registerHandlers() {
        crpcManager.addPropertyChangeListener(evt -> {
            update();
        });
    }

    private void configAdapter() {
    }

    private void update() {


        if (crpcManager.getMenuOpt() == MenuOpt.ABOUTUS) {
            this.setVisible(true);

        } else {
            this.setVisible(false);
        }

        lbStatus2.setText(String.format("" +
                "\n\n ✎ Authors:\n" +
                "\tCarlos Santos  {Email: a2003035578@isec.pt}\n" +
                "\tHugo Ferreira  {Email: a2020128305@isec.pt}\n" +
                "\tLeonardo Sousa {Email: a2019129243@isec.pt }\n" +
                "\tLuís Cruz  {Email: a2020142846@isec.pt}\n" +
                "\tRafael Gil {Email: a2020136741@isec.pt}\n\n" +
                "\n ⚑ Application Car Ride Price Check\n" +
                " ⌛ Date 2022/11/27\n\n" +
                " ⚑ This pratical assignment was carried out in an academic context for the subject of GPS at ISEC!"));
    }
}