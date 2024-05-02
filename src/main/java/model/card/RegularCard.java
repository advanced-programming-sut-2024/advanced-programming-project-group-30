package model.card;

<<<<<<< HEAD:src/main/java/model/card/RegularCard.java
public abstract class RegularCard extends Card{
=======
import model.Ability;
import model.Faction;

<<<<<<< HEAD:src/main/java/model/card/RegularCard.java
public class RegularCard extends Card {
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/RegularCard.java
=======
public class RegularCard extends DecksCard {
>>>>>>> 42d11a37c7db755408f2d623cd1747aff88e2b4b:Gwent/src/main/java/model/card/RegularCard.java
    private final boolean isHero;
    private final int point;

<<<<<<< HEAD:src/main/java/model/card/RegularCard.java
<<<<<<< HEAD:src/main/java/model/card/RegularCard.java
    public RegularCard(String name, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, boolean hasAction, int point) {
        super(name, itIsLeader, itIsSpecialCard);
=======
    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, boolean hasAction, int point, Ability ability) {
        super(name, explanation, faction, itIsLeader, itIsSpecialCard, ability);
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/RegularCard.java
=======
    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, int point, Ability ability, String position) {
        super(name, explanation, faction, itIsLeader, ability, false, position);
>>>>>>> 42d11a37c7db755408f2d623cd1747aff88e2b4b:Gwent/src/main/java/model/card/RegularCard.java
        this.isHero = isHero;
        this.point = point;
    }

    public boolean isHero() {
        return isHero;
    }

    public int getPoint() {
        return point;
    }
}
