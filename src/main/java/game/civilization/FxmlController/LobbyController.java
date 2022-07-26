package game.civilization.FxmlController;

import game.civilization.Controller.ClientLobbyDatabase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.SceneController.SettlerController;
import game.civilization.Model.Game;
import game.civilization.Model.Response;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {
    @FXML
    private AnchorPane pane;
    @FXML
    private Button ok;
    @FXML
    private Button refresh;
    @FXML
    private TextField id;
    @FXML
    private TextField number;
    @FXML
    private CheckBox privatee;
    private Pane gamePane;

    private ArrayList<Game> availableGames = new ArrayList<>();
    private ArrayList<Game> myGames = new ArrayList<>();
    private ArrayList<Game> gamesIn = new ArrayList<>();


    public void initialize() throws IOException {
        Response response = Client.getClientServerSocketController().initializeTabel();
        availableGames = (ArrayList<Game>) response.getData().get("list1");
        myGames = (ArrayList<Game>) response.getData().get("list2");
        buildTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(pane);
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientLobbyDatabase.getInstance().setLobbyController(this);
    }

    public void buildTable() {
        int x = 78, y = 146;
        for (Game availableGame : availableGames) {
            Label label = new Label(String.valueOf(availableGame.getId()));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setLayoutX(x);
            label.setLayoutY(y += 50);
            label.setPrefWidth(70);
            label.setPrefHeight(30);
            label.setFont(new Font("Baloo Bhaina Regular", 12));
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (gamePane != null) {
                        pane.getChildren().remove(gamePane);
                        gamePane = null;
                    } else {
                        gamePane = makeGamePane(availableGame);
                        pane.getChildren().add(gamePane);
                    }
                }
            });
            pane.getChildren().add(label);
        }
    }

    public void addGame() throws IOException {
        if (id.getText().equals("")) {
            return;
        }
        if (number.getText().equals("")) {
            return;
        }
        Game game = new Game();
        if (privatee.isSelected()) {
            game.setPrivate(true);
        } else {
            game.setPrivate(false);
        }
        game.setNumberOfPlayers(Integer.parseInt(number.getText()));
        game.setId(id.getText());
        game.addPlayer(UserDatabase.getCurrentUser());
        game.setAdmin(UserDatabase.getCurrentUser());
        Client.getClientServerSocketController().addGame(game);
    }

    private Pane makeGamePane(Game game) {
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
        pane.setLayoutX(60);
        pane.setLayoutY(70);

        Button join = new Button();
        if (game.getPlayers().contains(UserDatabase.getCurrentUser())) {
            join.setText("leave");
        } else {
            join.setText("join");
        }
        join.setStyle(SettlerController.res);
        join.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (join.getText().equals("join")) {
                    game.getPlayers().add(UserDatabase.getCurrentUser());
                    join.setText("leave");
                } else {
                    game.getPlayers().remove(UserDatabase.getCurrentUser());
                    join.setText("join");
                }

            }
        });
        pane.getChildren().add(join);
        return pane;
    }

    public void setAvailableGames(ArrayList<Game> availableGames) {
        this.availableGames = availableGames;
    }

    public void setMyGames(ArrayList<Game> myGames) {
        this.myGames = myGames;
    }

    public ArrayList<Game> getAvailableGames() {
        return availableGames;
    }

    public ArrayList<Game> getMyGames() {
        return myGames;
    }

    public void setGamesIn(ArrayList<Game> gamesIn) {
        this.gamesIn = gamesIn;
    }

    public ArrayList<Game> getGamesIn() {
        return gamesIn;
    }
}
