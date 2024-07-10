package controller;

import model.App;
import model.Result;
import model.User;
import network.Server;

public class LoginMenuController {
    public void login(String username, boolean stayLoggedIn) {
        User user = Server.getUserByUsername(username);
        App.setLoggedInUser(user, stayLoggedIn);
        App.getSceneManager().goToMainMenu();
    }

    public Result checkInformationForLogin(String username, String password) {
        if (username.isEmpty()) return new Result(false, "please fill username field.");
        if (password.isEmpty()) return new Result(false, "please fill password field.");
        User loggedInUser = Server.getUserByUsername(username);
        if (loggedInUser == null) return new Result(false, "this username does not exist.");
        if (!password.equals(loggedInUser.getPassword())) return new Result(false, "wrong password.");
        return new Result(true, "");
    }

    public Result getEmptyError(String string, String name) {
        if (string.isEmpty()) return new Result(false, "** " + name + " cannot be empty.");
        return new Result(true, "");
    }
}