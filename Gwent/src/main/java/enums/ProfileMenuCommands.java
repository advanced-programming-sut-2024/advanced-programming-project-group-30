package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    ;
    private final String command;
    ProfileMenuCommands(String command){
        this.command = command;
    }
    public Matcher getMatcher(String input){
        return Pattern.compile(this.command).matcher(input);
    }
}
