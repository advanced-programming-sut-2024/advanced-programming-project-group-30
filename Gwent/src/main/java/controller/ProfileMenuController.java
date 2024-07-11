package controller;

import model.App;
import model.GameHistory;
import model.Result;
import model.User;

import java.time.LocalDateTime;


public class ProfileMenuController {
    private final UserInformationController userInformationController = new UserInformationController();

    public Result changeUsername(String newUsername) {
        Result result = userInformationController.checkUsername(newUsername);
        if (!result.isNotSuccessful()) {
            App.getLoggedInUser().setUsername(newUsername);
            App.saveUsers();
        }
        return result;
    }

    public Result changeNickname(String newNickname) {
        Result result = userInformationController.checkNickname(newNickname);
        if (!result.isNotSuccessful()) {
            App.getLoggedInUser().setNickName(newNickname);
            App.saveUsers();
        }
        return result;
    }

    public Result changePassword(String newPassword, String oldPassword) {
        Result result = userInformationController.checkPasswordForChange(newPassword, oldPassword);
        if (!result.isNotSuccessful()) {
            App.getLoggedInUser().setPassword(newPassword);
            App.saveUsers();
        }
        return result;
    }

    public Result changeEmail(String newEmail) {
        Result result = userInformationController.checkEmail(newEmail);
        if (!result.isNotSuccessful()){
            App.getLoggedInUser().setEmail(newEmail);
            App.saveUsers();
        }
        return result;
    }
    public Result checkGameHistory(String gameHistoryCount){
        User user = App.getLoggedInUser();
        if (!gameHistoryCount.matches("\\d+")) return new Result(false, "** please enter a number.");
        else if (user != null && Integer.parseInt(gameHistoryCount) == 0)
            return new Result(false, "** please enter a number greater than 0.");
        else if (user != null && user.getGameHistories().size() < Integer.parseInt(gameHistoryCount))
            return new Result(false, "** you don't have that many games.");
        else return new Result(true, "");
    }

    public Result showDefaultGameHistory() {
        User user = App.getLoggedInUser();
        int count = 0;
        if (user != null){
            count = user.getGameHistories().size();
            if (count == 0) return new Result(false, "** There are no games to show.");
            if (count > 5) count = 5;
        }
        return new Result(true, showGameHistory(count));
    }
    public Result showGameHistoryByUserRequest(String gameHistoryCount){
        if (!gameHistoryCount.matches("-?\\d+")) return new Result(false, "** please enter a number.");
        else if (Integer.parseInt(gameHistoryCount) <= 0)
            return new Result(false, "** please enter a number greater than 0.");
        else if (App.getLoggedInUser().getGameHistories().size() < Integer.parseInt(gameHistoryCount))
            return new Result(false, "** you don't have that many games.");
        return new Result(true, showGameHistory(Integer.parseInt(gameHistoryCount)));
    }
    private String showGameHistory(int count){
        StringBuilder gameHistoryBuilder = new StringBuilder();
        User user = App.getLoggedInUser();
        GameHistory gameHistory;
        for (int i = 0; i < count; i++) {
            gameHistory = user.getGameHistories().get(i);
            gameHistoryBuilder.append(i + 1).append(". ");
            gameHistoryBuilder.append("Opponent: ").append(gameHistory.getOpponentName()).append("\n");
            gameHistoryBuilder.append("Winner: ").append(gameHistory.getWinnerName());
            LocalDateTime convertedDateTime = gameHistory.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
            gameHistoryBuilder.append("Date: ").append(convertedDateTime).append("\n");
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

    public void goToMainMenu() {
        App.getSceneManager().goToMainMenu();
    }
}
