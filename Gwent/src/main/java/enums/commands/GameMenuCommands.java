package enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    COMMAND("");

    private final String regex;

    GameMenuCommands(String command) {
        this.regex = command;
    }

    public String getRegex() {
        return regex;
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}
