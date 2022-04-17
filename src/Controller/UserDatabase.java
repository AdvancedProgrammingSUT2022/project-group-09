package Controller;

import Model.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserDatabase {
    static private ArrayList<User> users;
    static private User currentUser;

    static private void loadUsers() {
        //GSON
    }

    static public void saveUsers() {
        //GSON
    }

    static public boolean isUsernameDuplicate(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getUsername(), user.getUsername()))
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

    static public User getUserFromUsers(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getUsername(), user.getUsername()) &&
                    Objects.equals(user1.getPassword(), user.getPassword()))
                return user1;
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


}
