package game.civilization.FxmlController;

import game.civilization.Controller.GameControllerPackage.GameDataBase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Controller.NetworkController.Client.ClientSocketController;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import game.civilization.MenuRegex.GameMenuRegex;
import game.civilization.Model.City;
import game.civilization.Model.Civilization;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Resources.Resource;
import game.civilization.Model.Terrains.Terrain;
import game.civilization.Model.TradingObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

public class TradeController implements Initializable {

    @FXML
    private TextArea result;
    @FXML
    private Pane pane;
    private Spinner myGold;
    @FXML
    private TextArea message;
    @FXML
    private Button send;
    @FXML
    private Label targetCivilization;
    private Civilization civilization = null;
    private final ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private String firstMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        result.setEditable(false);
        firstMessage = targetCivilization.getText();
        findCiv();
        handleGold();
        initializeCivButtons();
        initializeMyItems();
        setResult();
    }

    private void setResult() {
        result.setText("");
        for (TradingObject tradingObject : civilization.getTradingObjects()) {
            result.setText(result.getText()+tradingObject.showInfo());
        }
    }

    private void findCiv() {
        for (Civilization civilization1 : GameDataBase.getCivilizations()) {
            if (civilization1.getName().equals(UserDatabase.getCurrentUser().getUsername()))
                civilization = civilization1;
        }
        if (civilization == null)
            System.out.println("Bug dar TradeController -> initializeMyItem");
    }

    private void handleGold() {
        myGold = new Spinner(0, civilization.getGold().getCurrentGold(), 0);
        myGold.setLayoutY(52);
        myGold.setLayoutX(951);
        pane.getChildren().add(myGold);
    }

    private void initializeMyItems() {
        civilization.updateData();
        civilization.getResources().add(Resource.BANANA);
        civilization.getResources().add(Resource.BANANA);
        civilization.getResources().add(Resource.BANANA);
        civilization.getResources().add(Resource.BANANA);
        civilization.getResources().add(Resource.BANANA);
        civilization.getResources().add(Resource.BANANA);
        civilization.getResources().add(Resource.BANANA);
        int x = 60;
        for (Resource resource : Objects.requireNonNull(civilization).getResources()) {
            x += 40;
            CheckBox checkBox = new CheckBox();
            checkBox.setStyle("-fx-background-color: #89c1ff");
            checkBox.setLayoutX(1180);
            checkBox.setLayoutY(x);
            checkBox.setText(resource.name());
            checkBoxes.add(checkBox);
            pane.getChildren().add(checkBox);
        }
    }

    public void sendTrade(ActionEvent actionEvent) throws IOException {
        if (targetCivilization.getText().equals(firstMessage)) {
            targetCivilization.setText("select civilization first");
            firstMessage = targetCivilization.getText();
            return;
        }
        ArrayList<Resource> resources = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                Resource resource = null;
                for (Resource res : Resource.getAllResources()) {
                    if (res.name().equals(checkBox.getText()))
                        resource = res;
                }
                for (City city : civilization.getCities()) {
                    boolean brk = false;
                    for (Terrain terrain : city.getTerrains()) {
                        if (terrain.getResources().contains(resource)) {
                            terrain.getResources().remove(resource);
                            brk = true;
                            break;
                        }
                    }
                    if (brk)
                        break;
                }
                resources.add(resource);
            }
        }
        civilization.getGold().setCurrentGold(civilization.getGold().getCurrentGold() - Integer.parseInt(myGold.getEditor().getText()));
        TradingObject tradingObject = new TradingObject();
        tradingObject.setMessage(message.getText());
        tradingObject.setGold(Integer.parseInt(myGold.getEditor().getText()));
        tradingObject.setTarget(targetCivilization.getText());
        tradingObject.setResources(resources);
        tradingObject.setFrom(civilization.getName());
        Message message = new Message();
        message.setAction("send trade");
        message.setMessage(tradingObject.toJson());
        Client.getClientSocketController().sendMessage(message);
        targetCivilization.setText("done !");
        firstMessage = targetCivilization.getText();
        civilization.getTradingObjects().add(tradingObject);
        setResult();
    }

    private void initializeCivButtons() {
        int x = 30;
        for (Civilization civilization : GameDataBase.getCivilizations()) {
            Button button = new Button();
            button.setText(civilization.getName());
            button.setLayoutX(30);
            button.setLayoutY(x);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    targetCivilization.setText(civilization.getName());
                }
            });
            x += 30;
            pane.getChildren().add(button);
        }
    }
}
