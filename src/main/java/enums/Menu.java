package enums;

import view.GameMenu;
import view.LoginMenu;
import view.MainMenu;
import view.ProfileMenu;

public enum Menu {
    LOGIN_MENU(new LoginMenu()),
    MAIN_MENU(new MainMenu()),
    GAME_MENU(new GameMenu()),
    PROFILE_MENU(new ProfileMenu());
    private final view.Menu menu;
    Menu(view.Menu menu){
        this.menu = menu;
    }
}
