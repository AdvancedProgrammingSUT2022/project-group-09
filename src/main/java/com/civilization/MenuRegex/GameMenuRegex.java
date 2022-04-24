package com.civilization.MenuRegex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegex {
    TURNCHEAT1("^increase (--turn|-t) (?<amount>-?[\\d]+)$"),
    GOLDCHEAT1("^increase (--gold|-g) (?<amount>-?[\\d]+)$"),
    SHOWCURRENTMENU("^menu show-current$"),
    ENTER("^menu enter (?<menuname>(Profile menu)|(Game menu)|(Main menu)|(Login menu))$"),
    EXIT("^menu exit$"),
    SHOWMAP1("^show map (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    SHOWMAP2("^show map (--coordinates|-c) -y (?<y>-?[\\d]+) -x (?<x>-?[\\d]+)$"),
    SHOWMAP(""),
    MOVE1("^move unit to (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    MOVE2("^move unit to (--coordinates|-c) -y (?<y>-?[\\d]+) -x (?<x>-?[\\d]+)$"),
    RESEARCH("^show research"),
    UNITS("^show units$"),
    CITIES("^show cities$"),
    DIPLOMACY("^show diplomacy$"),
    VICTORY("^show victory$"),
    DEMOGRAPHICS("^show demographics$"),
    NOTIFICATIONS("^show notifications$"),
    MILITARY("^show military$"),
    ECONOMIC("^show economic$"),
    DIPLOMATIC("^show diplomatic$"),
    DEALS("^show deals$"),
    SELECTUNIT("^select unit (?<x>\\d+) (?<y>\\d+)$"),
    SELECTCITY1("^select city (?<x>\\d+) (?<y>\\d+)$"),
    SELECTCITY2("^select city (?<name>\\S+)$"),
    MOVETO("^move to (?<x>\\d+) (?<y>\\d+)$"),
    SLEEP("^sleep unit$"),
    ALERT("^alert unit$"),
    FORTIFY("fortify unit"),
    FORTIFYHEAL("^fortify heal$"),
    GARRISON("^garrison unit$"),
    SETUP("^setup ranged$"),
    ATTACK("^attack (?<x>\\d+) (?<y>\\d+)$"),
    FOUND("^found city$"),
    CANCEL("^cancel mission$"),
    WAKE("^wake unit$"),
    DELETE("^delete unit$"),
    ROAD("^build road$"),
    RAILROAD("^build railroad$"),
    FARM("^build farm$"),
    MINE("^build mine$"),
    TRADINGPOST("^build trading post$"),
    LUMBERMILL("^build lumber mill$"),
    PASTURE("^build pasture$"),
    CAMP("^build camp$"),
    PLANTATION("^build plantation$"),
    QUARRY("^build quarry$"),
    jungle("^remove jungle$"),
    ROUTE("^remove route$"),
    SHOW1("^show (?<x>\\d+) (?<y>\\d+)$"),
    SHOW2("^show (?<cityName>\\S+)$"),
    MOVE("^move (?<direction>(right|left|up|down))$");

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

    private static final ArrayList<GameMenuRegex> showmapRegexes = new ArrayList<GameMenuRegex>() {
        {
            add(SHOWMAP1);
            add(SHOWMAP2);
        }
    };

    private static final ArrayList<GameMenuRegex> moveRegexes = new ArrayList<GameMenuRegex>() {
        {
            add(MOVE1);
            add(MOVE2);
        }
    };

    private static Matcher getMatcherShowMap(String input) {
        for (GameMenuRegex command : showmapRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    private static Matcher getMatcherMove(String input) {
        for (GameMenuRegex command : moveRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    public static Matcher getMatcher(String input, GameMenuRegex command) {
        if (command.equals(SHOWMAP)) 
            return getMatcherShowMap(input);
        if (command.equals(MOVE))
            return getMatcherMove(input);
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

    public String getRegex() {
        return regex;
    }
}
