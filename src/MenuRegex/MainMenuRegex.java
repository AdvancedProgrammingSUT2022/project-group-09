package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegex {
    SHOWCURRENTMENU("menu show-current"),
    ENTER("^menu enter (?<menuname>(Profile menu)|(Game menu)|(Main menu)|(Login menu)"),
    LOGOUT("logout"),
    EXIT("^menu exit");

    private final String regex;

    MainMenuRegex(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainMenuRegex command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
