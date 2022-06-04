package game.civilization.Controller;

import game.civilization.MenuRegex.LoginMenuRegex;
import game.civilization.View.CurrentMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    public String changeNickname(String newNickname) {
        if (UserDatabase.isNicknameDuplicate(newNickname))
            return "user with nickname " + newNickname + " already exists";
        if (LoginMenuRegex.getMatcher(newNickname, LoginMenuRegex.NICKNAME_FORMAT_REGEX) == null) {
            return "nickname format is invalid";
        }
        UserDatabase.getCurrentUser().setNickname(newNickname);
        return "nickname changed successfully!";
    }

    public String changePassword(String newPassword, String oldPassword) {
        if (!Objects.equals(UserDatabase.getCurrentUser().getPassword(), oldPassword))
            return "current password is invalid";
        if (Objects.equals(oldPassword, newPassword))
            return "please enter a new password";
        if (LoginMenuRegex.getMatcher(newPassword, LoginMenuRegex.PASSWORD_FORMAT_REGEX) == null) {
            return "password is weak";
        }
        UserDatabase.getCurrentUser().setPassword(newPassword);
        return "password changed successfully!";
    }

    public String changeProfile(ImageView profile, String url) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(url);
        } catch (FileNotFoundException e) {
            return "url is not valid";
        }
        UserDatabase.getCurrentUser().setInputStream(true);
        UserDatabase.getCurrentUser().setProfileUrl(url);
        profile.setImage(new Image(inputStream));
        return "ok";
    }

}
