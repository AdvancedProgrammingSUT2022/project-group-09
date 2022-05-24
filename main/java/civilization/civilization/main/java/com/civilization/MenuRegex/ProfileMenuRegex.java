package civilization.civilization.main.java.com.civilization.MenuRegex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuRegex {
    CHANGE_NICKNAME("^profile change (--nickname|-n) (?<nickname>[\\S]+)$"),
    CHANGE_PASSWORD1("^profile change (--password|-p) (--current|-c) (?<currentpassword>[\\S]+) (--new|-n) (?<newpassword>[\\S]+)$"),
    CHANGE_PASSWORD2("^profile change (--password|-p) (--new|-n) (?<newpassword>[\\S]+) (--current|-c) (?<currentpassword>[\\S]+)$"),
    CHANGE_PASSWORD(""),
    SHOW_CURRENT_MENU("menu show-current"),
    ENTER("^menu enter (?<menuname>(Profile menu)|(Game menu)|(Main menu)|(Login menu))"),
    EXIT("^menu exit");

    private final String regex;
    private static final ArrayList<ProfileMenuRegex> changePasswordRegexes = new ArrayList<>() {
        {
            add(CHANGE_PASSWORD1);
            add(CHANGE_PASSWORD2);
        }
    };

    ProfileMenuRegex(String regex) {
        this.regex = regex;
    }

    private static Matcher changePassword(String input) {
        for (ProfileMenuRegex command : changePasswordRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    public static Matcher getMatcher(String input, ProfileMenuRegex command) {
        if (command.equals(CHANGE_PASSWORD))
            return changePassword(input);
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
