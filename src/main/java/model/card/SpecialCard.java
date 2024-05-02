package model.card;
import model.Ability;
import model.Faction;
import model.card.DecksCard;

public class SpecialCard extends DecksCard {
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, String explanation, Faction faction, Ability ability, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, false, ability, true, position);
        this.discardAfterPlaying = discardAfterPlaying;
    }

    public boolean isDiscardAfterPlaying() {
        return discardAfterPlaying;
    }
}