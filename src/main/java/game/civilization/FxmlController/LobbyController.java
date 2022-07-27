package game.civilization.FxmlController;

import game.civilization.Controller.ClientLobbyDatabase;
import game.civilization.Controller.GameControllerPackage.GameDataBaseSaving;
import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Controller.UserDatabase;
import game.civilization.FxmlController.GameScenes.SceneController.SettlerController;
import game.civilization.Model.Game;
import game.civilization.Model.Response;
import game.civilization.Model.User;
import javafx.application.Platform;
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
    public TextField searchField;
    public Button search;
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
    private Pane anotherPane;

    private ArrayList<Game> availableGames = new ArrayList<>();
    private ArrayList<Game> myGames = new ArrayList<>();
    private ArrayList<Game> gamesIn = new ArrayList<>();
    private ArrayList<Object> allLabels = new ArrayList<>();


    public void initialize() throws IOException {
        clear();
        Response response = Client.getClientServerSocketController().initializeTabel();
        availableGames = (ArrayList<Game>) response.getData().get("list1");
        myGames = (ArrayList<Game>) response.getData().get("list2");
        buildTable();
        buildTable2();
    }

    private void clear() {
        pane.getChildren().removeAll(allLabels);
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
            allLabels.add(label);
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
        rectangle.setHeight(90);
        pane.getChildren().add(rectangle);
        pane.minWidth(6000);
        pane.minHeight(6000);
        pane.maxWidth(6000);
        pane.maxHeight(6000);
        pane.prefHeight(6000);
        pane.prefWidth(6000);
        pane.setLayoutX(60);
        pane.setLayoutY(70);
        Button stream = new Button("stream");
        stream.setLayoutX(102);
        stream.setLayoutY(70);
        stream.setStyle(SettlerController.res);
        stream.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Client.me.startWatchingStream(SceneController.getInstance().getStage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        Button join = new Button();
        join.setLayoutX(50);
        join.setLayoutY(70);
        if (isInGame(game) != null) {
            join.setText("leave");
        } else {
            join.setText("join");
        }
        join.setStyle(SettlerController.res);
        join.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(UserDatabase.getCurrentUser().getUsername());
                if (join.getText().equals("join")) {

                    game.getPlayers().add(UserDatabase.getCurrentUser());
                    try {
                        Client.getClientServerSocketController().addToGame(game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    join.setText("leave");
                } else {
                    game.getPlayers().remove(isInGame(game));
                    System.out.println(game.getPlayers().size());
                    try {
                        Client.getClientServerSocketController().leaveGame(game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    join.setText("join");
                    allLabels.add(gamePane);
                }

            }
        });
        pane.getChildren().add(stream);
        pane.getChildren().add(join);
        showDetails(pane, game);
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

    private User isInGame(Game game) {
        for (User player : game.getPlayers()) {
            if (player.getUsername().equals(UserDatabase.getCurrentUser().getUsername()))
                return player;
        }
        return null;
    }

    private void showDetails(Pane pane, Game game) {
        Label label1 = new Label("max players: " + String.valueOf(game.getNumberOfPlayers()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("players: ");
        for (User player : game.getPlayers()) {
            stringBuilder.append(player.getUsername()).append("  ");
        }
        Label label2 = new Label(stringBuilder.toString());
        label1.setFont(new Font("Baloo Bhaina Regular", 12));
        label2.setFont(new Font("Baloo Bhaina Regular", 12));
        label1.setLayoutX(0);
        label1.setLayoutY(0);
        label2.setLayoutX(0);
        label2.setLayoutY(20);
        pane.getChildren().add(label1);
        pane.getChildren().add(label2);
    }

    public void buildTable2() {
        int x = 570, y = 243;
        for (Game myGame : myGames) {
            System.out.println("okkk");
            Label label = new Label(String.valueOf(myGame.getId()));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setLayoutX(x);
            label.setLayoutY(y += 50);
            label.setPrefWidth(70);
            label.setPrefHeight(30);
            label.setFont(new Font("Baloo Bhaina Regular", 12));
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (anotherPane != null) {
                        pane.getChildren().remove(anotherPane);
                        anotherPane = null;
                    } else {
                        anotherPane = makeGamePane(myGame);
                        Button button = new Button("start");
                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    Client.getClientServerSocketController().launchGame(myGame);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        button.setLayoutX(0);
                        button.setLayoutY(70);
                        button.setStyle(SettlerController.res);
                        button.setFont(new Font("Baloo Bhaina Regular", 12));
                        anotherPane.getChildren().add(button);
                        pane.getChildren().add(anotherPane);
                        allLabels.add(anotherPane);
                    }
                }
            });
            allLabels.add(label);
            pane.getChildren().add(label);
        }
    }

    public void search() throws IOException {
        String id = searchField.getText();
        if (!id.equals("")) {
            Response response = Client.getClientServerSocketController().searchForGame(id);
            Game game = (Game) response.getData().get("game");
            if (game != null) {
                availableGames = new ArrayList<Game>();
                availableGames.add(game);
                clear();
                buildTable();
                buildTable2();
            }
        }
    }
}
