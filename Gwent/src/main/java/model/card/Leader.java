package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import model.Faction;
import model.ability.LeaderAbility;

public class Leader extends Card {
    private boolean isUsedAbility = false;
    private final LeaderAbility leaderAbility;

    public Leader(String name, FactionType faction, CardData cardData, LeaderAbility leaderAbility) {
        super(name, faction, cardData);
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
