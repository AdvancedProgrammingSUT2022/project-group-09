package game.civilization.Model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private String lastWinTime;
    private String avatarPicutrePath;
    private String lastLoginTime;

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

    public int getscore() {
        return score;
    }

    public void setscore(int score) {
        this.score = score;
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

    public String getAvatarPicutrePath() {
        return this.avatarPicutrePath;
    }

    public void setAvatarPicutrePath(String avatarPicutrePath) {
        this.avatarPicutrePath = avatarPicutrePath;
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
    }

    @Override
    public String toString() {
        return "username : " + username + "\n" +
                "password : " + password + "\n" +
                "nickname : " + nickname + "\n" +
                "score : " + score;
    }

}
