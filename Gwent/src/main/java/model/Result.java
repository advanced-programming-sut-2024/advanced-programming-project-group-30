package model;

public class Result {
    private final boolean isSuccessful;
    private final String message;

    public Result(boolean isSuccessful, String errorCode) {
        this.isSuccessful = isSuccessful;
        this.message = errorCode;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    @Override
    public String toString() {
        return message;
    }
}