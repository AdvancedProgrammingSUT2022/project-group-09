package game.civilization.Controller;

import game.civilization.Controller.NetworkController.Client.Client;
import game.civilization.MenuRegex.LoginMenuRegex;
import game.civilization.Model.Request;
import game.civilization.Model.Response;
import game.civilization.Model.User;
import game.civilization.View.CurrentMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public String changeNicknameCLinet(String newNickname) throws IOException {
        Request request = new Request();
        request.setAction("changeNickname");
        request.addData("nickname", newNickname);
        Response message = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        if (message.getAction().equals("change done")) {
            UserDatabase.setCurrentUser(User.fromJson(message.getMessage()));
            System.out.println("change nickname : " + UserDatabase.getCurrentUser().getNickname());
            return "nickname changed successfully!";
        }
        return message.getMessage();
    }

    public String changePasswordCLinet(String newPassword, String oldPassword) throws IOException {
        Request request = new Request();
        request.setAction("changePassword");
        request.addData("newPassword", newPassword);
        request.addData("oldPassword", oldPassword);
        Response message = Client.getClientServerSocketController().sendRequestAndGetResponse(request);
        if (message.getAction().equals("change done")) {
            UserDatabase.setCurrentUser(User.fromJson(message.getMessage()));
            System.out.println("change password : " + UserDatabase.getCurrentUser().getPassword());
            return "password changed successfully!";
        }
        return message.getMessage();
    }

    public String changeProfileCLinet(ImageView profile, String url) throws IOException {
        Request request = new Request();
        request.setAction("changePicture");
        request.addData("url",url);
        Client.getClientServerSocketController().justSendRequest(request);
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

}
