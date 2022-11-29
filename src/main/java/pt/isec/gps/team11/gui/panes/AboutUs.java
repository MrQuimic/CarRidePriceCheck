package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.CRPCManager;

public class AboutUs extends BorderPane {
    StackPane stackPane;
    CRPCManager crpcManager;


    /**
     * The Background.
     */
    Color background = Color.LIGHTGRAY;
    /**
     * The Nr meta 1.
     */
    int nrMeta1,
    /**
     * The Nr meta 2.
     */
    nrMeta2,
    /**
     * The Nr custom.
     */
    nrCustom;
    /**
     * The Button meta 1.
     */
    Button buttonMeta1,
    /**
     * The Button meta 2.
     */
    buttonMeta2,
    /**
     * The Button custom.
     */
    buttonCustom,
    /**
     * The Btn menu.
     */
    btnMenu;
    /**
     * The Text field.
     */
    TextField textField;
    private Label lbStatus;
    private Label lbStatus2;



    public AboutUs(CRPCManager crpcManager){
        this.crpcManager = crpcManager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {


        lbStatus2 = new Label();

        lbStatus2.setPrefWidth(Integer.MAX_VALUE);

        lbStatus2.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(1))));

        lbStatus2.setStyle("-fx-background-color: rgba(73,69,69,0.08);-fx-font-family: 'Courrier New'; -fx-font-size: 16;");

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



        if(crpcManager.getMenuOpt()==MenuOpt.ABOUTUS) {
            this.setVisible(true);
            //SoundManager.play("637996__davejf__melody-loop-110-bpm.mp3");

        }else{
            this.setVisible(false);

            //crpcManager.play(" ");

        }




            lbStatus2.setText(String.format("" +
                    "\n\n ✎ Authors:\n" +
                    " Carlos Santos  {Email: a2003035578@isec.pt}\n" +
                    " Hugo Ferreira  {Email: a2020128305@isec.pt}\n" +
                    " Leonardo Sousa {Email: a2019129243@isec.pt }\n" +
                    " Luís Cruz  {Email: a2020142846@isec.pt}\n" +
                    " Rafael Gil {Email: a2020136741@isec.pt}\n\n"));



    }



}
