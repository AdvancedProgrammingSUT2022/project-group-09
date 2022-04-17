package MenuRegex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegex {
    TURNCHEAT1("^increase (--turn|-t) (?<amount>-?[\\d]+)$"),
    GOLDCHEAT1("^increase (--gold|-g) (?<amount>-?[\\d]+)$"),
    SHOWCURRENTMENU("^menu show-current$"),
    ENTER("^menu enter (?<menuname>(Profile menu)|(Game menu)|(Main menu)|(Login menu)$"),
    EXIT("^menu exit$");

    private final String regex;

    GameMenuRegex(String regex) {
        this.regex = regex;
    }

    public static HashMap<String, Integer> playGameGetAllPlayerFromRegex(String input) {
        HashMap<String, Integer> usernames = new HashMap<>();
        String[] afterSplit = input.split("--");
        for (String s : afterSplit) {
            usernames.keySet().add(s.substring(10));
            usernames.put(s.substring(10), Integer.parseInt(s.substring(8, 9))); // hashmap baraye inke bebinim player chandome tartibesho ok konim
        }
        return usernames;
    }

    public static Matcher getMatcher(String input, GameMenuRegex command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
