package me.shuza.rxformvalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shuza on 9/22/17.
 */

public class Validator {
    public static boolean isValidEmailAddress(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
