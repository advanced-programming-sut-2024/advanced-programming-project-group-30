package controller;

import enums.Error;
import enums.Menu;
import enums.RegisterMenuCheck;
import enums.SecurityQuestion;
import model.App;
import model.Result;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RegisterMenuController {
    private static Random random = new Random();
    private static String PASSWORD_UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String PASSWORD_LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static String PASSWORD_NUMBERS = "0123456789";
    private static String PASSWORD_SPECIAL_CHARACTERS = "!@#$%^&*";
    private static String PASSWORD_ALL_CHARACTERS = PASSWORD_UPPERCASE_LETTERS + PASSWORD_LOWERCASE_LETTERS + PASSWORD_NUMBERS + PASSWORD_SPECIAL_CHARACTERS;

    private static void register(String username, String password, String passwordConfirm, String nickname, String email) {
        //TODO: check if all inputs are ok and then register
        User user = new User(username, password, email, nickname);
        App.addUser(user);
        //TODO: set security questions for user
    }
    public static Result checkInformation(String username, String password, String passwordConfirm, String nickname, String email) {
        if (!isUsernameUnique(username)) {
            return new Result(false, Error.REPEATED_USERNAME);
        }
        else if (!isUsernameValid(username)) {
            return new Result(false, Error.INVALID_USERNAME);
        }
        else if (!checkPasswordValidation(password)) {
            return new Result(false, Error.INVAlID_PASSWORD);
        }
        else if (!checkPasswordWeakness(password)) {
            return new Result(false, Error.WEAK_PASSWORD);
        }
        else if (!password.equals(passwordConfirm)) {
            return new Result(false, Error.WRONG_PASSWORD_CONFIRMATION);
        }
        else if (!checkEmail(email)){
            return new Result(false, Error.INVALID_EMAIL);
        }

        register(username, password, passwordConfirm, nickname, email);
        return new Result(true, Error.REGISTER_SUCCESSFUL);
    }
    private static boolean isUsernameUnique(String username) {
        return App.getUserByUsername(username) != null;
    }
    private static boolean isUsernameValid(String username) {
        return RegisterMenuCheck.VALID_USERNAME.getMatcher(username).matches();
    }
    private static boolean checkPasswordValidation(String password) {
        return RegisterMenuCheck.VALID_PASSWORD.getMatcher(password).matches();
    }
    private static boolean checkPasswordWeakness(String password) {
        return RegisterMenuCheck.STRONG_PASSWORD.getMatcher(password).matches();
    }

    private static boolean checkEmail(String email) {
        return RegisterMenuCheck.VALID_EMAIL.getMatcher(email).matches();
    }

    private static String createUniqueUserName(String duplicateUsername) {
        StringBuilder duplicateUsernameBuilder = new StringBuilder(duplicateUsername);
        for (char c : duplicateUsername.toCharArray()){
            duplicateUsernameBuilder.append(c);
            int randomInt = random.nextInt(3);
            switch (randomInt){
                case 0:
                    break;
                case 1:
                    int randomNumber = random.nextInt(10);
                    duplicateUsernameBuilder.append(randomNumber);
                    break;
                case 2:
                    duplicateUsernameBuilder.append("_");
                    break;
            }
        }
        return duplicateUsernameBuilder.toString();
    }

    public String createRandomPassword() {
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
        return passwordString.toString();
    }

    public Result pickQuestion(int questionNumber, String answer, String answerConfirmation) {
        User user = App.getLoggedInUser();
        if (!answer.equals(answerConfirmation)) {
            return new Result(false, Error.WRONG_ANSWER_CONFIRMATION);
        }
        SecurityQuestion securityQuestion = SecurityQuestion.values()[questionNumber];
        user.addToSecurityQuestions(securityQuestion, answer);
        return new Result(true, Error.SECURITY_QUESTION_DONE);
    }

    public void exitMenu() {
        App.setCurrentMenu(Menu.LOGIN_MENU);
    }
}
