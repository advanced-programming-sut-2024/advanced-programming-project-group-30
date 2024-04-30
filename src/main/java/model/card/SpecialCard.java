package model.card;

<<<<<<< HEAD:src/main/java/model/card/SpecialCard.java
public abstract class SpecialCard extends Card {
    public SpecialCard(String name) {
        super(name, false, true);
    }

    public abstract void doAction();
=======
import model.Ability;
import model.Faction;

public class SpecialCard extends Card {
    public SpecialCard(String name, String explanation, Faction faction, Ability ability) {
        super(name, explanation, faction, false, true, ability);
    }
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/SpecialCard.java
}
