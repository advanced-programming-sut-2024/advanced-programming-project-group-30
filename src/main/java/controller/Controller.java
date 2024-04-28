package controller;

import model.Result;

public abstract class Controller {
    public Result showCurrentMenu(){
        return new Result("", true, false);
    }

}
