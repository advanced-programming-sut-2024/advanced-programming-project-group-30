package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ;
    private final String command;
    MainMenuCommands(String command){
        this.command = command;
    }
    public Matcher getMatcher(String input){
        return Pattern.compile(this.command).matcher(input);
    }
}
