package enums;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import view.*;

import java.io.IOException;
import java.util.Scanner;

public enum Menu{
//    LOGIN_MENU(new LoginMenu()),
    MAIN_MENU("/FXML/MainMenu.fxml"),;
//    GAME_MENU(new GameMenu()),
//    PROFILE_MENU(new ProfileMenu()),
//    REGISTER_MENU(new RegisterMenu());

    private final Scene scene;
    private final view.Menu menu;

    Menu(String url){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        try{
            this.scene = new Scene(loader.load());
            this.menu = loader.getController(); 
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public void run(Scanner scanner) {
        menu.run(scanner);
    }
}
