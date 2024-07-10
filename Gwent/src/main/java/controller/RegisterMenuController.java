package controller;

import enums.SecurityQuestion;
import model.App;
import model.Result;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RegisterMenuController {
    public void register(String username, String password, String nickname, String email, String securityQuestion, String securityAnswer) {
        User user = new User(username, password, email, nickname, SecurityQuestion.getSecurityQuestion(securityQuestion), securityAnswer);
        App.addUser(user);
    }

    public Result checkSecurityQuestion(String question, String answer) {
        if (answer.isEmpty()) return new Result(false, "please fill answer field");
        SecurityQuestion securityQuestion = SecurityQuestion.getSecurityQuestion(question);
        if (securityQuestion == null) return new Result(false, "please choose a security question");
        return new Result(true, "");
    }

    public String createRandomPassword() {
        String PASSWORD_UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String PASSWORD_LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        String PASSWORD_NUMBERS = "0123456789";
        String PASSWORD_SPECIAL_CHARACTERS = "!@#$%^&*";
        String PASSWORD_ALL_CHARACTERS = PASSWORD_UPPERCASE_LETTERS + PASSWORD_LOWERCASE_LETTERS + PASSWORD_NUMBERS + PASSWORD_SPECIAL_CHARACTERS;
        Random random = new Random();
        ArrayList<Character> password = new ArrayList<>();
        password.add(PASSWORD_LOWERCASE_LETTERS.charAt(random.nextInt(PASSWORD_LOWERCASE_LETTERS.length())));
        password.add(PASSWORD_UPPERCASE_LETTERS.charAt(random.nextInt(PASSWORD_UPPERCASE_LETTERS.length())));
        password.add(PASSWORD_NUMBERS.charAt(random.nextInt(PASSWORD_NUMBERS.length())));
        password.add(PASSWORD_SPECIAL_CHARACTERS.charAt(random.nextInt(PASSWORD_SPECIAL_CHARACTERS.length())));
        int length = random.nextInt(8, 12);
        for (int i = 4; i < length; i++)
            password.add(PASSWORD_ALL_CHARACTERS.charAt(random.nextInt(PASSWORD_ALL_CHARACTERS.length())));
        Collections.shuffle(password);
        StringBuilder passwordString = new StringBuilder();
        for (char c : password)
            passwordString.append(c);
        return passwordString.toString();
    }
}