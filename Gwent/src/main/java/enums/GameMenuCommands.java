package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    ;
    private final String command;
    GameMenuCommands(String command){
        this.command = command;
    }
    public Matcher getMatcher(String input){
        return Pattern.compile(this.command).matcher(input);
    }
}
