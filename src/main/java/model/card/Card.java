package model.card;

public abstract class Card {
    private final String name;
    private final boolean isLeader;
    private final boolean isSpecialCard;
    // Enum

    public Card(String name, boolean itIsLeader, boolean itIsSpecialCard) {
        this.name = name;
        this.isLeader = itIsLeader;
        this.isSpecialCard = itIsSpecialCard;
    }

    public String getName() {
        return name;
    }

    public boolean isItIsLeader() {
        return isLeader;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }
}
