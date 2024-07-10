package controller.server;

import model.Result;
import model.User;
import network.Server;

public class LoginMenuControllerServer {
    public String[] login(String username) {
        User user = Server.getUserByUsername(username);
        user.setLoggedIn(true);
        return new String[]{user.getUsername(), user.getNickName(), user.getEmail()};
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
