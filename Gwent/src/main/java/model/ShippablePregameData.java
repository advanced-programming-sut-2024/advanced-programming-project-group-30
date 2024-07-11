package model;

import enums.FactionType;
import enums.cardsData.LeaderCardData;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;

import java.util.ArrayList;

public class ShippablePregameData {
    private final String username;
    private final FactionType faction;
    private final String leader;
    private final ArrayList<RegularCard> deckRegularCards;
    private final ArrayList<SpecialCard> deckSpecialCards;
    private final ArrayList<WeatherCard> deckWeatherCards;

    public ShippablePregameData(String username, FactionType faction, LeaderCardData leader, ArrayList<RegularCard> deckRegularCards, ArrayList<SpecialCard> deckSpecialCards, ArrayList<WeatherCard> deckWeatherCards) {
        this.username = username;
        this.faction = faction;
        this.leader = leader.toString();
        this.deckRegularCards = deckRegularCards;
        this.deckSpecialCards = deckSpecialCards;
        this.deckWeatherCards = deckWeatherCards;
    }

    public String getUsername() {
        return username;
    }

    public FactionType getFaction() {
        return faction;
    }

    public String getLeader() {
        return leader;
    }

    public ArrayList<DecksCard> getDeck() {
        ArrayList<DecksCard> decksCards = new ArrayList<>(deckRegularCards);
        decksCards.addAll(deckSpecialCards);
        decksCards.addAll(deckWeatherCards);
        return decksCards;
    }
}
