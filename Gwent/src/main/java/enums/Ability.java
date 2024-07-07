package enums;

import model.ability.RegularCardsAbility;
import model.ability.SpecialCardAbility;
import model.ability.WeatherCardAbility;

import java.lang.reflect.Method;

public enum Ability {
    SPECIAL_COMMANDER_HORN("commanderHorn", "Doubles the strength of all unit cards in that row. Limited to 1 per row.", SpecialCardAbility.createNewAbilityByName("commanderHorn"), CssAddress.COMMANDERS_HORN_ABILITY_ICON),
    DECOY("decoy", "Swap with a card on the battlefield to return it to your hand.", SpecialCardAbility.createNewAbilityByName("decoy"), CssAddress.DECOY_ABILITY_ICON),
    MARDROEME("mardroeme", "Triggers transformation of all Berserker cards on the same row..", RegularCardsAbility.createNewAbilityByName("mardroeme"), CssAddress.MARDROEME_ABILITY_ICON),
    SCORCH("scorch", "Discard after playing. Kills the strongest card(s) on the battlefield. ", RegularCardsAbility.createNewAbilityByName("scorch"), CssAddress.SCORCH_ABILITY_ICON),
    SPECIAL_SCORCH("specialScorch", "Discard after playing. Kills the strongest card(s) on the battlefield. ", SpecialCardAbility.createNewAbilityByName("scorch"), CssAddress.SPECIAL_SCORCH_ICON),
    BITING_FROST("bitingFrost", "Sets the strength of all Close Combat cards to 1 for both players.", WeatherCardAbility.createNewAbilityByName("bitingFrost"), CssAddress.FROST_WEATHER_ICON),
    CLEAR_WEATHER("clearWeather", "Removes all Weather Cards (Biting Frost, Impenetrable Fog and Torrential Rain) effects.", WeatherCardAbility.createNewAbilityByName("clearWeather"), CssAddress.CLEAR_WEATHER_ICON),
    IMPENETRABLE_FOG("impenetrableFog", "Sets the strength of all Ranged Combat cards to 1 for both players.", WeatherCardAbility.createNewAbilityByName("impenetrableFog"), CssAddress.FOG_WEATHER_ICON),
    SKELLIGE_STORM("skelligeStorm", "Sets the strength of all Ranged Combat cards to 1 for both players.\nSets the strength of all Siege Combat cards to 1 for both players. ", WeatherCardAbility.createNewAbilityByName("skelligeStorm"), CssAddress.STORM_WEATHER_ICON),
    TORRENTIAL_RAIN("torrentialRain", "Sets the strength of all Siege Combat cards to 1 for both players.", WeatherCardAbility.createNewAbilityByName("torrentialRain"), CssAddress.RAIN_WEATHER_ICON),
    SPY("spy", "Place on your opponent's battlefield (counts towards your opponent's total) and draw 2 cards from your deck. Not affected by any Special Cards or abilities.", RegularCardsAbility.createNewAbilityByName("spy"), CssAddress.SPY_ABILITY_ICON),
    TIGHT_BOND("tightBond", "Place next to a card with the same name to double the strength of both cards.", RegularCardsAbility.createNewAbilityByName("tightBond"), CssAddress.TIGHT_BOND_ABILITY_ICON),
    MUSTER("muster", "Find any cards with the same name in your deck and play them instantly.", RegularCardsAbility.createNewAbilityByName("muster"), CssAddress.MUSTER_ABILITY_ICON),
    MORAL_BOOST("moralBoost", "Adds +1 to all units in the same row.", RegularCardsAbility.createNewAbilityByName("moralBoost"), CssAddress.MORAL_BOOST_ABILITY_ICON),
    HORN_COMMANDER("hornCommander", "Doubles the strength of all unit cards in that row. Limited to 1 per row.", RegularCardsAbility.createNewAbilityByName("commanderHorn"), CssAddress.COMMANDERS_HORN_ABILITY_ICON),
    MEDIC("medic", "Choose one card from your discard pile and play it instantly (no Heroes or Special Cards). ", RegularCardsAbility.createNewAbilityByName("medic"), CssAddress.MEDIC_ABILITY_ICON),
    TRANSFORMER("transformer", "When this card is removed from the battlefield,\nit summons a powerful new Unit Card to take its place.", RegularCardsAbility.createNewAbilityByName("transformer"), CssAddress.AVENGER_ABILITY_ICON),
    BERKSER("berserker", "Transforms into a bear when a Mardroeme card is on its row. ", RegularCardsAbility.createNewAbilityByName("berserker"), CssAddress.BERSERKER_ABILITY_ICON);

    ;


    private final String abilityName;
    private final String explanation;
    private final Method ability;
    private final CssAddress cssAddress;
    Ability(String abilityName, String explanation, Method ability, CssAddress cssAddress){
        this.abilityName = abilityName;
        this.explanation = explanation;
        this.ability = ability;
        this.cssAddress = cssAddress;
    }
    public String getExplanation(){
        return this.explanation;
    }
    public String getAbilityName(){
        return this.abilityName;
    }
    public String getStyleClass(){
        return this.cssAddress.getStyleClass();
    }
    public Method getAbility(){
        return this.ability;
    }
    public static String getStyleClassByName(String name){
        for(Ability ability : Ability.values()){
            if(ability.getAbilityName().equals(name)){
                return ability.getStyleClass();
            }
        }
        return null;
    }
    public static Method getAbilityByName(String abilityName){
        for(Ability ability : Ability.values()){
            if(ability.getAbilityName().equals(abilityName)){
                return ability.getAbility();
            }
        }
        return null;
    }

}
