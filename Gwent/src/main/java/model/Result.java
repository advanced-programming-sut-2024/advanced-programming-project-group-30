package model;

import enums.Error;

public class Result {
    private final boolean isSuccessful;
    private final String errorCode;
    public Result(boolean isSuccessful, String errorCode) {
        this.isSuccessful = isSuccessful;
        this.errorCode = errorCode;
    }



    public boolean isSuccessful() {
        return isSuccessful;
    }
    public String getErrorCode() {
        return errorCode;
    }



}
