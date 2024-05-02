package model.card;

<<<<<<< HEAD:src/main/java/model/card/Card.java
public abstract class Card {
    private final String name;
    private final boolean isLeader;
    private final boolean isSpecialCard;
    // Enum

    public Card(String name, boolean itIsLeader, boolean itIsSpecialCard) {
        this.name = name;
        this.isLeader = itIsLeader;
        this.isSpecialCard = itIsSpecialCard;
=======
import model.Ability;
import model.Faction;

public abstract class Card {
    private final String name;
    private final String explanation;
    private final Faction faction;
    private final boolean isLeader;
    private final Ability ability;

    public Card(String name, String explanation, Faction faction, boolean itIsLeader, Ability ability) {
        this.name = name;
        this.explanation = explanation;
        this.faction = faction;
        this.isLeader = itIsLeader;
        this.ability = ability;
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/Card.java
    }

    public String getName() {
        return name;
    }

<<<<<<< HEAD:src/main/java/model/card/Card.java
    public boolean isItIsLeader() {
=======
    public String getExplanation() {
        return explanation;
    }

    public Faction getFaction() {
        return faction;
    }

    public boolean isLeader() {
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/Card.java
        return isLeader;
    }

<<<<<<< HEAD:src/main/java/model/card/Card.java
    public boolean isSpecialCard() {
        return isSpecialCard;
    }
<<<<<<< HEAD:src/main/java/model/card/Card.java
}
=======

=======
>>>>>>> 42d11a37c7db755408f2d623cd1747aff88e2b4b:Gwent/src/main/java/model/card/Card.java
    public Ability getAbility() {
        return ability;
    }
}
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/Card.java
