package controller.server;

import model.PregameData;
import model.Result;
import model.User;
import network.Server;

public class MainMenuControllerServer {
    public Result logout(String username) {
        User user = Server.getUserByUsername(username);
        if (user == null) {
            System.err.println("ye ahmaghi login nakarde omade bood to.");
            return new Result(false, "chejoori omadi inja vaghan.");
        }
        user.setLoggedIn(false);
        return new Result(true, username + " logout");
    }

    public String[] getProfileData(String userName) {
        User user = Server.getUserByUsername(userName);
        if (user == null) return null;
        return new String[]{user.getUsername(), user.getNickName(), user.getEmail(), String.valueOf(user.getRank()), String.valueOf(user.getHighestScore()),
                String.valueOf(user.getGameHistories().size()), String.valueOf(user.getWins()), String.valueOf(user.getLosses()), String.valueOf(user.getDraws())};
    }

    public PregameData createPregame(String username) {
        // TODO
        return null;
    }
}