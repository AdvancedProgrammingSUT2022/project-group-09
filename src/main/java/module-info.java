module game.civilization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.google.gson;

    opens game.civilization to javafx.fxml;
    opens game.civilization.FxmlController to javafx.fxml;
    exports game.civilization;
    exports game.civilization.FxmlController to javafx.fxml;
    opens game.civilization.Model to com.google.gson;
}