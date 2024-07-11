package controller;

import model.GameHistory;
import model.Result;
import model.User;
import network.Server;


public class ProfileMenuController {
    private final UserInformationController userInformationController = new UserInformationController();

    public Result changeUsername(String oldUsername, String newUsername) {
        User user = Server.getUserByUsername(oldUsername);
        if (user == null) return new Result(false, "Wtf! who are you?");
        Result result = userInformationController.checkUsername(newUsername);
        if (!result.isNotSuccessful()) user.setUsername(newUsername);
        return result;
    }

    public Result changeNickname(String username, String newNickname) {
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "Wtf! who are you?");
        Result result = userInformationController.checkNickname(newNickname);
        if (!result.isNotSuccessful()) user.setNickName(newNickname);
        return result;
    }

    public Result changeEmail(String username, String newEmail) {
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "Wtf! who are you?");
        Result result = userInformationController.checkEmail(newEmail);
        if (!result.isNotSuccessful()) user.setEmail(newEmail);
        return result;
    }

    public Result changePassword(String username, String newPassword, String oldPassword) {
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "Wtf! who are you?");
        Result result = userInformationController.checkPasswordForChange(username, newPassword, oldPassword);
        if (!result.isNotSuccessful()) user.setPassword(newPassword);
        return result;
    }

    public Result checkGameHistory(String username, String gameHistoryCount) {
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "Wtf! who are you?");
        if (!gameHistoryCount.matches("\\d+")) return new Result(false, "** please enter a number.");
        else if (Integer.parseInt(gameHistoryCount) == 0)
            return new Result(false, "** please enter a number greater than 0.");
        else if (user.getGameHistories().size() < Integer.parseInt(gameHistoryCount))
            return new Result(false, "** you don't have that many games.");
        else return new Result(true, "");
    }

    public Result getDefaultGameHistory(String username) {
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "Wtf! who are you?");
        int count;
        count = user.getGameHistories().size();
        if (count == 0) return new Result(false, "** There are no games to show.");
        if (count > 5) count = 5;
        return new Result(true, getGameHistory(user, count));
    }

    public Result getGameHistoryByUserRequest(String username, String gameHistoryCount) {
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "Wtf! who are you?");
        if (!gameHistoryCount.matches("-?\\d+")) return new Result(false, "** please enter a number.");
        else if (Integer.parseInt(gameHistoryCount) <= 0)
            return new Result(false, "** please enter a number greater than 0.");
        else if (user.getGameHistories().size() < Integer.parseInt(gameHistoryCount))
            return new Result(false, "** you don't have that many games.");
        return new Result(true, getGameHistory(user,Integer.parseInt(gameHistoryCount)));
    }

    private String getGameHistory(User user, int count) {
        StringBuilder gameHistoryBuilder = new StringBuilder();
        GameHistory gameHistory;
        for (int i = 0; i < count; i++) {
            gameHistory = user.getGameHistories().get(i);
            gameHistoryBuilder.append(i + 1).append(". ");
            gameHistoryBuilder.append("Opponent: ").append(gameHistory.getOpponent().getUsername()).append("\n");
            gameHistoryBuilder.append("Winner: ").append(gameHistory.getWinner().getUsername());
            gameHistoryBuilder.append("Date: ").append(gameHistory.getDate()).append("\n");
            gameHistoryBuilder.append("Result of the game\n").append("-------------------\n");
            for (int j = 0; j < 3; j++) {
                gameHistoryBuilder.append("         round ").append(j + 1).append("       ");
            }
            gameHistoryBuilder.append("\n");
            gameHistoryBuilder.append("You: ");
            for (int j = 0; j < 3; j++) {
                gameHistoryBuilder.append(gameHistory.getRoundsScore()[0][j]).append("               ");
            }
            gameHistoryBuilder.append("\n");
            gameHistoryBuilder.append("Opponent: ");
            for (int j = 0; j < 3; j++) {
                gameHistoryBuilder.append(gameHistory.getRoundsScore()[1][j]).append("               ");
            }
            gameHistoryBuilder.append("\n\n");
        }
        return gameHistoryBuilder.toString();
    }
}