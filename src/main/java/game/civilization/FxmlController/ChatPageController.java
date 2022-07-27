package game.civilization.FxmlController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import game.civilization.Main;
import game.civilization.Controller.ChatController.ClientChatController;
import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.Chat.ChatMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatPageController {

    private String senderUsername;
    private String receiverUsername;
    @FXML
    public VBox messagesList;
    @FXML
    public TextField newMessageField;

    public static void run(String senderUsername, String receiverUsername) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/chat-page.fxml"));
        ChatPageController controller = new ChatPageController();
        controller.setSenderUsername(senderUsername);
        controller.setReceiverUsername(receiverUsername);
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), 400, 600);
        Stage cheatStage = new Stage();
        cheatStage.setTitle("Chat with" + receiverUsername);
        cheatStage.setScene(scene);
        cheatStage.show();
        controller.runForFirstTime();
    }

    public void runForFirstTime() throws IOException {
        Request request = new Request();
        request.setAction("get_messages");
        request.addData("senderUsername", senderUsername);
        request.addData("receiverUsername", receiverUsername);
        Response response = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        ArrayList<ChatMessage> messages = (ArrayList<ChatMessage>) response.getData().get("messages");
        new ClientChatController().addMessagesToChat(messages);
        showMessages();
        newMessageField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().getCode() == 10) {
                Request request1 = new Request();
                request1.setAction("send_message");
                request1.addData("text", newMessageField.getText());
                request1.addData("senderUsername", senderUsername);
                request1.addData("receiverUsername", receiverUsername);
                try {
                    Client.getClientServerSocketController().justSendRequest(request);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ChatMessage message = new ChatMessage();
                message.setReceiverUsername(receiverUsername);
                message.setSenderUsername(senderUsername);
                message.setSentAt(LocalDateTime.now());
                message.setText(newMessageField.getText());
                new ClientChatController().addMessageToChat(message);
                newMessageField.setText("");
                showMessages();
            }
        });
    }
    

    public void showMessages() {
        messagesList.getChildren().clear();
        ArrayList<ChatMessage> messages = new ClientChatController().getMessagesOfChat(senderUsername, receiverUsername);
        for (ChatMessage message : messages) {
            String sender = message.getSenderUsename();
            VBox vBox = new VBox();
            HBox holder = new HBox();
            Label text = new Label();
            text.setText(message.getText());
            holder.getChildren().add(text);
            if (sender.equals(senderUsername)) {
                text.setStyle("-fx-background-color: #ffffff; -fx-padding: 12; -fx-background-radius: 12");
                holder.setAlignment(Pos.CENTER_LEFT);
            } else {
                text.setStyle("-fx-background-color: #effdde; -fx-padding: 12; -fx-background-radius: 12");
                holder.setAlignment(Pos.CENTER_RIGHT);
            }
            vBox.getChildren().add(holder);
            Label textSent = new Label();
            textSent.setText("sent at " + message.getSentAt());
            vBox.getChildren().add(textSent);
            messagesList.getChildren().add(vBox);
        }
    }

    public void backClicked() {
        System.out.println("reciver is: " + receiverUsername);
        System.out.println("sender is: " + senderUsername);
    }

    public void pedaratFotKard() {
        System.out.println("your father is dead now!");
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
