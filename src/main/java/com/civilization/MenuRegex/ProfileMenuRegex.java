package com.civilization.MenuRegex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuRegex {
    CHANGENICKNAME("^profile change (--nickname|-n) \\<(?<nickname>[\\S]+)\\>$"),
    //    CHANGENICKNAME2("^profile change -n \\<(?<nickname>[\\S]+)\\>$"),
    CHANGEPASSWORD1("^profile change (--password|-p) (--current|-c) \\<(?<currentpassword>[\\S]+)\\> (--new|-n) \\<(?<newpassword>[\\S]+)\\>$"),
    CHANGEPASSWORD2("^profile change (--password|-p) (--new|-n) \\<(?<newpassword>[\\S]+)\\> (--current|-c) \\<(?<currentpassword>[\\S]+)\\>$"),
    CHANGEPASSWORD(""),
    //    CHANGEPASSWORD3("^profile change -p -c \\<(?<currentpassword>[\\S]+)\\> -n \\<(?<newpassword>[\\S]+)\\>$"),
//    CHANGEPASSWORD4("^profile change -p -n \\<(?<newpassword>[\\S]+)\\> -c \\<(?<currentpassword>[\\S]+)\\>$"),
    SHOWCURRENTMENU("menu show-current"),
    ENTER("^menu enter (?<menuname>(Profile menu)|(Game menu)|(Main menu)|(Login menu)"),
    EXIT("^menu exit");

    private final String regex;
    private static final ArrayList<ProfileMenuRegex> changePasswordRegexes = new ArrayList<ProfileMenuRegex>() {
        {
            add(CHANGEPASSWORD1);
            add(CHANGEPASSWORD2);
        }
    };

    ProfileMenuRegex(String regex) {
        this.regex = regex;
    }

    private static Matcher changePassword(String input) {
        for (ProfileMenuRegex command : changePasswordRegexes) {
            Matcher matcher = Pattern.compile(command.regex).matcher(input);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return null;
    }

    public static Matcher getMatcher(String input, ProfileMenuRegex command) {
        if (command.equals(CHANGEPASSWORD))
            return changePassword(input);
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
