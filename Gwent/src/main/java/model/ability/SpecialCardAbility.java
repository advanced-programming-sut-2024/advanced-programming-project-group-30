package model.ability;

import java.lang.reflect.Method;

public class SpecialCardAbility {
    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = SpecialCardAbility.class.getDeclaredMethod(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
    private void clearWeather(){

    }
    private void commanderHorn(){

    }
    private void decoy(){

    }
    private void scorch(){

    }
    private void spy(){

    }

}
