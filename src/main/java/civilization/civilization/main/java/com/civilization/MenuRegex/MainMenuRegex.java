package civilization.civilization.main.java.com.civilization.MenuRegex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegex {
    PLAY_GAME("^play game (--player\\d \\S+).*$"),
    SHOW_CURRENT_MENU("menu show-current"),
    ENTER("^menu enter (?<menuname>(Profile menu)|(Game menu)|(Main menu)|(Login menu))"),
    LOGOUT("logout"),
    EXIT("^menu exit");

    private final String regex;

    MainMenuRegex(String regex) {
        this.regex = regex;
    }

    public static ArrayList<String> PlayGameRegexConvertToPlayers(String input) {
        HashMap<Integer, String> players = new HashMap<>();
        String[] afterSplit = input.substring(12).split("--");
        for (String s : afterSplit) {
            players.put(Integer.parseInt(s.substring(6, 7)), s.substring(8)); // hashmap baraye inke bebinim player chandome tartibesho ok konim
        }
        ArrayList<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= players.size(); i++) {
            if (players.get(i) == null)
                return null;
            playerNames.add(players.get(i));
        }
        return playerNames;
    }

    public static Matcher getMatcher(String input, MainMenuRegex command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
