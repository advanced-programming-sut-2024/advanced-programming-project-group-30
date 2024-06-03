package enums;

public enum Error {

    REPEATED_USERNAME("Username has already been taken.", 1),
    INVALID_USERNAME("Invalid username format", 2),
    INVAlID_PASSWORD("Invalid password format", 3),
    WEAK_PASSWORD("Password is too weak!", 4),
    WRONG_PASSWORD_CONFIRMATION("Password and confirmation don't match", 5),
    INVALID_EMAIL("Invalid email format", 6),
    REGISTER_SUCCESSFUL("Registered successfully", 7),
    WRONG_ANSWER_CONFIRMATION("Answer and confirmation don't match", 8),
    SECURITY_QUESTION_DONE("Security question added successfully", 9);
    public final String explanation;
    public final int code;

    Error(String message, int code) {
        this.explanation = message;
        this.code = code;
    }

    public static enums.Error getErrorByCode(int code) {
        for (Error errorCode : Error.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        throw new IllegalArgumentException("No error with this code");
    }

    @Override
    public String toString() {
        return explanation;
    }

    public int getCode() {
        return code;
    }
}

