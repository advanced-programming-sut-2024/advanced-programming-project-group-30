package model.card;
import model.Ability;
import model.Faction;
import model.card.Card;

public class Leader extends Card {
    private boolean isUsedAbility = false;

    public Leader(String name, String explanation, Faction faction, Ability ability) {
        super(name, explanation, faction, true, ability);
    }

    public boolean isUsedAbility() {
        return isUsedAbility;
    }

    public void setUsedAbility(boolean usedAbility) {
        isUsedAbility = usedAbility;
    }
}