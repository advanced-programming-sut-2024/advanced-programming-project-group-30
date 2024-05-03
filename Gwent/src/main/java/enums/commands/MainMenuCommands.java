package enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    COMMAND("");

    private final String regex;

    MainMenuCommands(String command) {
        this.regex = command;
    }

    public String getCommand() {
        return regex;
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}