package model.card;

import enums.FactionType;

import java.lang.reflect.Method;

public class SpecialCard extends DecksCard {
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, FactionType faction, boolean discardAfterPlaying, String cardDataName, Method method) {
        super(name, faction, cardDataName, method);
        this.discardAfterPlaying = discardAfterPlaying;
    }

    public void run() {

    }

    public boolean isDiscardAfterPlaying() {
        return discardAfterPlaying;
    }
}