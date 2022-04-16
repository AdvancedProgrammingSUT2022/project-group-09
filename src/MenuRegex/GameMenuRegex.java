package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegex {
    dorosteshkon("");

    private String regex;

    GameMenuRegex(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuRegex command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }   
}
