package model.card.specialcard;

import model.Faction;
import model.Row;

public class Mordroeme extends SpecialCard {
    private Row row;
    public Mordroeme(String name, String explanation, Faction faction, boolean discardAfterPlaying) {
        super(name, explanation, faction, discardAfterPlaying);
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
