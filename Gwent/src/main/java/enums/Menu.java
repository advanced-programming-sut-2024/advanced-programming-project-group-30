package enums;

import view.*;

import java.util.Scanner;

public enum Menu {
    LOGIN_MENU(new LoginMenu()),
    MAIN_MENU(new MainMenu()),
    GAME_MENU(new GameMenu()),
    PROFILE_MENU(new ProfileMenu());

    private final view.Menu menu;

    Menu(view.Menu menu) {
        this.menu = menu;
    }

    public void run(Scanner scanner) {
        menu.run(scanner);
    }
}
