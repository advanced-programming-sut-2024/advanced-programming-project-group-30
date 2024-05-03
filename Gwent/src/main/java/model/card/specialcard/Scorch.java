package model.card.specialcard;

import model.Faction;
import model.Game;
import model.Row;


public class Scorch extends SpecialCard {
    private Row selectedRow;

    public Scorch(String name, String explanation, Faction faction, boolean discardAfterPlaying) {
        super(name, explanation, faction, true);
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
