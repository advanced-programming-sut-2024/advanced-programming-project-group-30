package enums;

public enum Error {
    REPEATED_USERNAME("Username has already been taken.", "001"),
    INVALID_USERNAME("Invalid username format", "002"),
    INVAlID_PASSWORD("Invalid password format", "003"),
    WEAK_PASSWORD("Password is too weak!", "004"),
    WRONG_PASSWORD_CONFIRMATION("Password and confirmation don't match", "005"),
    INVALID_EMAIL("Invalid email format", "006");

    public final String explanation;
    public final String code;

    Error(String message, String code) {
        this.explanation = message;
        this.code = code;
    }
    public static Error getErrorByCode(String name){
        return Error.valueOf(name);
    }
    @Override
    public String toString() {
        return explanation;
    }
    public String getCode(){
        return null;
    }
}
