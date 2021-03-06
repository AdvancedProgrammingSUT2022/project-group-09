package game.civilization.Model;

import com.google.gson.Gson;
import game.civilization.Main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private String lastWinTime; //DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
    private String lastLoginTime; //DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
    private int rank;
    private String profileUrl;
    private boolean inputStream = false;

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLastWinTime() {
        return this.lastWinTime;
    }

    public void setLastWinTime(String lastWinTime) {
        this.lastWinTime = lastWinTime;
    }

    public String getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = 0;
        profileUrl = Main.class.getResource("images/avatar/5.png").toExternalForm();
    }

    @Override
    public String toString() {
        return "username : " + username + "\n" +
                "password : " + password + "\n" +
                "nickname : " + nickname + "\n" +
                "score : " + score + "\n" +
                "lastWinTime: " + lastWinTime + "\n" +
                "lastLoginTime: " + lastLoginTime + "\n";
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public boolean isInputStream() {
        return inputStream;
    }

    public void setInputStream(boolean inputStream) {
        this.inputStream = inputStream;
    }
}
