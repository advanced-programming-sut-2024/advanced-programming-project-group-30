package controller;

import model.Result;

public abstract class Controller {
    public String showCurrentMenu() {
        return "";
    }

    public abstract Result menuEnter();

    public abstract String menuExit();
}
