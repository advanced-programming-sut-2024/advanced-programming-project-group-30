package controller;

import model.Result;

public abstract class Controller {
    public String showCurrentMenu() {
        return null;
    }

    public abstract Result menuEnter();

    public abstract String menuExit();
}
