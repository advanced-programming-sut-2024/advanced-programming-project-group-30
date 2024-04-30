package controller;

import model.Result;

public class MainMenuController extends Controller{
    public Result logout(){
        return new Result("", true, false);
    }
}
