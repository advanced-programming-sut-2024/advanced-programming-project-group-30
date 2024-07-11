package enums.cardsData;

import enums.Ability;
import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.card.RegularCard;
import view.ChosenModelView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public enum MonstersRegularCardsData implements RegularCardData {
    DRAUG("Draug", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    IMLERITH("Imlerith", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    LESHEN("Leshen", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAYRAN("Kayran", Ability.MORAL_BOOST, true, 8, 1, RegularCardPositionType.AGILE),
    TOAD("Toad", Ability.SCORCH, false, 7, 1, RegularCardPositionType.RANGED_COMBAT),
    ARACHAS_BEHEMOTH("Arachas Behemoth", Ability.MUSTER, false, 6, 1, RegularCardPositionType.SIEGE),
    CRONE_BREWESS("Crone:Brewess", Ability.MUSTER, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CRONE_WEAVESS("Crone:Weavess", Ability.MUSTER, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CRONE_WHISPESS("Crone:Whispess", Ability.MUSTER, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    EARTH_ELEMENTAL("Earth Elemental", null, false, 6, 1, RegularCardPositionType.SIEGE),
    FIEND("Fiend", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    FIRE_ELEMENTAL("Fire Elemental", null, false, 6, 1, RegularCardPositionType.SIEGE),
    FORKTAIL("Forktail", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    FRIGHTENER("Frightener", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    GRAVE_HAG("Grave Hag", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    GRIFFIN("Griffin", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ICE_GIANT("Ice Giant", null, false, 5, 1, RegularCardPositionType.SIEGE),
    PLAGUE_MAIDEN("Plague Maiden", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_KATAKAN("Vampire:Katakan", Ability.MUSTER, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    WEREWOLF("Werewolf", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ARACHAS("Arachas", Ability.MUSTER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    ARACHAS_1("Arachas", Ability.MUSTER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    ARACHAS_2("Arachas", Ability.MUSTER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    BOTCHLING("Botchling", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_BRUXA("Vampire:Bruxa", Ability.MUSTER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_EKIMMARA("Vampire:Ekimmara", Ability.MUSTER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_FLEDER("Vampire:Fleder", Ability.MUSTER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_GARKAIN("Vampire:Garkain", Ability.MUSTER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CELAENO_HARPY("Celaeno Harpy", null, false, 2, 1, RegularCardPositionType.AGILE),
    COCKATRICE("Cockatrice", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    ENDREGA("Endrega", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    FOGLET("Foglet", null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    GARGOYLE("Gargoyle", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    HARPY("Harpy", null, false, 2, 1, RegularCardPositionType.AGILE),
    NEKKER("Nekker", Ability.MUSTER, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    NEKKER_1("Nekker", Ability.MUSTER, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    NEKKER_2("Nekker", Ability.MUSTER, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    WYVERN("Wyvern", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    GHOUL("Ghoul", Ability.MUSTER, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    GHOUL_1("Ghoul", Ability.MUSTER, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    GHOUL_2("Ghoul", Ability.MUSTER, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    ;

    private final String name;
    private final Ability ability;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/monsters_" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmCardsImages/monsters_" + this.toString().toLowerCase() + ".jpg";


    MonstersRegularCardsData(String name, Ability ability, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.ability = ability;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (MonstersRegularCardsData data : MonstersRegularCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                regularCards.add(data.createCard());
        return regularCards;
    }

    @Override
    public Ability getAbility() {
        return this.ability;
    }

    @Override
    public boolean isHero() {
        return isHero;
    }

    @Override
    public int getPoint() {
        return this.point;
    }

    @Override
    public int getNumber() {
        return numberOfCard;
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
    public ChosenModelView<DeckCardData> getChooseModelView() {
        return new ChosenModelView<>(Objects.requireNonNull(
                this.getClass().getResourceAsStream(lgImageAddress)), this, ability.getExplanation(), ability.getAbilityMethodName());
    }

    public RegularCard createCard() {
        Method ability = null;
        if (this.ability != null)
            ability = this.ability.getAbility();
        return new RegularCard(this.name, FactionType.MONSTERS, this, this.isHero, ability, this.cardPositionType);
    }
}