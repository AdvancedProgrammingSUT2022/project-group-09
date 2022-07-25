package game.civilization.Controller;

import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.MenuRegex.LoginMenuRegex;
import game.civilization.Model.NetworkModels.Message;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.User;
import game.civilization.View.CurrentMenu;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenuController extends Controller {
    @Override
    public String menuNavigate(Matcher matcher) {
        String menuName = matcher.group("menuname");
        if (Objects.equals(menuName, "Main menu")) {
            if (UserDatabase.getCurrentUser() != null) {
                CurrentMenu.set(CurrentMenu.MainMenu);
                return "entered MainMenu";
            }
            return "please login first";
        }
        return "menu navigation is not possible!";
    }

    public String registerClient(String username, String nickname, String password) throws IOException {
        Request request = new Request();
        request.setAction("register");
        request.addData("username", username);
        request.addData("nickname", nickname);
        request.addData("password", password);
        Response message = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        System.out.println(message.getMessage());
        return message.getMessage();
    }

    public String loginClient(String username, String password) throws IOException {
        Request request = new Request();
        request.setAction("login");
        request.addData("username", username);
        request.addData("password", password);
        Response message = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        if (message.getAction().equals("login done")) {
            UserDatabase.setCurrentUser(User.fromJson(message.getMessage()));
            CurrentMenu.set(CurrentMenu.MainMenu);
            System.out.println("login : " + UserDatabase.getCurrentUser().getUsername());
            return "user logged in successfully!";
        }
        return message.getMessage();
    }

    public boolean isUserOnlineClient(String username) throws IOException {
        Request request = new Request();
        request.setAction("isOnline");
        request.addData("username", username);
        Response message = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        if (message.getAction().equals("isOnline done")) {
            if (message.getMessage().equals("online"))
                return true;
            else if (message.getMessage().equals("offline"))
                return false;
        }
        return false;
    }

    public String registerServer(String username, String nickname, String password) {
        if (LoginMenuRegex.getMatcher(username, LoginMenuRegex.USERNAME_FORMAT_REGEX) == null) {
            return "username format is invalid";
        }
        if (LoginMenuRegex.getMatcher(nickname, LoginMenuRegex.NICKNAME_FORMAT_REGEX) == null) {
            return "nickname format is invalid";
        }
        if (LoginMenuRegex.getMatcher(password, LoginMenuRegex.PASSWORD_FORMAT_REGEX) == null) {
            return "password is weak";
        }
        User newUser = new User(username, password, nickname);
        if (UserDatabase.isUsernameDuplicate(newUser))
            return "user with username " + newUser.getUsername() + " already exists";
        if (UserDatabase.isNicknameDuplicate(newUser))
            return "user nickname " + newUser.getNickname() + " already exists";
        UserDatabase.addUser(newUser);
        UserDatabase.saveUsers();
        return "user created successfully!";
    }

    public String loginServer(String username, String password) {
        User user = new User(username, password, "");
        if (!UserDatabase.isUsernameDuplicate(user)) {
            return "Username and Password didn't match!";
        }
        if (!UserDatabase.isUsernameAndPasswordTrue(user)) {
            return "Username and Password didn't match!";
        }
        user = UserDatabase.getUserFromUsers(user);
        if (user == null)
            return "BUG!";
        return "user logged in successfully!";
    }

    public void logoutClient() {
        if (UserDatabase.getCurrentUser() == null)
            return;
        UserDatabase.setCurrentUser(null);
    }

    public String exitClient() {
        CurrentMenu.set(CurrentMenu.EndGame);
        return "Game Ended!";
    }

}
