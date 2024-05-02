package model.card;
import model.Faction;
public abstract class Card {
    private final String name;
    private final String explanation;
    private final Faction faction;
    private final boolean isLeader;

    public Card(String name, String explanation, Faction faction, boolean itIsLeader) {
        this.name = name;
        this.explanation = explanation;
        this.faction = faction;
        this.isLeader = itIsLeader;
    }

    public String getName() {
        return name;
    }
    public String getExplanation() {
        return explanation;
    }
    public Faction getFaction() {
        return faction;
    }
    public boolean isLeader() {
        return isLeader;
    }

}