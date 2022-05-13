package com.civilization.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Matcher;

import com.civilization.MenuRegex.ProfileMenuRegex;
import com.civilization.Model.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ProfileChangeTest {

    @BeforeEach
    public void loadUsers() {
        User user1 = new User("username1", "password1", "nickname1");
        User user2 = new User("username2", "password2", "nickname2");
        UserDatabase.addUser(user1);
        UserDatabase.addUser(user2);
        UserDatabase.setCurrentUser(user1);
    }

    @AfterEach
    public void removeUsers() {
        UserDatabase.getUsers().clear();
        UserDatabase.setCurrentUser(null);
    }

    @Test
    public void duplicateNickname() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("profile change -n nickname2", ProfileMenuRegex.CHANGE_NICKNAME);
        assertEquals("user with nickname " + matcher.group("nickname") + " already exists", profileMenuController.changeNickname(matcher));
    }

    @Test
    public void invalidNickname() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("profile change -n ndaf*732@@$3", ProfileMenuRegex.CHANGE_NICKNAME);
        assertEquals("nickname format is invalid", profileMenuController.changeNickname(matcher));
    }

    @Test
    public void changeNickname() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        Matcher matcher = ProfileMenuRegex.getMatcher("profile change -n nickname", ProfileMenuRegex.CHANGE_NICKNAME);
        assertEquals("nickname changed successfully!", profileMenuController.changeNickname(matcher));
    }

    @Test
    public void currentPasswordInvalid() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String input = "profile change -p -c sometmppassword -n password";
        Matcher matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.CHANGE_PASSWORD);
        assertEquals("current password is invalid", profileMenuController.changePassword(matcher));
    }

    @Test
    public void samePassword() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String input = "profile change -p -c password1 -n password1";
        Matcher matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.CHANGE_PASSWORD);
        assertEquals("please enter a new password", profileMenuController.changePassword(matcher));
    }

    @Test
    public void weakPassword() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String input = "profile change -p -c password1 -n password";
        Matcher matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.CHANGE_PASSWORD);
        assertEquals("password is weak", profileMenuController.changePassword(matcher));
    }

    @Test
    public void passwordChangeSuccessfull() {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String input = "profile change -p -c password1 -n adjf948297u*&(*(AFDKAKHSDJ";
        Matcher matcher = ProfileMenuRegex.getMatcher(input, ProfileMenuRegex.CHANGE_PASSWORD);
        assertEquals("password changed successfully", profileMenuController.changePassword(matcher));
    }

}
