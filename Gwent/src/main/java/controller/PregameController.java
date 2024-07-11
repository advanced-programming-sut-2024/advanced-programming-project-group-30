package controller;

import model.CardCollection;
import model.User;
import network.Server;

public class PregameController {
    public CardCollection getUserCardCollection(String username) {
        User user = Server.getUserByUsername(username);
        if (user == null) return null;
        return user.getCardCollection();
    }
}