module game.civilization {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.web;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires transitive java.sql;

    requires transitive org.controlsfx.controls;
    requires transitive com.dlsc.formsfx;
    requires transitive validatorfx;
    requires transitive org.kordamp.ikonli.javafx;
    requires transitive org.kordamp.bootstrapfx.core;
    requires transitive eu.hansolo.tilesfx;
    requires transitive com.google.gson;
    requires transitive com.auth0.jwt;
    requires transitive xstream;


    exports game.civilization.Model.Terrains to com.google.gson, xstream;
    exports game.civilization.Model.TerrainFeatures to com.google.gson, xstream;
    exports game.civilization.Model.Improvements to com.google.gson, xstream;
    exports game.civilization.Model.Info to com.google.gson, xstream;
    exports game.civilization.Model.Units to com.google.gson, xstream;
    exports game.civilization.Model.Buildings to com.google.gson, xstream;
    exports game.civilization.Model.Resources to com.google.gson, xstream;
    exports game.civilization.Model to com.google.gson, xstream, com.auth0.jwt;
    exports game.civilization.Model.TechnologyPackage to com.google.gson, xstream;
    opens game.civilization.Model.TechnologyPackage to com.google.gson, xstream;
    opens game.civilization.Model.Terrains to com.google.gson, xstream;
    opens game.civilization.Model.TerrainFeatures to com.google.gson, xstream;
    opens game.civilization.Model.Improvements to com.google.gson, xstream;
    opens game.civilization.Model.Info to com.google.gson, xstream;
    opens game.civilization.Model.Units to com.google.gson, xstream;
    opens game.civilization.Model.Resources to com.google.gson, xstream;
    opens game.civilization.Model.Buildings to com.google.gson, xstream;
    opens game.civilization.Model.NetworkModels to com.google.gson, xstream;
    opens game.civilization.Model.Chat to com.google.gson;

    exports game.civilization.Model.Chat to com.google.gson;
    opens game.civilization to javafx.fxml;
    opens game.civilization.Controller.GameControllerPackage to com.google.gson, xstream;
    opens game.civilization.Model to com.google.gson, xstream, javafx.base, com.auth0.jwt;
    opens game.civilization.FxmlController to javafx.fxml;
    exports game.civilization;
    exports game.civilization.FxmlController to javafx.fxml;
    exports game.civilization.FxmlController.GameScenes to javafx.fxml;
    opens game.civilization.FxmlController.GameScenes to javafx.fxml;
    exports game.civilization.Controller.NetworkController.Client to javafx.graphics, xstream;
    exports game.civilization.Controller.GameControllerPackage to javafx.graphics;
}