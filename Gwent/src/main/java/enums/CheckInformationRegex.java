package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheckInformationRegex {
    VALID_USERNAME("[a-zA-Z0-9_]+"),
    STRONG_PASSWORD("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}"),
    VALID_PASSWORD("[a-zA-Z0-9!@#$%^&*]+"),
    VALID_EMAIL("[A-Za-z0-9+_.-]+@(.+)");

    private final String regex;

    CheckInformationRegex(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}