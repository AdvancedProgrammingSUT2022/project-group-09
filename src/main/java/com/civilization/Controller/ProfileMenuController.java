package com.civilization.Controller;

import com.civilization.View.CurrentMenu;
import com.sun.tools.javac.Main;

import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenuController extends Controller {
    @Override
    public String menuNavigate(Matcher matcher) {
        String menu = matcher.group("menaname");
        if (Objects.equals(menu, "Main Menu"))
            exit();
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
        return "password changed successfuly";
    }


}
