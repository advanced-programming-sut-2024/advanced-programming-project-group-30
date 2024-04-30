package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN(""),
    USERNAME_FORMAT(""),
    STRONG_PASSWORD("");
    private final String regex;
    LoginMenuCommands(String command) {
        this.regex = command;
    }
    public String getRegex() {
        return regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(regex).matcher(input);
    }


}