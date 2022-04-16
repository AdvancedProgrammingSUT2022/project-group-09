package Controller;

import Model.User;

import java.util.regex.Matcher;

public class MainMenuController extends Controller {

    private UserDatabase userDatabase;

    @Override
    public String menuNavigate(Matcher matcher) {
        return null;
    }

    public String logout(Matcher matcher) {
        return "";
    }
}
