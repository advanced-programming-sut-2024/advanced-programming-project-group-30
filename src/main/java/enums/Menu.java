package enums;

import view.*;

public enum Menu {
    LOGIN_MENU(new LoginMenu()),
    MAIN_MENU(new MainMenu()),
    GAME_MENU(new GameMenu()),
    PROFILE_MENU(new ProfileMenu()),
    REGISTER_MENU(new RegisterMenu()),
    PREGAME_MENU(new PreGameMenu());
    private final view.Menu menu;
    Menu(view.Menu menu){
        this.menu = menu;
    }
}
