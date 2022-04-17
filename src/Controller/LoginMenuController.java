package Controller;

import Model.User;
import View.CurrentMenu;

import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenuController extends Controller {
    @Override
    public String menuNavigate(Matcher matcher) {
        String menuname = matcher.group("manuname");
        if (Objects.equals(menuname, "MainMenu")) {
            if (UserDatabase.getCurrentUser() != null) {
                CurrentMenu.set(CurrentMenu.MainMenu);
                return "entered MainMenu";
            }
            return "please login first";
        }
        return "menu navigation is not possible!";
    }

    public String register(Matcher matcher) {
        User newUser = new User(matcher.group("username"), matcher.group("password"), matcher.group("nickname"));
        if (UserDatabase.isUsernameDuplicate(newUser))
            return "user with username " + newUser.getUsername() + " already exists";
        if (UserDatabase.isNicknameDuplicate(newUser))
            return "user nickname " + newUser.getNickname() + " already exists";
        UserDatabase.addUser(newUser);
        return "user created successfuly!";
    }

    public String login(Matcher matcher) {
        User user = new User(matcher.group("username"), matcher.group("password"), "");
        if (!UserDatabase.isUsernameDuplicate(user)) return "Username and Password didnt match!";
        if (UserDatabase.isUsernameAndPasswordTrue(user)) return "Username and Password didnt match!";
        user = UserDatabase.getUserFromUsers(user);
        if (user == null) return "BUG!";
        UserDatabase.setCurrentUser(user);
        CurrentMenu.set(CurrentMenu.MainMenu);
        return "user logged in successfully!";
    }

    public String exit() {
        CurrentMenu.set(CurrentMenu.EndGame);
        return "Game Ended!";
    }

}
