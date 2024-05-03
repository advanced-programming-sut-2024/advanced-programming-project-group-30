package model.card;
import model.ability.Ability;
import model.Faction;

public class SpecialCard extends DecksCard {
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, false, true, position);
        this.discardAfterPlaying = discardAfterPlaying;
    }

}