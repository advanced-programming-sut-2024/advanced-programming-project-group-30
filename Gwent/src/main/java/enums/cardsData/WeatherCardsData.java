package enums.cardsData;

import enums.Ability;
import javafx.scene.image.Image;
import model.card.WeatherCard;

import java.util.ArrayList;
import java.util.Objects;

public enum WeatherCardsData implements DeckCardData{
    BITING_FROST("BitingFrost", Ability.BITING_FROST,  3,false),
    CLEAR_WEATHER("ClearWeather", Ability.CLEAR_WEATHER,  2,true),
    IMPENETRABLE_FOG("ImpenetrableFog", Ability.IMPENETRABLE_FOG,  3,false),
    SKELLIGE_STORM("SkelligeStorm", Ability.SKELLIGE_STORM,  3,false),
    TORRENTIAL_RAIN("TorrentialRain", Ability.TORRENTIAL_RAIN,  3,false);

    private final String name;
    private final Ability ability;
    private final int numberOfCard;
    private final boolean isDiscardAfterPlaying;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/special_" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmCardsImages/special_" + this.toString().toLowerCase() + ".jpg";
    WeatherCardsData(String name, Ability ability,int cardsNumber, boolean isDiscardAfterPlaying) {
        this.name = name;
        this.ability = ability;
        this.numberOfCard = cardsNumber;
        this.isDiscardAfterPlaying = isDiscardAfterPlaying;
    }
    public static ArrayList<WeatherCard> getAllWeatherCards() {
        ArrayList<WeatherCard> weatherCards = new ArrayList<>();
        for (enums.cardsData.WeatherCardsData data : enums.cardsData.WeatherCardsData.values()) {
            for (int i = 0; i < data.numberOfCard; i++) {
                WeatherCard card = data.createCard();
                System.out.println("weather: " + card + " weather name " + data.name);
                if (weatherCards.contains(card)) {
                    i--;
                    continue;
                }
                weatherCards.add(data.createCard());
            }
        }
        return weatherCards;

    }
    private WeatherCard createCard() {
        return new WeatherCard(name,  null,this ,this.isDiscardAfterPlaying,ability.getAbility());
    }

    @Override
    public Image getLgImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(lgImageAddress)));

    }
    @Override
    public Image getSmImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(smImageAddress)));
    }


    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public Ability getAbility() {
        return this.ability;
    }
}
