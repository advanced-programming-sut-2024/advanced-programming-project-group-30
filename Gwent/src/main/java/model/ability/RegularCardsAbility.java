package model.ability;

import model.Game;

public abstract class RegularCardsAbility {
    public RegularCardsAbility createNewAbilityByName(String name) {
        return null;
    }

    public abstract void run(Game currentGame);
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
