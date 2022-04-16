package Controller;

import Model.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserDatabase {
    private final ArrayList<User> users;
    private User currentUser;

    public UserDatabase() {
        this.users = new ArrayList<>();
        this.currentUser = null;
        //TODO LOAD USER GSON
    }

    private void loadUsers() {
        //GSON
    }

    public void saveUsers() {
        //GSON
    }

    public boolean isUsernameDuplicate(User user) {
        return false;
    }

    public boolean isNicknameDuplicate(User user) {
        return false;
    }

    public User getUserFromUsers(User user) {
        for (User tmpUser : users) {
            if (Objects.equals(tmpUser.getUsername(), user.getUsername())) {
                return tmpUser;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }


}
