package controller;

import model.App;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreBoardController {
    public static ArrayList<User> calculateRankings() {
        ArrayList<User> users = App.getAllUsers();

        // Sort by wins in descending order
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getWins(), u1.getWins());
            }
        });

        return users;
    }
}
