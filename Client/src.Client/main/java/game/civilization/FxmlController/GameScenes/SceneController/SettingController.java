package game.civilization.FxmlController.GameScenes.SceneController;

import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.FxmlController.SceneController;
import game.civilization.Model.Units.UnitType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class SettingController {
    private static SettingController instance;

    public static SettingController getInstance() {
        if (instance == null)
            instance = new SettingController();
        return instance;
    }

    private SettingController() {
        Media media = new Media(getClass().getResource("/game/civilization/musics/music.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        pane = makeSettingPane();
    }

    private final MediaPlayer mediaPlayer;
    private final Pane pane;
    private boolean autoSave;

    private Pane makeSettingPane() {
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle();
        rectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;-fx-fill: linear-gradient(#ffda4a, #ffd700)");
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(220);
        rectangle.setHeight(60);
        pane.getChildren().add(rectangle);
        pane.minWidth(6000);
        pane.minHeight(6000);
        pane.maxWidth(6000);
        pane.maxHeight(6000);
        pane.prefHeight(6000);
        pane.prefWidth(6000);
        pane.setLayoutX(1300);
        pane.setLayoutY(70);
        Button musicButton = new Button();
        musicButton.setText("Music");
        musicButton.setLayoutX(78);
        musicButton.setStyle(SettlerController.res);
        musicButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (mediaPlayer.isAutoPlay()) {
                    mediaPlayer.pause();
                    mediaPlayer.setAutoPlay(false);
                } else
                    mediaPlayer.setAutoPlay(true);
            }
        });

        Button saveAndExit = new Button();
        saveAndExit.setText("save and exit");
        saveAndExit.setLayoutX(60);
        saveAndExit.setLayoutY(30);
        saveAndExit.setStyle(SettlerController.res);
        saveAndExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameDataBaseSaving.saveGame();
                try {
                    SceneController.getInstance().MainMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        Button autoSave = new Button();
        if (isAutoSave()) {
            autoSave.setText("auto save ON");
        } else {
            autoSave.setText("auto save OFF");
        }
        autoSave.setLayoutX(60);
        autoSave.setLayoutY(60);
        autoSave.setStyle(SettlerController.res);
        autoSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setAutoSave(!isAutoSave());
                if (isAutoSave()) {
                    autoSave.setText("auto save ON");
                } else {
                    autoSave.setText("auto save OFF");
                }
            }
        });


        Button speed = new Button();
        speed.setText("speed Boost");
        speed.setLayoutX(60);
        speed.setLayoutY(90);
        speed.setStyle(SettlerController.res);
        speed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (UnitType unitType : UnitType.getAllUnits()) {
                    unitType.setMovement(unitType.getMovement() * 2);
                }
            }
        });

        pane.getChildren().add(speed);
        pane.getChildren().add(autoSave);
        pane.getChildren().add(musicButton);
        pane.getChildren().add(saveAndExit);
        return pane;
    }

    public Pane getPane() {
        return pane;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }
}
