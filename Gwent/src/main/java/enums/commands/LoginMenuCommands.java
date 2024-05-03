package enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN("login -u (?<username>\\S+) -p (?<password>\\S+)");

    private final String regex;

    LoginMenuCommands(String command) {
        this.regex = command;
    }

    public String getRegex() {
        return regex;
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}
