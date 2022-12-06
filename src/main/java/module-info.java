module pt.isec.gps.team11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires java.desktop;
    requires org.junit.jupiter.api;
    requires google.maps.services;
    requires java.logging;
    requires org.apache.commons.lang3;
    requires mail;

    opens pt.isec.gps.team11 to javafx.fxml;
    exports pt.isec.gps.team11;
}
