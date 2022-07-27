package game.civilization.FxmlController;

import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.Components.SwitchButton;
import game.civilization.Main;
import game.civilization.Model.Invitation;
import game.civilization.Model.Response;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMenuViewController {
    public TextField invitationText;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ChoiceBox<String> autoSaveChoiceBox;
    @FXML
    private Circle inbox;

    private SwitchButton musicButton;
    private Spinner<Integer> widthSpinner;
    private Spinner<Integer> heightSpinner;
    private ArrayList<Invitation> invitations;
    private boolean state = false;
    private Pane invitationPane;

    private String toolTipStyle = "-fx-text-fill: white; -fx-font-family: \"Times New Roman\"; -fx-font-size: 15;";
    private String boxBackgroundStyle = "-fx-background-color: linear-gradient(#ffd65b, #e68400)," +
            " linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #ff9921), " +
            "linear-gradient(#ffe657 0%, #ff9921 50%, #ff9921 100%);";

    public void initialize() {
        widthSpinnerInitialization();
        heightSpinnerInitialization();
        musicButtonInitialization();
        inbox.setFill(new ImagePattern(new Image(Main.class.getResource("images/randomRequirements/message_transparent_background.png").toString(), 50, 50, false, true)));
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

    public void enterLobby() throws IOException {
        SceneController.getInstance().Lobby();
    }

    public void playGame() {
        UserDatabase.loadUsers();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Client.me.startOfflineGame(SceneController.getInstance().getStage(), UserDatabase.getUsers());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void buildMap() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Client.me.startBuildMap(SceneController.getInstance().getStage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void continueGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Client.me.playSavedOfflineGame(SceneController.getInstance().getStage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadMap() {
        UserDatabase.loadUsers();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Client.me.startSavedMapOfflineGame(SceneController.getInstance().getStage(), UserDatabase.getUsers());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sentInvitation() throws IOException {
        if (invitationText.getText().equals("")) {
            return;
        }
        String[] strings = invitationText.getText().split(" ");
        Invitation invitation = new Invitation();
        invitation.setUsername1(UserDatabase.getCurrentUser().getUsername());
        invitation.setUsername2(strings[1]);
        invitation.setGameId(strings[0]);
        Client.getClientServerSocketController().sendInvitation(invitation);
    }

    public void showInvitationPanel() throws IOException {
        System.out.println(state + " state");
        if (!state) {
            invitationPane = new Pane();
            Response response = Client.getClientServerSocketController().initInvitation();
            invitations = (ArrayList<Invitation>) response.getData().get("invitations");
            int x = 20, y = 50;
            for (Invitation invitation : invitations) {
                Label label = new Label(invitation.getGameId());
                label.setLayoutY(y += 40);
                label.setLayoutX(x);
//            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//
//                }
//            });
                Button button1 = new Button("accept");
                Button button2 = new Button("deny");
                button1.setLayoutY(y);
                button2.setLayoutY(y);
                button1.setLayoutX(60);
                button2.setLayoutX(120);
                button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Client.getClientServerSocketController().acceptInvitation(invitation);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Client.getClientServerSocketController().denyInvitation(invitation);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                invitationPane.getChildren().add(label);
                invitationPane.getChildren().add(button1);
                invitationPane.getChildren().add(button2);
            }
            anchorPane.getChildren().add(invitationPane);
            state = true;
        }
        else {
            clear();
            state = false;
        }

    }

    private void clear() {
        anchorPane.getChildren().remove(invitationPane);
    }
}
