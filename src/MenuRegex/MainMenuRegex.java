package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegex {
    dorosteshkon("");

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
