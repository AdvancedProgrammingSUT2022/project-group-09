package game.civilization.FxmlController;

import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Controller.UserDatabase;
import game.civilization.Model.Game;
import game.civilization.Model.Response;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    private ArrayList<Game> availableGames = new ArrayList<>();
    private ArrayList<Game> myGames = new ArrayList<>();
    private ArrayList<Game> gamesIn = new ArrayList<>();


    public void initialize() throws IOException {
        Response response = Client.getClientServerSocketController().initializeTabel();
        availableGames = (ArrayList<Game>) response.getData().get("table");
        buildTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildTable(){
        int x = 78, y = 146;
        for (Game availableGame : availableGames) {
            Label label = new Label(String.valueOf(availableGame.getId()));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setLayoutX(x);
            label.setLayoutY(y += 50);
            label.setPrefWidth(70);
            label.setPrefHeight(30);
            label.setFont(new Font("Baloo Bhaina Regular", 12));
            label.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    //todo make pane that shows details
                }
            });
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    //todo make pane that Has a button for leave or add
                }
            });
            pane.getChildren().add(label);
        }
    }

    public void addGame(){
        if (id.getText().equals("")){
            return;
        }
        if (number.getText().equals("")){
            return;
        }
        Game game = new Game();
        if (privatee.isSelected()){
            game.setPrivate(true);
        }
        else {
            game.setPrivate(false);
        }
        game.setNumberOfPlayers(Integer.parseInt(number.getText()));
        game.setId(id.getText());
//        game.addPlayer(UserDatabase.getCurrentUser());
//        game.setAdmin(UserDatabase.getCurrentUser());
        //todo build tabel for this
    }

}
