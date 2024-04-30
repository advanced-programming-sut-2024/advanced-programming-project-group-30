package model.card;

public abstract class RegularCard extends Card {
    private final boolean isHero;
    private final boolean hasAction;
    private final int point;

    public RegularCard(String name, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, boolean hasAction, int point) {
        super(name, itIsLeader, itIsSpecialCard);
        this.isHero = isHero;
        this.hasAction = hasAction;
        this.point = point;
    }

    public boolean isHero() {
        return isHero;
    }

    public boolean isHasAction() {
        return hasAction;
    }

    public int getPoint() {
        return point;
    }
}
