package game.civilization.Controller.ChatController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import game.civilization.Controller.NetworkController.Server.Server;
import game.civilization.Controller.NetworkController.Server.ServerSocketController;
import game.civilization.Model.Response;
import game.civilization.Model.Chat.ChatMessage;
import game.civilization.Model.Chat.ServerChatDatabase;

public class ServerChatController {

    public static void addMessage(String text, String receiverUsername, String senderUsername) throws IOException {
        System.out.println("adding messaage");
        ChatMessage message = new ChatMessage();
        message.setText(text);
        message.setReceiverUsername(receiverUsername);
        message.setSenderUsername(senderUsername);
        message.setSentAt(LocalDateTime.now());

        // User receiver = UserDatabase.findUserByUsername(receiverUsername);
        // receiver.addReceivedMessage(message);
        // User sender = UserDatabase.findUserByUsername(senderUsername);
        // sender.addSentMessage(message);

        ServerChatDatabase.getInstance().getMessages().add(message);
        System.out.println("messages in server: " + ServerChatDatabase.getInstance().getMessages());
        Response response = new Response();
        response.setAction("message");
        response.addData("message", message.toJson());
        response.addData("fromUsername", senderUsername);
        ServerSocketController receiverSocketController = Server.getClientSocketByUsername(receiverUsername);
        if (receiverSocketController != null) {
            System.out.println("about to send this response directly");
            receiverSocketController.sendResponseDirectly(response);
            System.out.println("response sent successfully");
        }
        else {
            System.out.println("null shode in sockete lamasab");
        }
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
