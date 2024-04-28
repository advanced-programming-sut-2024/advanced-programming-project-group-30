package model.card;

public abstract class RegularCard extends Card{
    private final boolean isHero;
    private final int point;

    public RegularCard(String name, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, int point) {
        super(name, itIsLeader, itIsSpecialCard);
        this.isHero = isHero;
        this.point = point;
    }
}
