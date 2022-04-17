package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuRegex {
    CREATE1("^user create --username \\<(?<username>[\\S]+)\\> --nickname \\<(?<nickname>[\\S]+)\\> --password \\<(?<password>[\\S]+)\\>$"),
    CREATE2("^user create --username \\<(?<username>[\\S]+)\\> --password \\<(?<password>[\\S]+)\\> --nickname \\<(?<nickname>[\\S]+)\\>$"),
    CREATE3("^user create --nickname \\<(?<nickname>[\\S]+)\\> --username \\<(?<username>[\\S]+)\\> --password \\<(?<password>[\\S]+)\\>$"),
    CREATE4("^user create --nickname \\<(?<nickname>[\\S]+)\\> --password \\<(?<password>[\\S]+)\\> --username \\<(?<username>[\\S]+)\\>$"),
    CREATE5("^user create --password \\<(?<password>[\\S]+)\\> --username \\<(?<username>[\\S]+)\\> --nickname \\<(?<nickname>[\\S]+)\\>$"),
    CREATE6("^user create --password \\<(?<password>[\\S]+)\\> --nickname \\<(?<nickname>[\\S]+)\\> --username \\<(?<username>[\\S]+)\\>$"),
    CREATE7("^user create -u \\<(?<username>[\\S]+)\\> -n \\<(?<nickname>[\\S]+)\\> -p \\<(?<password>[\\S]+)\\>$"),
    CREATE8("^user create -u \\<(?<username>[\\S]+)\\> -p \\<(?<password>[\\S]+)\\> -n \\<(?<nickname>[\\S]+)\\>$"),
    CREATE9("^user create -n \\<(?<nickname>[\\S]+)\\> -u \\<(?<username>[\\S]+)\\> -p \\<(?<password>[\\S]+)\\>$"),
    CREATE10("^user create -n \\<(?<nickname>[\\S]+)\\> -p \\<(?<password>[\\S]+)\\> -u \\<(?<username>[\\S]+)\\>$"),
    CREATE11("^user create -p \\<(?<password>[\\S]+)\\> -u \\<(?<username>[\\S]+)\\> -n \\<(?<nickname>[\\S]+)\\>$"),
    CREATE12("^user create -p \\<(?<password>[\\S]+)\\> -n \\<(?<nickname>[\\S]+)\\> -u \\<(?<username>[\\S]+)\\>$"),
    LOGIN1("^user login --username \\<(?<username>\\S+)\\> --password \\<(?<password>\\S+)\\>$"),
    LOGIN2("^user login --password \\<(?<password>[\\S]+)\\> --username \\<(?<username>[\\S]+)\\>$"),
    LOGIN3("^user login -u \\<(?<username>[\\S]+)\\> -p \\<(?<password>[\\S]+)\\>$"),
    LOGIN4("^user login -p \\<(?<password>[\\S]+)\\> -u \\<(?<username>[\\S]+)\\>$"),
    ENTER("^menu enter (?<menuname>\\S+)"),
    EXIT("^menu exit"),
    SHOWMENU("^menu show-current");
    //TODO complete enter menu regex

    private final String regex;

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
