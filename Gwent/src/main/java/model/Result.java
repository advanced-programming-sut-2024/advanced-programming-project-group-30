package model;

import enums.Error;

public class Result {
    private final boolean isSuccessful;
    private final Error errorCode;
    public Result(boolean isSuccessful, Error errorCode) {
        this.isSuccessful = isSuccessful;
        this.errorCode = errorCode;
    }



    public boolean isSuccessful() {
        return isSuccessful;
    }
    public Error getErrorCode() {
        return errorCode;
    }



}
