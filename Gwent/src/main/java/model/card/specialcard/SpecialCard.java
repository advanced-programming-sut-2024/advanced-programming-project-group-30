package model.card.specialcard;
import model.Faction;
import model.card.DecksCard;

public abstract class SpecialCard extends DecksCard {
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, false, true, position);
        this.discardAfterPlaying = discardAfterPlaying;
    }
}