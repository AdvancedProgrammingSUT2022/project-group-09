package game.civilization.Controller.ChatController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import game.civilization.Controller.UserDatabase;
import game.civilization.Controller.NetworkController.Server.Server;
import game.civilization.Controller.NetworkController.Server.ServerSocketController;
import game.civilization.Model.Response;
import game.civilization.Model.User;
import game.civilization.Model.Chat.ChatMessage;
import game.civilization.Model.Chat.ServerChatDatabase;
import game.civilization.Model.NetworkModels.Message;

public class ServerChatController {

    public static void addMessage(String text, String receiverUsername, String senderUsername) throws IOException {
        ChatMessage message = new ChatMessage();
        message.setText(text);
        message.setReceiverUsername(receiverUsername);
        message.setSenderUsername(senderUsername);
        message.setSentAt(LocalDateTime.now());

        User receiver = UserDatabase.findUserByUsername(receiverUsername);
        receiver.addReceivedMessage(message);
        User sender = UserDatabase.findUserByUsername(senderUsername);
        sender.addSentMessage(message);

        ServerChatDatabase.getInstance().getMessages().add(message);
        Response response = new Response();
        response.addData("message", message);
        response.addData("fromUsername", senderUsername);
        ServerSocketController receiverSocketController = Server.getClientSocketByUsername(receiverUsername);
        receiverSocketController.sendResponseDirectly(response);
        System.out.println("update sent to desired user");
    }

    public static ArrayList<ChatMessage> getMessagesOfChat(String receiverUsername, String senderUsername) {
        ArrayList<ChatMessage> allMessages = ServerChatDatabase.getInstance().getMessages();
        ArrayList<ChatMessage> messages = new ArrayList<>();
        for (ChatMessage message : allMessages) {
            if ((message.getReceiverUsername().equals(receiverUsername)
                    && message.getSenderUsename().equals(senderUsername))
                    || (message.getReceiverUsername().equals(senderUsername)
                            && message.getSenderUsename().equals(receiverUsername))) {
                messages.add(message);
            }
        }
        messages.sort(Comparator.comparing(ChatMessage::getSentAt));
        return messages;
    }


}
