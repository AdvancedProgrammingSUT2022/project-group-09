package game.civilization.FxmlController;

import game.civilization.Controller.GameControllerPackage.GameMenuController;
import game.civilization.MenuRegex.GameMenuRegex;
import game.civilization.FxmlController.GameScenes.SceneModels.GameSceneDataBase;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

public class CheatSceneController implements Initializable {
    @FXML
    private TextArea text;
    private int line = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    StringBuilder t = new StringBuilder(text.getText());
                    t.deleteCharAt(t.length() - 1);
                    try {
                        t = new StringBuilder(t.substring(t.lastIndexOf("\n")));
                    } catch (Exception ignored) {
                    }
                    String str = String.valueOf(t);
                    str = str.replaceAll("\n", "");
                    activeCheat(str);
                }
            }
        });

    }

    private void activeCheat(String input) {
        Matcher matcher;
        GameMenuController gameMenuController = new GameMenuController();
        if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_GOLD)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().increaseGold(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_ARCHER)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().setArcher(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_WORKER)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().setWorker(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_TANK)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().setTank(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_SETTLER)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().setSettler(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_LANCER)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().setLancer(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_ARTILLERY)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().setArtillery(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.SET_CANNON)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().setCannon(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_HAPPINESS)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().increaseHappiness(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_SCIENCE)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().increaseScience(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.INCREASE_TURN)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().increaseTurn(matcher) + "\n");
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.UNIT_RESET)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().resetUnit());
        } else if ((matcher = GameMenuRegex.getMatcher(input, GameMenuRegex.OPEN_ALL_TECHNOLOGIES)) != null) {
            text.setText(text.getText() + gameMenuController.getCheatConteroller().openTechnologies() + "\n");
        } else {
            text.setText(text.getText() + "invalid command" + "\n");
        }
        text.selectPositionCaret(text.getText().length());
        text.deselect();
        GameSceneDataBase.getInstance().getGameSceneController().refresh();
    }

    private int countLines(String str) {
        String[] lines = str.split("\n");
        return lines.length;
    }
}
