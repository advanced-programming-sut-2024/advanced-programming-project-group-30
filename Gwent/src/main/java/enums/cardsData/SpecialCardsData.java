package enums.cardsData;

import enums.Ability;
import javafx.scene.image.Image;
import model.ability.SpecialCardAbility;
import model.card.SpecialCard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

    public enum SpecialCardsData implements CardData {
        COMMANDER_HORN("CommanderHorn", Ability.HORN_COMMANDER, 3, false),
        DECOY("Decoy", Ability.DECOY,  3, false),
        MARDROEME("Mardroeme", Ability.MARDROEME,  3,true),
        SCORCH("Scorch", Ability.SCORCH,  3,false),
        BITING_FROST("BitingFrost", Ability.BITING_FROST,  3,false),
        CLEAR_WEATHER("ClearWeather", Ability.CLEAR_WEATHER,  2,false),
        IMPENETRABLE_FOG("ImpenetrableFog", Ability.IMPENETRABLE_FOG,  3,false),
        SKELLIGE_STORM("SkelligeStorm", Ability.SKELLIGE_STORM,  3,false),
        TORRENTIAL_RAIN("TorrentialRain", Ability.TORRENTIAL_RAIN,  3,false),
        ;

        private final String name;
        private final Ability ability;
        private final int numberOfCard;
        private final boolean isDiscardAfterPlaying;



        SpecialCardsData(String name, Ability ability,int cardsNumber, boolean isDiscardAfterPlaying) {
            this.name = name;
            this.ability = ability;
            this.numberOfCard = cardsNumber;
            this.isDiscardAfterPlaying = isDiscardAfterPlaying;
        }

        public static ArrayList<SpecialCard> getAllSpecialCard() {
            ArrayList<SpecialCard> specialCards = new ArrayList<>();
            for (enums.cardsData.SpecialCardsData data : enums.cardsData.SpecialCardsData.values()) {
                System.out.println(data);
                for (int i = 0; i < data.numberOfCard; i++)
                    specialCards.add(data.createCard());
            }
            return specialCards;

        }

        private SpecialCard createCard() {
            return new SpecialCard(name,  null,this ,this.isDiscardAfterPlaying,ability.getAbility());
        }

        @Override
        public Image getLgImage() {
            String address = "/Images/Game/LgCardsImages/special_" + this.toString().toLowerCase() + ".jpg";
            return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));

        }
        @Override
        public Image getSmImage() {
            String address = "/Images/Game/SmCardsImages/special_" + this.toString().toLowerCase() + ".jpg";
            Image image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));
            return image;
        }


    }









