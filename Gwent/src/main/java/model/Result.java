package model;

public class Result {
    private final String message;
    private final boolean isSuccessful;
    private final boolean needFurtherInput;

    public Result(String message, boolean isSuccessful, boolean needFurtherInput) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.needFurtherInput = needFurtherInput;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public boolean isNeedFurtherInput() {
        return needFurtherInput;
    }

    @Override
    public String toString() {
        return message;
    }
}
