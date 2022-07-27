package game.civilization.Controller.ChatController;

import java.io.IOException;
import java.util.ArrayList;

import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.FxmlController.ChatPageController;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.User;
import game.civilization.Model.Chat.ChatMessage;
import game.civilization.Model.Chat.ClientChatDatabase;
import javafx.application.Platform;

public class ClientChatController {
    public ArrayList<User> getOnlineUsers() throws IOException {
        ArrayList<User> onlineUsers = new ArrayList<>();
        Request request = new Request();
        request.setAction("get online users");
        Response response = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        int count = (int) Math.floor((Integer) response.getData().get("count"));
        for (int i = 0; i < count; i++) {
            onlineUsers.add(User.fromJson((String) response.getData().get("user" + i)));
        }
        System.out.println("baw inja dare kar mikone");
        return onlineUsers;
    }

    public ArrayList<ChatMessage> getMessagesFromServer(String senderUsername, String receiverUsername)
            throws IOException {
        Request request = new Request();
        request.setAction("get_messages");
        request.addData("senderUsername", senderUsername);
        request.addData("receiverUsername", receiverUsername);
        Response response = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        ArrayList<ChatMessage> messages = (ArrayList<ChatMessage>) response.getData().get("messages");
        return messages;
    }

    public ArrayList<ChatMessage> getMessagesOfChat(String user1Nickname, String user2Nickname) {
        ArrayList<ChatMessage> messages = ClientChatDatabase.getInstance().getMessages();
        ArrayList<ChatMessage> ans = new ArrayList<>();
        for (ChatMessage message : messages) {
            if ((message.getReceiverUsername().equals(user1Nickname)
                    && message.getSenderUsename().equals(user2Nickname))
                    || (message.getSenderUsename().equals(user1Nickname)
                            && message.getReceiverUsername().equals(user2Nickname))) {
                ans.add(message);
            }
        }
        System.out.println("finished getting message");
        return ans;
    }

    public void removeMessageFromServer(ChatMessage message) throws IOException {
        ClientChatDatabase.getInstance().getMessages().remove(message);
        Request request = new Request();
        request.setAction("remove message");
        request.addData("senderUsername", message.getSenderUsename());
        request.addData("receiverUsername", message.getReceiverUsername());
        request.addData("text", message.getText());
        Client.getClientServerSocketController().justSendRequest(request);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChatPageController.getChatPageController().showMessages();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void removeMessageFromChat(String text, String user1Username, String user2Username) {
        ArrayList<ChatMessage> messages = ClientChatDatabase.getInstance().getMessages();
        for (int i = 0; i < messages.size(); i++) {
            ChatMessage message = messages.get(i);
            if (message.getText().equals(text)) {
                if ((message.getSenderUsename().equals(user1Username)
                        && message.getReceiverUsername().equals(user2Username))
                        || (message.getSenderUsename().equals(user2Username)
                                && message.getReceiverUsername().equals(user1Username))) {
                    ClientChatDatabase.getInstance().getMessages().remove(i);
                    return;
                }
            }
        }
    }

    public void addMessagesToChat(ArrayList<ChatMessage> messages) {
        for (ChatMessage message : messages)
            ClientChatDatabase.getInstance().addChat(message);
    }

    public void addMessageToChat(ChatMessage message) {
        ClientChatDatabase.getInstance().addChat(message);
    }
}
