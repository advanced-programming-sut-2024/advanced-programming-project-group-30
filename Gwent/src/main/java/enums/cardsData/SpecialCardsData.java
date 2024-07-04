package enums.cardsData;

import javafx.scene.image.Image;
import model.ability.SpecialCardAbility;
import model.card.SpecialCard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

    public enum SpecialCardsData implements CardData {
        COMMANDER_HORN("CommanderHorn", "commanderHorn", "Doubles the strength of all unit cards in that row. Limited to 1 per row.", 3, false),
        DECOY("Decoy", "decoy", "Swap with a card on the battlefield to return it to your hand.", 3, false),
        MARDROEME("Mardroeme", "mardroeme", "Triggers transformation of all Berserker cards on the same row.", 3,true),
        SCORCH("Scorch", "scorch", "Triggers transformation of all Berserker cards on the same row.", 3,false),
        BITING_FROST("BitingFrost", "bitingFrost", "Sets the strength of all Close Combat cards to 1 for both players.", 3,false),
        CLEAR_WEATHER("ClearWeather", "clearWeather", "Removes all Weather Cards (Biting Frost, Impenetrable Fog and Torrential Rain) effects.", 2,false),
        IMPENETRABLE_FOG("ImpenetrableFog", "impenetrableFog", "Sets the strength of all Ranged Combat cards to 1 for both players.", 3,false),
        SKELLIGE_STORM("SkelligeStorm", "skelligeStorm", "Sets the strength of all Ranged Combat cards to 1 for both players.", 3,false),
        TORRENTIAL_RAIN("TorrentialRain", "torrentialRain", "Sets the strength of all Siege Combat cards to 1 for both players.", 3,false),
        ;

        private final String name;
        private final String abilityName;
        private final String description;
        private final int numberOfCard;
        private final boolean isDiscardAfterPlaying;



        SpecialCardsData(String name, String abilityName, String description, int cardsNumber, boolean isDiscardAfterPlaying) {
            this.name = name;
            this.abilityName = abilityName;
            this.description = description;
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
            Method ability = SpecialCardAbility.createNewAbilityByName(abilityName);
            return new SpecialCard(name, description, null,this ,this.isDiscardAfterPlaying,ability);
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

        @Override
        public int getNumber() {
            return numberOfCard;
        }

        @Override
        public int getPoint() {
            return 0;
        }

        public String getAbilityName() {
//            String abilityName = this.abilityName.replaceAll("(?=[A-Z])", " ");
//            abilityName = abilityName.charAt(0) + abilityName.substring(1);
//            if (abilityName.equals("Commander Horn")) abilityName = "Commander's Horn";
//            return abilityName;
            return abilityName;
        }
    }









