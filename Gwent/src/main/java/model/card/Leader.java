package model.card;

import enums.FactionType;
import model.ability.LeaderAbility;

public class Leader extends Card {
    private boolean isUsedAbility = false;
    private final LeaderAbility leaderAbility;

    public Leader(String name, FactionType faction, LeaderAbility leaderAbility) {
        super(name, faction);
        this.leaderAbility = leaderAbility;
    }

    public boolean isUsedAbility() {
        return isUsedAbility;
    }

    public void setUsedAbility(boolean usedAbility) {
        isUsedAbility = usedAbility;
    }

    public LeaderAbility getLeaderAbility() {
        return this.leaderAbility;
    }
}
