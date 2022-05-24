package civilization.civilization.main.java.com.civilization.MenuRegex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegex {
    SHOW_INFO("show info"),
    SHOW_CURRENT_MENU("^menu show-current$"),
    ENTER("^menu enter (?<menuname>(Profile menu)|(Game menu)|(Main menu)|(Login menu))$"),
    EXIT("^menu exit$"),
    SHOW_MAP1("^show map (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    SHOW_MAP2("^show map (--coordinates|-c) -y (?<y>-?[\\d]+) -x (?<x>-?[\\d]+)$"),
    SHOW_MAP(""),
    SHOW_DETAILS1("^show details (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    SHOW_DETAILS2("^show details (--coordinates|-c) -y (?<y>-?[\\d]+) -x (?<x>-?[\\d]+)$"),
    SHOW_DETAILS(""),
    MOVE1("^move unit to (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    MOVE2("^move unit to (--coordinates|-c) -y (?<y>-?[\\d]+) -x (?<x>-?[\\d]+)$"),
    MOVE_UNIT(""),
    SHOW_RESEARCH_INFORMATION("^show research information"),
    SHOW_UNITS("^show units$"),

    SHOW_HAPPINESS("show happiness"),
    SHOW_CITIES("^show cities$"),
    SHOW_DIPLOMACY_INFORMATION("^show diplomacy information$"),
    SHOW_VICTORY_INFORMATION("^show victory information$"),
    SHOW_DEMOGRAPHICS_INFORMATION("^show demographics information$"),
    SHOW_NOTIFICATIONS("^show notifications$"),
    RESET_NOTIFICATIONS("^reset notifications$"),
    SHOW_MILITARY_INFORMATION("^show military information$"),
    SHOW_ECONOMIC_INFORMATION("^show economic information$"),
    SHOW_DIPLOMATIC_INFORMATION("^show diplomatic information$"),
    SHOW_DEALS("^show deals$"),
    SELECT_MILITARY_UNIT("^select military unit (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    SELECT_WORKER("^select worker (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    SELECT_SETTLER("^select settler (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    SELECT_CITY_COORDINATE1("^select city (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    SELECT_CITY_COORDINATE2("^select city (--coordinates|-c) -y (?<y>-?[\\d]+)-x (?<x>-?[\\d]+)$"),
    SELECT_CITY_COORDINATE(""),
    SELECT_CITY_NAME("^select city (--name|-n) (?<name>\\S+)$"),
    SLEEP("^sleep unit$"),
    ALERT("^alert unit$"),
    FORTIFY("fortify unit"),
    FORTIFY_UNTIL_HEAL("^fortify heal$"),
    GARRISON("^garrison unit$"),
    SETUP("^setup ranged$"),
    ATTACK1("^attack (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$"),
    ATTACK2("^attack (--coordinates|-c) -y (?<y>-?[\\d]+) -x (?<x>-?[\\d]+)$"),
    ATTACK(""),
    PILLAGE("pillage"),
    FOUND("^found city$"),
    DO_NOTHING("^do nothing$"),
    WAKE("^wake unit$"),
    DELETE("^delete unit$"),
    BUILD_ROAD("^build road$"),
    BUILD_RAILROAD("^build railroad$"),
    BUILD_FARM("^build farm$"),
    BUILD_MINE("^build mine$"),
    BUILD_TRADING_POST("^build trading post$"),
    BUILD_LUMBER_MILL("^build lumber mill$"),
    BUILD_PASTURE("^build pasture$"),
    BUILD_CAMP("^build camp$"),
    BUILD_PLANTATION("^build plantation$"),
    BUILD_QUARRY("^build quarry$"),
    REMOVE_JUNGLE("^remove jungle$"),
    REMOVE_ROUTE("^remove route$"),
    REMOVE_MARSH("remove marsh"),
    REMOVE_FOREST("remove forest"),
    REPAIR("^repair$"),
    MOVE_MAP("^move map (--number|-n) (?<number>\\d+) to (--direction|-d) (?<direction>(right|left|up|down))$"),
    CHOOSE_TECHNOLOGY("start working on (--technology|-tch) (?<technology>[a-z ]*)"),
    SHOW_TECHNOLOGY_TREE("show technology tree"),

    SHOW_CITY_INFO("show info"),
    SET_CITIZEN1("set citizen to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    SET_CITIZEN2("set citizen to -y (?<y>-?\\d+) -x (?<x>-?\\d+)"),
    SET_CITIZEN(""),
    MOVE_CITIZEN1("move citizen -x (?<x>-?\\d+) -y (?<y>-?\\d+) to -x (?<xx>-?\\d+) -y (?<yy>-?\\d+)"),
    MOVE_CITIZEN2("move citizen -y (?<y>-?\\d+) -x (?<x>-?\\d+) to -x (?<xx>-?\\d+) -y (?<yy>-?\\d+)"),
    MOVE_CITIZEN3("move citizen -x (?<x>-?\\d+) -y (?<y>-?\\d+) to -y (?<yy>-?\\d+) -x (?<xx>-?\\d+)"),
    MOVE_CITIZEN4("move citizen -y (?<y>-?\\d+) -x (?<x>-?\\d+) to -y (?<yy>-?\\d+) -x (?<xx>-?\\d+)"),
    MOVE_CITIZEN(""),

    REMOVE_CITIZEN("remove citizen -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    TECHNOLOGY_MENU("enter technology menu"),
    BUILD_MENU("enter build menu"),

    BUY_TILE("buy terrain -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),

    DELETE_CITY("delete city"),
    BUILD_UNIT("build unit (--number|-n) (?<number>\\d+)"),
    BUILD_UNIT_GOLD("build unit (--gold|-g) (--number|-n) (?<number>\\d+)"),
    NEXT_TURN("next turn"),
    BUILD_BUILDING("build building (--number|-n) (?<number>\\d+)"),
    BUILD_BUILDING_GOLD("build building (--gold|-g) (--number|-n) (?<number>\\d+)"),
    ENTER_CHEAT_MENU("enter cheat menu"),
    INCREASE_GOLD("increase gold (--number|-n) (?<number>\\d+)"),
    INCREASE_SCIENCE("increase science (--number|-n) (?<number>\\d+)"),
    INCREASE_TURN("increase turn (--number|-n) (?<number>\\d+)"),
    INCREASE_HAPPINESS("increase happiness (--number|-n) (?<number>\\d+)"),
    UNIT_RESET("unit reset"),

    SET_ARCHER("set archer to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    SET_TANK("set tank to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    SET_ARTILLERY("set artillery to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    SET_CANNON("set cannon to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    SET_LANCER("set lancer to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    SET_SETTLER("set settler to -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    OPEN_ALL_TECHNOLOGIES("open all technologies")
    ;


    private final String regex;

    GameMenuRegex(String regex) {
        this.regex = regex;
    }

    private static final ArrayList<GameMenuRegex> showMapRegexes = new ArrayList<>() {
        {
            add(SHOW_MAP1);
            add(SHOW_MAP2);
        }
    };

    private static final ArrayList<GameMenuRegex> moveRegexes = new ArrayList<>() {
        {
            add(MOVE1);
            add(MOVE2);
        }
    };

    private static final ArrayList<GameMenuRegex> showDetailsRegexes = new ArrayList<>() {
        {
            add(SHOW_DETAILS1);
            add(SHOW_DETAILS2);
        }
    };

    private static final ArrayList<GameMenuRegex> attackRegexes = new ArrayList<>() {
        {
            add(ATTACK1);
            add(ATTACK2);
        }
    };

    private static final ArrayList<GameMenuRegex> selectCityCoordinateRegexes = new ArrayList<>() {
        {
            add(SELECT_CITY_COORDINATE1);
            add(SELECT_CITY_COORDINATE2);
        }
    };

    private static final ArrayList<GameMenuRegex> setCitizenRegexes = new ArrayList<>() {
        {
            add(SET_CITIZEN1);
            add(SET_CITIZEN2);
        }
    };

    private static final ArrayList<GameMenuRegex> moveCitizenRegexes = new ArrayList<>() {
        {
            add(MOVE_CITIZEN1);
            add(MOVE_CITIZEN2);
            add(MOVE_CITIZEN3);
            add(MOVE_CITIZEN4);
        }
    };

    private static Matcher getMatcherShowMap(String input) {
        for (GameMenuRegex command : showMapRegexes) {
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

    private static Matcher getMatcherShowDetails(String input) {
        for (GameMenuRegex command : showDetailsRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    private static Matcher getMatcherAttack(String input) {
        for (GameMenuRegex command : attackRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    private static Matcher getMatcherSelectCityCoordinate(String input) {
        for (GameMenuRegex command : selectCityCoordinateRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    private static Matcher getMatcherSetCitizen(String input) {
        for (GameMenuRegex command : setCitizenRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    private static Matcher getMatcherMoveCitizen(String input) {
        for (GameMenuRegex command : moveCitizenRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    public static Matcher getMatcher(String input, GameMenuRegex command) {
        if (command.equals(SHOW_MAP))
            return getMatcherShowMap(input);
        if (command.equals(MOVE_UNIT))
            return getMatcherMove(input);
        if (command.equals(SHOW_DETAILS))
            return getMatcherShowDetails(input);
        if (command.equals(ATTACK))
            return getMatcherAttack(input);
        if (command.equals(SELECT_CITY_COORDINATE))
            return getMatcherSelectCityCoordinate(input);
        if (command.equals(SET_CITIZEN))
            return getMatcherSetCitizen(input);
        if (command.equals(MOVE_CITIZEN))
            return getMatcherMoveCitizen(input);
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
