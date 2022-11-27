module pt.isec.gps.team11.carridepricecheck {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens pt.isec.gps.team11.carridepricecheck to javafx.fxml;
    exports pt.isec.gps.team11.carridepricecheck;
}