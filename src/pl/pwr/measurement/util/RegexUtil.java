package pl.pwr.measurement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    private RegexUtil() {       }
    public static boolean checkIP(String IP) {
        final String regex = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
        Pattern checkRegex = Pattern.compile(regex);
        Matcher regexMatcher = checkRegex.matcher(IP);
        if (regexMatcher.find()) {
            return true;
        } else {
            return false;
        }
    }
}
