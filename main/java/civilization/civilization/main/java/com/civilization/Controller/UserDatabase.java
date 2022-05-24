package civilization.civilization.main.java.com.civilization.Controller;

import civilization.civilization.main.java.com.civilization.Model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserDatabase {
    static private final ArrayList<User> users = new ArrayList<>();
    static private User currentUser;

    static public void loadUsers() {
        try {
            File myObj = new File("UserDatabase.json");
            if (!myObj.exists())
                myObj.createNewFile();
            List<User> users1 = new ArrayList<>();
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("UserDatabase.json"));
            User[] tempList = gson.fromJson(reader, User[].class);
            if (tempList != null)
                users1 = Arrays.asList(tempList);
            ArrayList<User> userArrayList = new ArrayList<>(users1);
            reader.close();
            users.addAll(userArrayList);
        } catch (IOException e) {
            System.err.println("ERROR! in loading UserDatabase[JSON]");
            throw new RuntimeException(e);
        }
    }

    static public void saveUsers() {
        Gson gson = new Gson();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("UserDatabase.json"));
            gson.toJson(users, writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("ERROR! in SavingUser[JSON]");
            throw new RuntimeException(e);
        }
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
    
}