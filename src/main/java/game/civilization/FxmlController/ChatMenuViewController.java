package game.civilization.FxmlController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import game.civilization.Main;
import game.civilization.Controller.UserDatabase;
import game.civilization.Controller.ChatController.ClientChatController;
import game.civilization.Model.User;

public class ChatMenuViewController {
    @FXML
    private VBox privateChatVBox;

    private ClientChatController chatController = new ClientChatController();

    public void back(ActionEvent actionEvent) throws IOException {
        SceneController.getInstance().MainMenu();
    }

    public void refresh() {
        // ImageView imageView = (ImageView) privateChatVBox.getChildren().get(0);
        privateChatVBox.getChildren().clear();
        // privateChatVBox.getChildren().add(imageView);
        ArrayList<User> onlineUsers = new ArrayList<>();
        try {
            onlineUsers = chatController.getOnlineUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user : onlineUsers) {
            if (UserDatabase.getCurrentUser().getUsername().equals(user.getUsername()))
                continue;
            HBox holder = new HBox();
            holder.setSpacing(10);
            holder.setStyle("-fx-background-color: white;");
            Circle circle = new Circle(30, 30, 30);
            circle.setFill(Color.GREENYELLOW);
            holder.getChildren().add(circle);
            Label text = new Label();
            text.setStyle("-fx-font-size: 30px; -fx-font-family: \"Times New Roman\";");
            text.setText(user.getUsername());
            holder.setMinHeight(24);
            holder.getChildren().add(text);
            privateChatVBox.getChildren().add(holder);
            holder.setOnMouseClicked(mouseEvent -> {
                try {
                    ChatPageController.run(UserDatabase.getCurrentUser().getUsername(), user.getUsername());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(privateChatVBox.getChildren());
    }
}
