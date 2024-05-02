package enums;

public enum Error {
    INVALID_USERNAME("", "001");


    public final String message;
    public final String code;


    Error(String message, String code) {
        this.message = message;
        this.code = code;
    }
    public String getCode(){
        return null;
    }
}
