package civilization.civilization.main.java.com.civilization.Controller;

import civilization.civilization.main.java.com.civilization.Controller.GameControllerPackage.GameDataBase;
import civilization.civilization.main.java.com.civilization.MenuRegex.MainMenuRegex;
import civilization.civilization.main.java.com.civilization.Model.User;
import civilization.civilization.main.java.com.civilization.View.CurrentMenu;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;

public class MainMenuController extends Controller {

    @Override
    public String menuNavigate(Matcher matcher) {
        String menu = matcher.group("menuname");
        if (Objects.equals(menu, "Game menu"))
            return "use play game method";
        if (Objects.equals(menu, "Profile menu")) {
            CurrentMenu.set(CurrentMenu.ProfileMenu);
            return "entered Profile menu";
        }
        if (Objects.equals(menu, "Login menu"))
            return "use logout method";
        return "menu name is not valid";
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
        if (users.size() <= 1)
            return "ba kam tar az 2 nafar nemishe bazi kard";
        GameDataBase.runGameForFirstTime(users);
        CurrentMenu.set(CurrentMenu.GameMenu);
        return "you entered Game Menu";
    }
}
