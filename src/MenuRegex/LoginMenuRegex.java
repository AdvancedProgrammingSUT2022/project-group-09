package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuRegex {
    dorosteshkon("");

    private String regex;

    LoginMenuRegex(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuRegex command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }   
}
