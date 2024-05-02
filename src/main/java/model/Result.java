package model;

import enums.Error;

import javax.swing.*;

public class Result {
    private final String message;
    private final boolean isSuccessful;
    private final Error error;

    public Result(String message, boolean isSuccessful, Error error) {
        this.message = message;
        this.isSuccessful = isSuccessful;
        this.error = error;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }


    @Override
    public String toString() {
        return message;
    }
}
