package game.civilization.Model.Chat;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServerChatDatabase {
    ArrayList<ChatMessage> messages = new ArrayList<>();
    private static ServerChatDatabase instance;

    private ServerChatDatabase() {
        ChatMessage message = new ChatMessage();
        message.setReceiverUsername("aylin");
        message.setSenderUsername("pouya");
        message.setSentAt(LocalDateTime.now());
        message.setText("kos mikham");
        messages.add(message);
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
