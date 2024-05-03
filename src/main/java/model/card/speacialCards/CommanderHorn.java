package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.Row;
import model.card.SpecialCard;

public class CommanderHorn extends SpecialCard {
    private Row row;

    public CommanderHorn(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, position, false);
    }
    public void setRow(Row row) {
        this.row = row;
    }
    public void run(Game game){

    }
}
