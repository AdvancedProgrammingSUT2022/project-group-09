package game.civilization.Model.Chat;

import java.time.LocalDateTime;

import com.google.gson.Gson;

public class ChatMessage {
    private String senderUsername;
    private String receiverUsername;
    private String sentAt;
    private String text;

    public String getReceiverUsername() {
        return this.receiverUsername;
    }

    public String getSenderUsename() {
        return senderUsername;
    }

    public String getSentAt() {
        return sentAt;
    }

    public String getText() {
        return text;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt.toString();
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ChatMessage fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ChatMessage.class);
    }

    @Override
    public String toString() {
        return this.text + " from " + this.senderUsername + " to " + this.receiverUsername;
    }

}
