package game.civilization.Controller;

import game.civilization.Controller.NetworkController.GameServer.SqlHandler;
import game.civilization.Model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserDatabase {
    static private ArrayList<User> users = new ArrayList<>();
    static private User currentUser;

    static public void loadUsers() {
        SqlHandler.loadUsers();
    }

    public static void updateData() {
        if (currentUser != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            currentUser.setLastLoginTime(dtf.format(now));
        }
        saveUsers();
    }

    static public void saveUsers() {
        SqlHandler.saveUsers();
    }

    static public boolean isUsernameDuplicate(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getUsername(), user.getUsername()))
                return true;
        }
        return false;
    }

    static public boolean isUsernameDuplicate(String username) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username))
                return true;
        }
        return false;
    }

    static public boolean isNicknameDuplicate(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getNickname(), user.getNickname()))
                return true;
        }
        return false;
    }

    static public boolean isNicknameDuplicate(String nickname) {
        for (User user : users) {
            if (Objects.equals(user.getNickname(), nickname))
                return true;
        }
        return false;
    }

    static public User getUserFromUsers(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getUsername(), user.getUsername()) &&
                    Objects.equals(user1.getPassword(), user.getPassword()))
                return user1;
        }
        return null;
    }

    static public User findUserByUsername(String username) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username))
                return user;
        }
        return null;
    }

    static public User findUserByPassword(String password) {
        for (User user : users) {
            if (Objects.equals(user.getPassword(), password))
                return user;
        }
        return null;
    }

    static public User findUserByNickname(String nickname) {
        for (User user : users) {
            if (Objects.equals(user.getNickname(), nickname))
                return user;
        }
        return null;
    }

    static public boolean isUsernameAndPasswordTrue(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getUsername(), user.getUsername()) &&
                    Objects.equals(user1.getPassword(), user.getPassword()))
                return true;
        }
        return false;
    }


    static public User getCurrentUser() {
        return currentUser;
    }

    static public void setCurrentUser(User currentUser) {
        UserDatabase.currentUser = currentUser;
    }

    static public ArrayList<User> getUsers() {
        return users;
    }

    static public void addUser(User user) {
        users.add(user);
    }

    public static void setUsers(ArrayList<User> users) {
        UserDatabase.users = users;
    }

}