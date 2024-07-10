package controller;

import model.App;
import model.PregameData;
import model.Result;
import model.User;
import network.Server;

public class ChooseOpponentController {
    public Result checkOpponentInformation(String username, String password) {
        if (username.isEmpty()) return new Result(false, "please fill username field");
        if (password.isEmpty()) return new Result(false, "please fill password field");
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "user not found");
        if (user == App.getLoggedInUser()) return new Result(false, "you can't play with yourself");
        if (!user.getPassword().equals(password)) return new Result(false, "wrong password");
        return new Result(true, "");
    }

    public PregameData createPregameData(String opponentUsername) {
        return new PregameData(App.getLoggedInUser(), Server.getUserByUsername(opponentUsername));
    }
}