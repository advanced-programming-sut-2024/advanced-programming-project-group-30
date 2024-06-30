package model.ability;

import model.Game;
import model.Player;
import model.Row;
import model.card.RegularCard;


public abstract class RegularCardsAbility {
    public RegularCardsAbility createNewAbilityByName(String name) {
        return null;
    }

    public abstract void run(Game currentGame);
    public void Medic(Game currentGame, RegularCard card){

    }
}

class Decoy extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class HornCommander extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class Medic extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class MoralBoost extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {
        Player currentPlayer = currentGame.getCurrentPlayer();


    }
}

class Muster extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class Scorch extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class TightBond extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class Spy extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class Berserker extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class Mardroeme extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}

class Transformers extends RegularCardsAbility {
    @Override
    public void run(Game currentGame) {

    }
}
