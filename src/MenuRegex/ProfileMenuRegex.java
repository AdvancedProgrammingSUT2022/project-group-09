package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuRegex {
    dorosteshkon("");

    private String regex;

    ProfileMenuRegex(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileMenuRegex command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }   
}
