package controller;

import model.App;
import model.Faction;
import model.Result;
import model.User;

public class LoginMenuController {
    public void login(String username, String rememberMe) {
        User user = App.getUserByUsername(username);
        App.setLoggedInUser(user);
        App.getSceneManager().goToMainMenu();
    }

    public Result checkInformationForLogin(String username, String password) {
        if (username.isEmpty()) return new Result(false, "please fill username field.");
        if (password.isEmpty()) return new Result(false, "please fill password field.");
        User loggedInUser = App.getUserByUsername(username);
        if (loggedInUser == null) return new Result(false, "this username does not exist.");
        if (!password.equals(loggedInUser.getPassword())) return new Result(false, "wrong password.");
        return new Result(true, "");
    }

    public Result getEmptyError(String string, String name) {
        if (string.isEmpty()) return new Result(false, "** " + name + " cannot be empty.");
        return new Result(true, "");
    }
}