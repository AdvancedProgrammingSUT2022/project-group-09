module game.civilization {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.web;

    requires transitive org.controlsfx.controls;
    requires transitive com.dlsc.formsfx;
    requires transitive validatorfx;
    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.kordamp.bootstrapfx.core;
    requires transitive eu.hansolo.tilesfx;
    requires transitive com.google.gson;

    opens game.civilization to javafx.fxml;
    exports game.civilization;
    exports game.civilization.FxmlController to javafx.fxml;
    opens game.civilization.Model to com.google.gson;
}