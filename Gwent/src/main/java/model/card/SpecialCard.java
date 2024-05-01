package model.card;

public abstract class SpecialCard extends Card {
    public SpecialCard(String name) {
        super(name, false, true);
    }

    public abstract void doAction();
}
