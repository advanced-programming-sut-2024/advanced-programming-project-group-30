package model.card;

import enums.FactionType;
import enums.cardsData.*;

import java.lang.reflect.Method;


public abstract class DecksCard {
    private final String name;
    private final FactionType faction;
    private final String cardDataName;
    protected final Method ability;

    public DecksCard(String name, FactionType faction, String cardDataName, Method ability) {
        this.name = name;
        this.faction = faction;
        this.cardDataName = cardDataName;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public FactionType getFaction() {
        return faction;
    }

    public CardData getCardData() {
        if (this instanceof SpecialCard) return SpecialCardsData.valueOf(cardDataName);
        if (this instanceof WeatherCard) return WeatherCardsData.valueOf(cardDataName);
        if (faction == null) return NeutralRegularCardsData.valueOf(cardDataName);
        if (faction.equals(FactionType.MONSTERS)) return MonstersRegularCardsData.valueOf(cardDataName);
        if (faction.equals(FactionType.SKELLIGE)) return SkelligeRegularCardsData.valueOf(cardDataName);
        if (faction.equals(FactionType.SCOIA_TAEL)) return ScoiaTaelRegularCardsData.valueOf(cardDataName);
        if (faction.equals(FactionType.NILFGAARDIAN_EMPIRE))
            return NilfgaardianEmpireRegularCardsData.valueOf(cardDataName);
        if (faction.equals(FactionType.NORTHERN_REALMS)) return NorthernRealmsRegularCardsData.valueOf(cardDataName);
        return null;
    }
}
