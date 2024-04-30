package model;

import model.card.Card;
import model.card.Leader;

import java.util.ArrayList;

public class Faction {
    private final String name;
    private final String explanation;
    private final ArrayList<Card> allCards;
    private final ArrayList<Leader> leaders;

    public static Faction createFaction(String name) {
        return new Faction("", "", new ArrayList<Card>(), new ArrayList<Leader>());
    }

    protected Faction(String name, String explanation, ArrayList<Card> allCards, ArrayList<Leader> leaders) {
        this.name = name;
        this.explanation = explanation;
        this.allCards = allCards;
        this.leaders = leaders;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public ArrayList<Leader> getLeaders() {
        return leaders;
    }
}
