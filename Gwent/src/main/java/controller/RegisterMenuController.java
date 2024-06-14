package controller;

import enums.Menu;
import enums.SecurityQuestion;
import model.App;
import model.Result;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RegisterMenuController {
    private void register(String username, String password, String passwordConfirm, String nickname, String email) {
        //TODO: check if all inputs are ok and then register
        User user = new User(username, password, email, nickname);
        App.addUser(user);
        //TODO: set security questions for user
    }

    private String createUniqueUserName(String duplicateUsername) {
        Random random = new Random();
        StringBuilder uniqueUsernameBuilder = new StringBuilder(duplicateUsername);
        for (char c : duplicateUsername.toCharArray()) {
            uniqueUsernameBuilder.append(c);
            int randomInt = random.nextInt(3);
            switch (randomInt) {
                case 1:
                    int randomNumber = random.nextInt(10);
                    uniqueUsernameBuilder.append(randomNumber);
                    break;
                case 2:
                    uniqueUsernameBuilder.append("_");
                    break;
            }
            if (App.getUserByUsername(duplicateUsername) == null) break;
        }
        if (App.getUserByUsername(duplicateUsername) != null) return createUniqueUserName(uniqueUsernameBuilder.toString());
        return uniqueUsernameBuilder.toString();
    }

    public Result createRandomPassword() {
        String PASSWORD_UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String PASSWORD_LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        String PASSWORD_NUMBERS = "0123456789";
        String PASSWORD_SPECIAL_CHARACTERS = "!@#$%^&*";
        String PASSWORD_ALL_CHARACTERS = PASSWORD_UPPERCASE_LETTERS + PASSWORD_LOWERCASE_LETTERS + PASSWORD_NUMBERS + PASSWORD_SPECIAL_CHARACTERS;
        Random random = new Random();
        List<Character> password = new ArrayList<>();
        password.add(PASSWORD_LOWERCASE_LETTERS.charAt(random.nextInt(PASSWORD_LOWERCASE_LETTERS.length())));
        password.add(PASSWORD_UPPERCASE_LETTERS.charAt(random.nextInt(PASSWORD_UPPERCASE_LETTERS.length())));
        password.add(PASSWORD_NUMBERS.charAt(random.nextInt(PASSWORD_NUMBERS.length())));
        password.add(PASSWORD_SPECIAL_CHARACTERS.charAt(random.nextInt(PASSWORD_SPECIAL_CHARACTERS.length())));

        for (int i = 4; i < 8; i++) {
            password.add(PASSWORD_ALL_CHARACTERS.charAt(random.nextInt(PASSWORD_ALL_CHARACTERS.length())));
        }
        Collections.shuffle(password);
        StringBuilder passwordString = new StringBuilder();
        for (char c : password) {
            passwordString.append(c);
        }
        App.getLoggedInUser().setPassword(passwordString.toString());
        return new Result(true, passwordString.toString());
    }

    public Result pickQuestion(String question, String answer, String answerConfirmation) {
        User user = App.getLoggedInUser();
        if (!answer.equals(answerConfirmation)) {
            return new Result(false, "** answers do not match");
        }

        return new Result(true, "");
    }

    public void exitMenu() {
        App.setCurrentMenu(Menu.LOGIN_MENU);
    }
}
