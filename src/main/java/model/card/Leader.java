package model.card;

public abstract class Leader extends Card {
    private boolean isUsedAbility = false;

    public Leader(String name) {
        super(name, true, false);
    }

    public abstract void UseAbility();
}
