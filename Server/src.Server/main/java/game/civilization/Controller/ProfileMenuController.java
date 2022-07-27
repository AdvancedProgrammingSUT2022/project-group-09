package game.civilization.Controller;


import game.civilization.MenuRegex.LoginMenuRegex;
import game.civilization.Model.User;

import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenuController extends Controller {


    public String changeNicknameServer(User user,String newNickname) {
        if (UserDatabase.isNicknameDuplicate(newNickname))
            return "user with nickname " + newNickname + " already exists";
        if (LoginMenuRegex.getMatcher(newNickname, LoginMenuRegex.NICKNAME_FORMAT_REGEX) == null) {
            return "nickname format is invalid";
        }
        user.setNickname(newNickname);
        UserDatabase.saveUsers();
        return "nickname changed successfully!";
    }

    public String changePasswordServer(User user,String newPassword, String oldPassword) {
        System.out.println(oldPassword);
        System.out.println(newPassword);
        if (!Objects.equals(user.getPassword(), oldPassword))
            return "current password is invalid";
        if (Objects.equals(oldPassword, newPassword))
            return "please enter a new password";
        if (LoginMenuRegex.getMatcher(newPassword, LoginMenuRegex.PASSWORD_FORMAT_REGEX) == null) {
            return "password is weak";
        }
        user.setPassword(newPassword);
        UserDatabase.saveUsers();
        return "password changed successfully!";
    }

    public void changeProfileServer(User user, String url) {
        user.setInputStream(true);
        user.setProfileUrl(url);
        UserDatabase.saveUsers();
    }

    @Override
    public String menuNavigate(Matcher matcher) {
        return null;
    }
}
