package enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    COMMAND("");

    private final String command;

    RegisterMenuCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(this.command).matcher(input);
    }
}