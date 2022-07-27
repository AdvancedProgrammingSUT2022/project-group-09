package game.civilization.Model.Chat;

import java.util.ArrayList;

public class ClientChatDatabase {
    private static ClientChatDatabase instance = null;
    private ArrayList<ChatMessage> messages = new ArrayList<>();

    public static ClientChatDatabase getInstance() {
        if (instance == null)
            instance = new ClientChatDatabase();
        return instance;
    }

    public ArrayList<ChatMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

    public void addChat(ChatMessage message) {
        this.messages.add(message);
    }
}
