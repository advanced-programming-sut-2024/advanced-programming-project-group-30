package model.card;

<<<<<<< HEAD:src/main/java/model/card/RegularCard.java
public abstract class RegularCard extends Card{
=======
import model.Ability;
import model.Faction;

public class RegularCard extends Card {
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/RegularCard.java
    private final boolean isHero;
    private final boolean hasAction;
    private final int point;

<<<<<<< HEAD:src/main/java/model/card/RegularCard.java
    public RegularCard(String name, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, boolean hasAction, int point) {
        super(name, itIsLeader, itIsSpecialCard);
=======
    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, boolean hasAction, int point, Ability ability) {
        super(name, explanation, faction, itIsLeader, itIsSpecialCard, ability);
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/RegularCard.java
        this.isHero = isHero;
        this.hasAction = hasAction;
        this.point = point;
    }

    public boolean isHero() {
        return isHero;
    }

    public boolean isHasAction() {
        return hasAction;
    }

    public int getPoint() {
        return point;
    }
}
