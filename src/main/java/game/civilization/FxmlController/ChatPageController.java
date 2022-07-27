package game.civilization.FxmlController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import game.civilization.Main;
import game.civilization.Controller.ChatController.ClientChatController;
import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.Chat.ChatMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    private ClientChatController clientChatController;
    private static ChatPageController chatPageController;

    public static ChatPageController getChatPageController() {
        return chatPageController;
    }

    public static void run(String senderUsername, String receiverUsername) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/chat-page.fxml"));
        ChatPageController controller = new ChatPageController();
        chatPageController = controller;
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

    public void runForFirstTime() throws IOException, InterruptedException {
        disableButtons();
        clientChatController = new ClientChatController();
        Request request = new Request();
        request.setAction("get_messages");
        request.addData("senderUsername", senderUsername);
        request.addData("receiverUsername", receiverUsername);
        Response response = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        ArrayList<ChatMessage> messages = (ArrayList<ChatMessage>) response.getData().get("messages");
        clientChatController.addMessagesToChat(messages);
        showMessages();
        newMessageField.setOnKeyPressed(keyEvent -> {
            disableButtons();
            if (keyEvent.getCode().getCode() == 10 && !newMessageField.getText().equals("")) {
                Request request1 = new Request();
                request1.setAction("send_message");
                request1.addData("text", newMessageField.getText());
                request1.addData("senderUsername", senderUsername);
                request1.addData("receiverUsername", receiverUsername);
                try {
                    Client.getClientServerSocketController().justSendRequest(request1);
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (IOException | InterruptedException e) {
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
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                    showMessages();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showMessages() throws IOException, InterruptedException {
        messagesList.getChildren().clear();
        ArrayList<ChatMessage> messages = new ClientChatController().getMessagesOfChat(senderUsername,
                receiverUsername);
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
                holder.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        enableButtons();
                        removeButton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                disableButtons();
                                try {
                                    clientChatController.removeMessageFromServer(message);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            } else {
                text.setStyle("-fx-background-color: #effdde; -fx-padding: 12; -fx-background-radius: 12");
                holder.setAlignment(Pos.CENTER_LEFT);
            }
            vBox.getChildren().add(holder);
            Label textSent = new Label();
            textSent.setText("sent at " + message.getSentAt());
            vBox.getChildren().add(textSent);
            messagesList.getChildren().add(vBox);
        }
    }

    public void backClicked() {
        try {
            showMessages();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public void disableButtons() {
        this.removeButton.setDisable(true);
        this.editButton.setDisable(true);
        this.removeButton.setVisible(false);
        this.editButton.setVisible(false);
    }

    public void enableButtons() {
        this.removeButton.setDisable(false);
        this.editButton.setDisable(false);
        this.removeButton.setVisible(true);
        this.editButton.setVisible(true);
    }
}
