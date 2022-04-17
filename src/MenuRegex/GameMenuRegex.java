package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegex {
    TURNCHEAT1("^increase --turn \\<(?<amount>\\-?[\\d]+)$"),
    TURNCHEAT2("^increase -t \\<(?<amount>\\-?[\\d]+)$"),
    GOLDCHEAT1("^increase --gold \\<(?<amount>\\-?[\\d]+)$"),
    GOLDCHEAT2("^increase -g \\<(?<amount>\\-?[\\d]+)$"),
    ENTER("^menu enter (?<menuname>Main menu)");

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
