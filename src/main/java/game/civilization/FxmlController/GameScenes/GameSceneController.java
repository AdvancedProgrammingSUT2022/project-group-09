package game.civilization.FxmlController.GameScenes;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.Controller.GameControllerPackage.InfoController;
import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.SceneController.*;
import game.civilization.FxmlController.MapMovement;
import game.civilization.FxmlController.SceneController;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.Main;
import game.civilization.Model.Civilization;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameSceneController implements Initializable {
    @FXML
    private ImageView military;
    @FXML
    private ImageView demographic;
    @FXML
    private Label currentTechTurn;
    @FXML
    private ImageView currentTech;
    @FXML
    private Label year;
    @FXML
    private TextArea notification;
    @FXML
    private Button tradeButton;
    @FXML
    private Label turnLabel;
    @FXML
    private Label civilizationName;
    @FXML
    private Label scienceLabel;
    @FXML
    private Label goldLabel;
    @FXML
    private Label happinessLabel;
    @FXML
    private Button nextTurnButton;
    @FXML
    private Pane backPane;
    @FXML
    private Pane pane;
    @FXML
    private Pane cityDetailsPane;
    @FXML
    private Pane cityButtonsPane;
    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!GameDataBase.isOnline()) {
            UserDatabase.setCurrentUser(GameDataBase.getPlayers().get(GameDataBase.getTurn() % GameDataBase.getCivilizations().size()));
        }
        handleDemographic();
        handleMilitary();
        handleEconomy();
        setDataToGameSceneDataBase();
        MapController.getInstance().run();
        UnitsController.getInstance().run();
        MapMovement.getInstance().run();
        CityMenuController.getInstance().run();
        TechnologyController.getInstance().run();
        UnitPanel.getInstance().run();
        CityPanel.getInstance().run();
        loadPane();
        refresh();
    }

    private void handleDemographic() {
        demographic.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notification.setText(GameDataBase.getCurrentCivilization().getDemographics());
            }
        });
        demographic.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        setNotification();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask, 3000);
            }
        });
    }

    private void handleMilitary() {
        military.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notification.setText(new InfoController().showMilitary());
            }
        });
        military.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        setNotification();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask, 3000);
            }
        });
    }

    private void handleEconomy() {
        goldLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                notification.setText(new InfoController().showEconomy());
            }
        });
        goldLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        setNotification();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask, 3000);
            }
        });
    }

    private void setNotification() {
        notification.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.setLayoutX(0);
                pane.setLayoutY(0);
            }
        });
        notification.setEditable(false);
        try {
            notification.setText((Objects.requireNonNull(GameDataBase.findCiv(UserDatabase.getCurrentUser().getUsername()))).getNotification());
        } catch (Exception ignored) {
            notification.setText("you lose Bye Bye");
        }
    }

    private void clearPane() {
        pane.getChildren().clear();
        turnLabel.setText("");
    }

    private void loadPane() {
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTiles());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getCityIcons());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getTileFeatures());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getUnits());
        pane.getChildren().addAll(GameSceneDataBase.getInstance().getMilitaryUnits());
    }

    private void setDataToGameSceneDataBase() {
        GameSceneDataBase.getInstance().setGameSceneController(this);
        GameSceneDataBase.getInstance().setYear(year);
        GameSceneDataBase.getInstance().setBackPane(backPane);
        GameSceneDataBase.getInstance().setPane(pane);
        GameSceneDataBase.getInstance().setGoldLabel(goldLabel);
        GameSceneDataBase.getInstance().setHappinessLabel(happinessLabel);
        GameSceneDataBase.getInstance().setScienceLabel(scienceLabel);
        GameSceneDataBase.getInstance().setCityButtonsPane(cityButtonsPane);
        GameSceneDataBase.getInstance().setCityDetailsPane(cityDetailsPane);
        GameSceneDataBase.getInstance().setScrollPane(scrollPane);
        GameSceneDataBase.getInstance().setCurrentTechTurn(currentTechTurn);
        GameSceneDataBase.getInstance().setCurrentTech(currentTech);
    }

    public void nextTurn(ActionEvent actionEvent) throws IOException {
        if (isNotMyTurn())
            return;
        GameSceneDataBase.getInstance().clear();
        clearPane();
        new GameMenuController().doNextTurn();
        refresh();
        if (GameDataBase.isOnline())
            Client.getClientProxySocketController().setGame();
    }

    public void refresh() {
        civilizationName.setText(GameDataBase.getCurrentCivilization().getName());
        GameSceneDataBase.getInstance().getYear().setText("year : " + GameDataBase.getYear());
        GameSceneDataBase.getInstance().clear();
        clearPane();
        setNotification();
        if (!turnHandler())
            return;
        setResearchIcon();
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
        GameDataBase.getCurrentCivilization().updateData();
        MapController.getInstance().run();
        UnitsController.getInstance().run();
        TechnologyController.getInstance().run();
        UnitPanel.getInstance().run();
        CityPanel.getInstance().run();
        CityMenuController.getInstance().run();
        loadPane();
    }

    private boolean turnHandler() {
        if (isNotMyTurn()) {
            turnLabel.setText("Its not your Turn !");
            if (amILose()) {
                turnLabel.setText("your Score is :" + GameSceneDataBase.getInstance().getScore() + "you lose");
                nextTurnButton.setText("exit");
                nextTurnButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            SceneController.getInstance().MainMenu();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            return false;
        }
        if (amIWin()) {
            turnLabel.setText("your Score is :" + GameSceneDataBase.getInstance().getScore() + "you Win");
            nextTurnButton.setText("exit");
            nextTurnButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        SceneController.getInstance().MainMenu();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return false;
        }
        return true;
    }

    public void CheatActivate(ActionEvent actionEvent) {
        if (isNotMyTurn())
            return;
        try {
            SceneController.getInstance().cheat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openTrade(ActionEvent actionEvent) {
        if (isNotMyTurn())
            return;
        try {
            SceneController.getInstance().trade();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNotMyTurn() {
        return !GameDataBase.getCurrentCivilization().getName().equals(UserDatabase.getCurrentUser().getUsername());
    }

    private boolean amILose() {
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            if (civilization.getName().equals(UserDatabase.getCurrentUser().getUsername()))
                return false;
        }
        return true;
    }

    private boolean amIWin() {
        if (GameDataBase.getCivilizations().size() == 1)
            return GameDataBase.getCivilizations().get(0).getName().equals(UserDatabase.getCurrentUser().getUsername());
        return false;
    }

    public void setResearchIcon() {
        try {
            GameSceneDataBase.getInstance().getCurrentTechTurn().setText("turn : " + String.valueOf(GameDataBase.getCurrentCivilization().getTechnologies().
                    getTurn(GameDataBase.getCurrentCivilization().getTechnologies().getTechnologyCurrentlyResearching())));
            GameSceneDataBase.getInstance().getCurrentTech().setImage(new Image(Main.class.getResource("images/technology/" + GameDataBase.getCurrentCivilization().getTechnologies().getTechnologyCurrentlyResearching().getName() + ".png").toExternalForm()));
        } catch (Exception ignore) {
            GameSceneDataBase.getInstance().getCurrentTechTurn().setText("");
        }
    }

    public void setting(MouseEvent actionEvent) {
        if (backPane.getChildren().contains(SettingController.getInstance().getPane()))
            backPane.getChildren().remove(SettingController.getInstance().getPane());
        else
            backPane.getChildren().add(SettingController.getInstance().getPane());
    }

    public void changeCursor(Cursor cursor) {
        pane.getScene().setCursor(cursor);
    }
}
