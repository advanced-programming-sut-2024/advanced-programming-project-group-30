package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.Row;
import model.card.Card;
import model.card.SpecialCard;

public class Scorch extends SpecialCard {
    private Row selectedRow;

    public Scorch(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, position, true);
    }
    public void setSelectedRow(Row row){
        this.selectedRow = row;
    }
    public Row getSelectedRow(){
        return this.selectedRow;
    }
    public void run(Game game, Row row){

    }
}
