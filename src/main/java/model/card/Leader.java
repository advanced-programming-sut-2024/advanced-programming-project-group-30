package model.card;

<<<<<<< HEAD:src/main/java/model/card/Leader.java
public abstract class Leader extends Card {
    private boolean isUsedAbility = false;

    public Leader(String name) {
        super(name, true, false);
    }

    public abstract void UseAbility();
}
=======
import model.Ability;
import model.Faction;

public class Leader extends Card {
    public Leader(String name, String explanation,Faction faction, Ability ability) {
        super(name, explanation,faction, true, false, ability);
    }
}
>>>>>>> 756ab1fea1d618c1862d4e156aa8eba25261fee7:Gwent/src/main/java/model/card/Leader.java
