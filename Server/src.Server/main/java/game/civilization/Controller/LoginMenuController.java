package game.civilization.Controller;

import game.civilization.MenuRegex.LoginMenuRegex;
import game.civilization.Model.User;

import java.util.regex.Matcher;

public class LoginMenuController extends Controller {
    @Override
    public String menuNavigate(Matcher matcher) {
        return "menu navigation is not possible!";
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
}
