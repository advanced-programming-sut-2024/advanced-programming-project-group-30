package model.card.speacialCards;

import model.Faction;
import model.Row;
import model.card.SpecialCard;

public class Mordroeme extends SpecialCard {
    private Row row;
    public Mordroeme(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, position, discardAfterPlaying);
    }
    public void setRow(Row row) {
        this.row = row;
    }
    public Row getRow() {
        return this.row;
    }

    public void run() {

    }
}
