package MenuRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuRegex {
    CHANGENICKNAME1("^profile change --nickname \\<(?<nickname>[\\S]+)\\>$"),
    CHANGENICKNAME2("^profile change -n \\<(?<nickname>[\\S]+)\\>$"),
    CHANGEPASSWORD1("^profile change --password --current \\<(?<currentpassword>[\\S]+)\\> --new \\<(?<newpassword>[\\S]+)\\>$"),
    CHANGEPASSWORD2("^profile change --password --new \\<(?<newpassword>[\\S]+)\\> --current \\<(?<currentpassword>[\\S]+)\\>$"),
    CHANGEPASSWORD3("^profile change -p -c \\<(?<currentpassword>[\\S]+)\\> -n \\<(?<newpassword>[\\S]+)\\>$"),
    CHANGEPASSWORD4("^profile change -p -n \\<(?<newpassword>[\\S]+)\\> -c \\<(?<currentpassword>[\\S]+)\\>$"),
    ENTER("^menu enter (?<menuname>(Main menu)|(Profile menu)|(Game menu))");

    private final String regex;

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
