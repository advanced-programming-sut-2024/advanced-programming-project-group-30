package enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN("login -u (?<username>\\S+) -p (?<password>\\S+)");

    private final String command;

    LoginMenuCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(this.command).matcher(input);
    }
}