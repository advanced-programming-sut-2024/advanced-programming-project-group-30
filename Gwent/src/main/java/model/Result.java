package model;

import java.io.Serializable;

public class Result implements Serializable {
    private final boolean isSuccessful;
    private final String message;

    public Result(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public boolean isNotSuccessful() {
        return !isSuccessful;
    }
    @Override
    public String toString() {
        return message;
    }

}
