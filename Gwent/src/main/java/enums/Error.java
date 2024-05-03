package enums;

public enum Error {
    INVALID_USERNAME("", "001");

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