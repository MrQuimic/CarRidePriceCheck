package pt.isec.gps.team11.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.gps.team11.gui.MenuOpt;
import pt.isec.gps.team11.model.CRPCManager;

public class Contacts extends BorderPane {
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


    public Contacts(CRPCManager crpcManager) {
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


        if (crpcManager.getMenuOpt() == MenuOpt.CONTACTS) {
            this.setVisible(true);
            //SoundManager.play("637996__davejf__melody-loop-110-bpm.mp3");

        } else {
            this.setVisible(false);

            //crpcManager.play(" ");

        }


        lbStatus2.setText(String.format("" +
                "\n\n ☎ Contacts:\n" +
                "\t\tCarlos Santos  " +
                "\t\t\t\ta2003035578@isec.pt\n" +
                "\t\tHugo Ferreira   " +
                "\t\t\ta2020128305@isec.pt\n" +
                "\t\tLeonardo Sousa  " +
                "\t\t\ta2019129243@isec.pt \n" +
                "\t\tLuís Cruz   " +
                "\t\t\t\ta2020142846@isec.pt\n" +
                "\t\tRafael Gil " +
                "\t\t\t\ta2020136741@isec.pt\n\n" +
                "\n ⚑ You can write additional info when booking\n"));


    }


}