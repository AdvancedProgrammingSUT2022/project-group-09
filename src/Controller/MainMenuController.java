package Controller;

import Controller.GameControllerPackage.GameDataBase;
import MenuRegex.MainMenuRegex;
import MenuRegex.ProfileMenuRegex;
import Model.User;
import View.CurrentMenu;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;

public class MainMenuController extends Controller {

    private UserDatabase userDatabase;

    @Override
    public String menuNavigate(Matcher matcher) {
        String menu = matcher.group("menuname");
        if (Objects.equals(menu, "Game Menu"))
            return "use play game method";
        if (Objects.equals(menu, "Profile Menu")) {
            CurrentMenu.set(CurrentMenu.ProfileMenu);
            return "entered Profile Menu";
        }
        if (Objects.equals(menu, "Login Menu"))
            return "use logout method";
        return "menu navigation is not possible";
    }

    public String logout() {
        UserDatabase.setCurrentUser(null);
        CurrentMenu.set(CurrentMenu.LoginMenu);
        return "user logged out successfully";
    }

    public String playGame(String input) {
        ArrayList<String> usernames = MainMenuRegex.PlayGameRegexConvertToPlayers(input);
        if (usernames == null) return "you entered wrong number";
        ArrayList<User> users = new ArrayList<User>();
        for (String username : usernames) {
            if (UserDatabase.findUserByUsername(username) == null)
                return "user with username " + username + " doesn't exist";
            users.add(UserDatabase.findUserByUsername(username));
        }
        GameDataBase.runGameForFirstTime(users);
        CurrentMenu.set(CurrentMenu.GameMenu);
        return "you entered Game Menu";
    }
}
