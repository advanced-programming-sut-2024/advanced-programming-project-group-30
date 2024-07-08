package enums;

import model.ability.RegularCardsAbility;
import model.ability.SpecialCardAbility;
import model.ability.WeatherCardAbility;

import java.lang.reflect.Method;

public enum Ability {
    SPECIAL_COMMANDER_HORN("Commander Horn", "Doubles the strength of all unit cards in that row.\n" +
            " Limited to 1 per row.", SpecialCardAbility.createNewAbilityByName("commanderHorn"), CssAddress.COMMANDERS_HORN_ABILITY_ICON),
    DECOY("Decoy", "Swap with a card on the battlefield to return it to your hand.",
            SpecialCardAbility.createNewAbilityByName("decoy"), CssAddress.DECOY_ABILITY_ICON),
    MARDROEME("Mardroeme", "Triggers transformation of all Berserker cards on the same row..",
            RegularCardsAbility.createNewAbilityByName("mardroeme"), CssAddress.MARDROEME_ABILITY_ICON),
    SCORCH("Scorch", "Discard after playing. Kills the strongest card(s) on the battlefield. ",
            RegularCardsAbility.createNewAbilityByName("scorch"), CssAddress.SCORCH_ABILITY_ICON),
    SPECIAL_SCORCH("Special Scorch", "Discard after playing. Kills the strongest card(s) on the battlefield. ",
            SpecialCardAbility.createNewAbilityByName("scorch"), CssAddress.SPECIAL_SCORCH_ICON),
    BITING_FROST("Biting Frost", "Sets the strength of all Close Combat cards to 1 for both players.",
            WeatherCardAbility.createNewAbilityByName("bitingFrost"), CssAddress.FROST_WEATHER_ICON),
    CLEAR_WEATHER("Clear Weather", "Removes all Weather Cards (Biting Frost, Impenetrable Fog and Torrential Rain) effects.",
            WeatherCardAbility.createNewAbilityByName("clearWeather"), CssAddress.CLEAR_WEATHER_ICON),
    IMPENETRABLE_FOG("Impenetrable Fog", "Sets the strength of all Ranged Combat cards to 1 for both players.",
            WeatherCardAbility.createNewAbilityByName("impenetrableFog"), CssAddress.FOG_WEATHER_ICON),
    SKELLIGE_STORM("Skellige Storm", "Sets the strength of all Ranged Combat cards to 1 for both players.\n" +
            "Sets the strength of all Siege Combat cards to 1 for both players. ", WeatherCardAbility.createNewAbilityByName("skelligeStorm"), CssAddress.STORM_WEATHER_ICON),
    TORRENTIAL_RAIN("Torrential Rain", "Sets the strength of all Siege Combat cards to 1 for both players.",
            WeatherCardAbility.createNewAbilityByName("torrentialRain"), CssAddress.RAIN_WEATHER_ICON),
    SPY("Spy", "Place on your opponent's battlefield (counts towards your opponent's total)\n" +
            " and draw 2 cards from your deck. Not affected by any Special Cards or abilities.", RegularCardsAbility.createNewAbilityByName("spy"), CssAddress.SPY_ABILITY_ICON),
    TIGHT_BOND("Tight Bond", "Place next to a card with the same name to double the strength of both cards.",
            RegularCardsAbility.createNewAbilityByName("tightBond"), CssAddress.TIGHT_BOND_ABILITY_ICON),
    MUSTER("Muster", "Find any cards with the same name in your deck and play them instantly.",
            RegularCardsAbility.createNewAbilityByName("muster"), CssAddress.MUSTER_ABILITY_ICON),
    MORAL_BOOST("Moral Boost", "Adds +1 to all units in the same row.",
            RegularCardsAbility.createNewAbilityByName("moralBoost"), CssAddress.MORAL_BOOST_ABILITY_ICON),
    HORN_COMMANDER("Horn Commander", "Doubles the strength of all unit cards in that row.\n" +
            " Limited to 1 per row.", RegularCardsAbility.createNewAbilityByName("commanderHorn"), CssAddress.COMMANDERS_HORN_ABILITY_ICON),
    MEDIC("Medic", "Choose one card from your discard pile and play it instantly (no Heroes or Special Cards).",
            RegularCardsAbility.createNewAbilityByName("medic"), CssAddress.MEDIC_ABILITY_ICON),
    TRANSFORMER("Transformer", "When this card is removed from the battlefield,\n" +
            "it summons a powerful new Unit Card to take its place.", RegularCardsAbility.createNewAbilityByName("transformer"), CssAddress.AVENGER_ABILITY_ICON),
    BERKSER("Berserker", "Transforms into a bear when a Mardroeme card is on its row. ", RegularCardsAbility.createNewAbilityByName("berserker"), CssAddress.BERSERKER_ABILITY_ICON);;

    private final String abilityName;
    private final String explanation;
    private final Method ability;
    private final CssAddress cssAddress;

    Ability(String abilityName, String explanation, Method ability, CssAddress cssAddress) {
        this.abilityName = abilityName;
        this.explanation = explanation;
        this.ability = ability;
        this.cssAddress = cssAddress;
    }

    public static String getStyleClassByName(String name) {
        for (Ability ability : Ability.values())
            if (ability.getAbilityMethodName().equals(name)) return ability.getStyleClass();
        return null;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getAbilityMethodName() {
        return abilityName.substring(0, 1).toLowerCase() + abilityName.substring(1).replace(" ", "");
    }

    public String getExplanation() {
        return this.explanation;
    }

    public Method getAbility() {
        return this.ability;
    }

    public String getStyleClass() {
        return this.cssAddress.getStyleClass();
    }
}