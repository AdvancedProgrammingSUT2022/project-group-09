package game.civilization.Model.Chat;

import java.util.ArrayList;

public class Chat {
    private String user1Nickname;
    private String user2Nickname;
    private ArrayList<ChatMessage> messages = new ArrayList<>();

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public String getUser1Nickname() {
        return this.user1Nickname;
    }

    public String getUser2Nickname() {
        return this.user2Nickname;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    public void setMessages(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setUser1Nickname(String user1Nickname) {
        this.user1Nickname = user1Nickname;
    }

    public void setUser2Nickname(String user2Nickname) {
        this.user2Nickname = user2Nickname;
    }
    
}
