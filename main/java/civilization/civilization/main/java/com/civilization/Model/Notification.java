package civilization.civilization.main.java.com.civilization.Model;

public class Notification {
    private StringBuilder stringBuilder;
    
    public Notification() {
        this.stringBuilder = new StringBuilder();
    }

    public void resetNotifiction() {
        this.stringBuilder = new StringBuilder();
    }

    public String getNotification() {
        return stringBuilder.toString();
    }

    public void updateNotification(String input) {
        this.stringBuilder.append(input + "\n");
    }
}
