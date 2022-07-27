package game.civilization.Model.Chat;

import java.util.ArrayList;

public class ServerChatDatabase {
    ArrayList<ChatMessage> messages = new ArrayList<>();
    private static ServerChatDatabase instance;

    private ServerChatDatabase() {
        
    }

    public static ServerChatDatabase getInstance() {
        if (instance == null)
            instance = new ServerChatDatabase();
        return instance;
    }

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }
}
