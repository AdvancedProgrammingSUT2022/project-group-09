package civilization.civilization.main.java.com.civilization.Controller;

import civilization.civilization.main.java.com.civilization.MenuRegex.LoginMenuRegex;
import civilization.civilization.main.java.com.civilization.View.CurrentMenu;

import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenuController extends Controller {
    @Override
    public String menuNavigate(Matcher matcher) {
        String menu = matcher.group("menuname");
        if (Objects.equals(menu, "Main menu")) {
            CurrentMenu.set(CurrentMenu.MainMenu);
            return "entered Main Menu";
        }
        // exit();
        return "menu navigation is not possible";
    }

    public String exit() {
        CurrentMenu.set(CurrentMenu.MainMenu);
        return "entered Main Menu";
    }

    public String changeNickname(Matcher matcher) {
        String newNickname = matcher.group("nickname");
        if (UserDatabase.isNicknameDuplicate(newNickname))
            return "user with nickname " + newNickname + " already exists";
        if (LoginMenuRegex.getMatcher(newNickname, LoginMenuRegex.NICKNAME_FORMAT_REGEX) == null) {
            return "nickname format is invalid";
        }
        UserDatabase.getCurrentUser().setNickname(newNickname);
        return "nickname changed successfully!";
    }

    public String changePassword(Matcher matcher) {
        String oldPassword = matcher.group("currentpassword");
        String newPassword = matcher.group("newpassword");
        if (!Objects.equals(UserDatabase.getCurrentUser().getPassword(), oldPassword))
            return "current password is invalid";
        if (Objects.equals(oldPassword, newPassword))
            return "please enter a new password";
        if (LoginMenuRegex.getMatcher(newPassword, LoginMenuRegex.PASSWORD_FORMAT_REGEX) == null) {
            return "password is weak";
        }
        return "password changed successfully";
    }

}
