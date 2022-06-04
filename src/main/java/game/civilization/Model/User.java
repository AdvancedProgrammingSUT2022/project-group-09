package game.civilization.Model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private String lastWinTime;
    private String avatarPicturePath = "src/main/resources/game/civilization/images/avatar/default.jpg";
    private String lastLoginTime;
    private int rank;

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

    public String getAvatarPicturePath() {
        return this.avatarPicturePath;
    }

    public void setAvatarPicturePath(String avatarPicutrePath) {
        this.avatarPicturePath = avatarPicutrePath;
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
                "score : " + score + "\n" +
                "lastWinTime: " + lastWinTime + "\n" +
                "lastLoginTime: " + lastLoginTime + "\n";
    }

}
