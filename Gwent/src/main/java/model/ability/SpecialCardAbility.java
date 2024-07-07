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
    public void commanderHorn(){

    }
    public void decoy(){

    }
    public void scorch(){

    }
    public void spy(){

    }

}
