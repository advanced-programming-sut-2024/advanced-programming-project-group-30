package enums.cardsData;

import enums.FactionType;
import javafx.scene.image.Image;
import model.ability.LeaderAbility;
import view.ChosenModelView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public enum LeaderCardData implements CardData {
    REALMS_FOLTEST_SILVER(FactionType.NORTHERN_REALMS, "Pick an Impenetrable Fog card from your deck" +
            " and play it instantly.", "theSiegemasterAbility", true),
    REALMS_FOLTEST_GOLD(FactionType.NORTHERN_REALMS, "Clear any weather effects (resulting from Biting Frost,\n" +
            " Torrential Rain or Impenetrable Fog cards) in play.", "theSteelForgedAbility", true),
    REALMS_FOLTEST_COPPER(FactionType.NORTHERN_REALMS, "Doubles the strength of all your Siege units" +
            " (unless a Commander's Horn\n is also present on that row).", "kingOfTemeriaAbility", true),
    REALMS_FOLTEST_BRONZE(FactionType.NORTHERN_REALMS, "Destroy your enemy's strongest Siege unit(s) if the combined\n" +
            " strength of all his or her Siege units is 10 or more.", "lordCommanderOfTheNorthAbility", true),
    REALMS_FOLTEST_SON_OF_MEDELL(FactionType.NORTHERN_REALMS, "Destroy your enemy's strongest Ranged Combat unit(s)" +
            " if \nthe combined strength of all his or her Ranged Combat units is 10 or more.", "sonOfMedellAbility", true),
    NILFGAARD_EMHYR_SILVER(FactionType.NILFGAARDIAN_EMPIRE, "Pick a Torrential Rain card from your deck" +
            " and play it instantly.", "theWhiteFlameAbility", true),
    NILFGAARD_EMHYR_COPPER(FactionType.NILFGAARDIAN_EMPIRE, "Look at 3 random cards from your opponent's hand.",
            "hisImperialMajestyAbility", true),
    NILFGAARD_EMHYR_BRONZE(FactionType.NILFGAARDIAN_EMPIRE, "Cancel your opponent's Leader Ability.",
            "emporerOfNilfgaardAbility", false),
    NILFGAARD_EMHYR_GOLD(FactionType.NILFGAARDIAN_EMPIRE, "Draw a card from your opponent's discard pile.",
            "theRelentlessAbility", false),
    NILFGAARD_EMHYR_INVADER_OF_THE_NORTH(FactionType.NILFGAARDIAN_EMPIRE, "Abilities that restore a unit to the battlefield\n" +
            " restore a randomly-chosen unit. Affects both players.", "invaderOfTheNorthAbility", false),
    MONSTERS_EREDIN_SILVER(FactionType.MONSTERS, "Double the strength of all your Close Combat units (unless a" +
            " Commander's horn is \talso present on that row).", "bringerOfDeathAbility", true),
    MONSTERS_EREDIN_BRONZE(FactionType.MONSTERS, "Restore a card from your discard pile to your hand.",
            "kingOfTheWildHuntAbility", true),
    MONSTERS_EREDIN_GOLD(FactionType.MONSTERS, "Discard 2 card and draw 1 card of your choice from your deck.",
            "destroyerOfWorldsAbility", true),
    MONSTERS_EREDIN_COPPER(FactionType.MONSTERS, "Pick any weather card from your deck and play it instantly.",
            "commanderOfTheRedRidersAbility", true),
    MONSTERS_EREDIN_THE_TREACHEROUS(FactionType.MONSTERS, "Doubles the strength of all spy cards (affects both players).",
            "theTreacherousAbility", false),
    SCOIATAEL_FRANCESCA_SILVER(FactionType.SCOIA_TAEL, "Destroy your enemy's strongest Close Combat unit(s) if\n" +
            " the combined strength of all his or her\n Close Combat units is 10 or more.", "queenOfDolBlathannaAbility", true),
    SCOIATAEL_FRANCESCA_GOLD(FactionType.SCOIA_TAEL, "Doubles the strength of all your Ranged Combat units\n" +
            " (unless a Commander's Horn is also present on that row).", "theBeautifulAbility", true),
    SCOIATAEL_FRANCESCA_COPPER(FactionType.SCOIA_TAEL, "Draw an extra card at the beginning of the battle.",
            "daisyOfTheValleyAbility", false),
    SCOIATAEL_FRANCESCA_BRONZE(FactionType.SCOIA_TAEL, "Pick a Biting Frost card from your deck and play it instantly.",
            "pureBloodElfAbility", true),
    SCOIATAEL_FRANCESCA_HOPE(FactionType.SCOIA_TAEL, "Move agile units to whichever valid row maximizes their strength\n" +
            " (don't move units already in optimal row).", "hopeOfTheAenSeidheAbility", true),
    SKELLIGE_CRACH_AN_CRAITE(FactionType.SKELLIGE, "Shuffle all cards from each player's graveyard back into\n" +
            " their decks.", "crachAnCraiteAbility", true),
    SKELLIGE_KING_BRAN(FactionType.SKELLIGE, "Units only lose half their Strength in bad weather conditions.",
            "kingBranAbility", true),
    ;

    private final FactionType faction;
    private final String explanation;
    private final Method ability;
    private final boolean isAbilityOneTime;
    private final String lgImageAddress = "/Images/Game/LgLeadersImages/" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmLeadersImages/" + this.toString().toLowerCase() + ".jpg";

    LeaderCardData(FactionType faction, String explanation, String abilityMethodName, boolean isAbilityOneTime) {
        this.faction = faction;
        this.explanation = explanation;
        this.ability = LeaderAbility.createNewAbilityByName(abilityMethodName);
        this.isAbilityOneTime = isAbilityOneTime;
    }

    public static ArrayList<LeaderCardData> getFactionsLeader(FactionType faction) {
        ArrayList<LeaderCardData> leaderCards = new ArrayList<>();
        for (LeaderCardData leaderCard : LeaderCardData.values())
            if (faction.equals(leaderCard.faction)) leaderCards.add(leaderCard);
        return leaderCards;
    }

    public Method getAbility() {
        return ability;
    }

    public boolean isAbilityOneTime() {
        return isAbilityOneTime;
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
    public ChosenModelView<LeaderCardData> getChooseModelView() {
        return new ChosenModelView<>(Objects.requireNonNull(
                this.getClass().getResourceAsStream(lgImageAddress)), this, "Leader Ability", explanation);
    }
}
