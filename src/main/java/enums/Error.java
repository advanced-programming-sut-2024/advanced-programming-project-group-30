package enums;

public enum Error {
    INVALID_USERNAME("", "001");

    public final String message;
    public final String code;


    Error(String message, String code) {
        this.message = message;
        this.code = code;
    }
    public static Error getErrorByCode(String name){
        return Error.valueOf(name);
    }
    @Override
    public String toString() {
        return message.toString();
    }
    public String getCode(){
        return null;
    }
}
