package controller;

import enums.RegisterMenuCheck;
import model.App;
import model.Result;

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

    public void register(String username, String password, String passwordConfirm, String nickname, String email) {


    }
    private static Result checkInformation(String username, String password, String passwordConfirm, String nickname, String email) {

        return null;
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

    public String createUniqueUserName(String duplicateUsername) {
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
        return passwordString.toString();
    }

    public Result pickQuestion(int questionNumber, String answer, String answerConfirmation) {
        return null;
    }

    public Result enterMenu(String menuName) {
        return null;
    }

    public Result exitMenu() {
        return null;
    }
}
