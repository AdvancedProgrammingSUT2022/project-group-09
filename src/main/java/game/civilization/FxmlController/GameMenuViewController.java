package game.civilization.FxmlController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import game.civilization.Main;
import game.civilization.FxmlController.Components.SwitchButton;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class GameMenuViewController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ChoiceBox<String> autoSaveChoiceBox;
    @FXML
    private Circle inbox;
    
    private SwitchButton musicButton;
    private Spinner<Integer> widthSpinner;
    private Spinner<Integer> heightSpinner;

    private String toolTipStyle = "-fx-text-fill: white; -fx-font-family: \"Times New Roman\"; -fx-font-size: 15;";
    private String boxBackgroundStyle = "-fx-background-color: linear-gradient(#ffd65b, #e68400)," +
            " linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #ff9921), " +
            "linear-gradient(#ffe657 0%, #ff9921 50%, #ff9921 100%);";

    public void initialize() {
        widthSpinnerInitialization();
        heightSpinnerInitialization();
        musicButtonInitialization();
        inbox.setFill(new ImagePattern(new Image(Main.class.getResource("images/randomRequirements/message_transparent_background.png").toString(),50, 50, false, true)));
        System.out.println(Main.class.getResource("images/randomRequirements/message_transparent_background.png"));
        autoSaveChoiceBox.getItems().addAll(Arrays.asList("----------", "round", "option 3", "option 4"));
    }
    
    public void back() throws IOException {
        SceneController.getInstance().MainMenu();
    }
    
    private void musicButtonInitialization() {
        musicButton = new SwitchButton();
        musicButton.setScaleX(1.5);
        musicButton.setScaleY(1.5);
        musicButton.setLayoutX(521);
        musicButton.setLayoutY(578);
        this.anchorPane.getChildren().add(musicButton);
    }

    private void widthSpinnerInitialization() {
        widthSpinner = new Spinner<>(25, 45, 30);
        widthSpinner.setEditable(true);
        widthSpinner.setPrefSize(75, 25);
        widthSpinner.setLayoutX(1014);
        widthSpinner.setLayoutY(152);
        widthSpinner.setStyle(boxBackgroundStyle);
        anchorPane.getChildren().add(widthSpinner);
        Tooltip widthTooltip = new Tooltip("please enter your desired width");
        widthTooltip.setStyle(toolTipStyle);
        Tooltip.install(widthSpinner, widthTooltip);
    }

    private void heightSpinnerInitialization() {
        heightSpinner = new Spinner<>(25, 45, 30);
        heightSpinner.setEditable(true);
        heightSpinner.setPrefSize(75, 25);
        heightSpinner.setLayoutX(1014);
        heightSpinner.setLayoutY(104);
        heightSpinner.setStyle(boxBackgroundStyle);
        anchorPane.getChildren().add(heightSpinner);
        Tooltip heightTooltip = new Tooltip("please enter your desired height");
        heightTooltip.setStyle(toolTipStyle);
        Tooltip.install(heightSpinner, heightTooltip);
    }

    public void enterLobby() {

    }

    public void playGame() {

    }

    public void buildMap() {

    }

    public void continueGame() {

    }

    public void loadMap() {
        
    }
}
