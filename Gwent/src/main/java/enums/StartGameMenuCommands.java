package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StartGameMenuCommands {
    ;
    private final String command;
    StartGameMenuCommands(String command){
        this.command = command;
    }
    public Matcher getMatcher(String input){
        return Pattern.compile(this.command).matcher(input);
    }
}
