package model;

import model.card.DecksCard;
import model.card.Leader;

import java.util.ArrayList;

public class Faction {
    private final String name;
    private final String explanation;
    private final ArrayList<DecksCard> decksCards;
    private final ArrayList<Leader> leaders;

    protected Faction(String name, String explanation, ArrayList<DecksCard> allCards, ArrayList<Leader> leaders) {
        this.name = name;
        this.explanation = explanation;
        this.decksCards = allCards;
        this.leaders = leaders;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public ArrayList<DecksCard> getDecksCards() {
        return decksCards;
    }

    public ArrayList<Leader> getLeaders() {
        return leaders;
    }
}
