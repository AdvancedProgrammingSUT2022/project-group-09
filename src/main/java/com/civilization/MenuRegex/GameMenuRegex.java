package com.civilization.MenuRegex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegex {
    SHOWINFO("show info"),
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
    MOVEUNIT(""),
    SHOWRESEARCHINFORMATION("^show research information"),
    SHOWUNITS("^show units$"),
    SHOWCITIES("^show cities$"),
    SHOWDIPLOMACYINFORMATION("^show diplomacy information$"),
    SHOWVICTORYINFORMATION("^show victory information$"),
    SHOWDEMOGRAPHICSINFORMATION("^show demographics information$"),
    SHOWNOTIFICATIONS("^show notifications$"),
    SHOWMILITARYINFORMATION("^show military information$"),
    SHOWECONOMICINFORMATION("^show economic information$"),
    SHOWDIPLOMATICINFORMATION("^show diplomatic information$"),
    SHOWDEALS("^show deals$"),
    SELECTMILITARYUNIT("^select military unit (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$$"),
    SELECTWORKER("^select worker (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$$"), SELECTSETTLER("^select settler (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$$"),
    SELECTCITYCOORDINATE("^select city (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$$"),
    SELECTCITYNAME("^select city (--name|-n) (?<name>\\S+)$"),
    //    MOVETO("^move to (?<x>\\d+) (?<y>\\d+)$"),
    SLEEP("^sleep unit$"),
    ALERT("^alert unit$"),
    FORTIFY("fortify unit"),
    FORTIFYHEAL("^fortify heal$"),
    GARRISON("^garrison unit$"),
    SETUP("^setup ranged$"),
    ATTACK("^attack (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    FOUND("^found city$"),
    CANCEL("^cancel mission$"),
    WAKE("^wake unit$"),
    DELETE("^delete unit$"),
    BUILDROAD("^build road$"),
    BUILDRAILROAD("^build railroad$"),
    BUILDFARM("^build farm$"),
    BUILDMINE("^build mine$"),
    BUILDTRADINGPOST("^build trading post$"),
    BUILDLUMBERMILL("^build lumber mill$"),
    BUILDPASTURE("^build pasture$"),
    BUILDCAMP("^build camp$"),
    BUILDPLANTATION("^build plantation$"),
    BUILDQUARRY("^build quarry$"),
    REMOVEJUNGLE("^remove jungle$"),
    REMOVEROUTE("^remove route$"),
    REPAIR("^repair$"),
    MOVEMAP("^move map (?<number>\\d+) to (?<direction>(right|left|up|down))$"),
    CHOOSETECHNOLOGY("start working on (?<technology>[a-z ]*)"),
    SHOWTECHNOLOGYTREE("show technology tree"),

    SHOWCITYINFO("show info"),
    SETCITIZEN("set citizen to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    MOVECITIZEN("move citizen -x (?<x>-?\\d+) -y (?<y>-?\\d+) to -x (?<xx>-?\\d+) -y (?<yy>-?\\d+)"),
    TECHNOLOGYMENU("enter technology menu");

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
        if (command.equals(MOVEUNIT))
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
