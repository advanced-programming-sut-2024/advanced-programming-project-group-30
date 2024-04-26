package controller;

import model.Result;

public abstract class Controller {
    public abstract Result exitMenu();
    public abstract Result enterMenu(String menuName);
    public abstract Result showCurrentMenu();
}
