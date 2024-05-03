package model;

public class Result {
    private final String message;
    private final boolean isSuccessful;
    private final Error error;

    public Result(String message, boolean isSuccessful, boolean needFurtherInput, Error error) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.error = error;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public Error getError() {
        return error;
    }

    @Override
    public String toString() {
        return message;
    }
}
