package gwent.controller;

import controller.MainMenuController;
import enums.MenuScene;
import javafx.stage.Stage;
import model.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainMenuControllerTest extends ApplicationTest {

    MainMenuController mainMenuController = new MainMenuController();
    static String s;
    @Override
    public void start(Stage stage) {
        App.loadUsers();
        App.testSetup();
        App.setLoggedInUser(App.getUserByUsername("pishi"), false);


        App.setCurrentMenuScene(MenuScene.MAIN_SCENE);
        stage.setScene(App.getCurrentMenuScene().getScene());
        App.setPrimaryStage(stage);

        mainMenuController.goToProfileMenu();
        s = App.getCurrentMenuScene().name();

        mainMenuController.logout();


    }

    @Test
    public void testLogout(){
        try {
            App.getLoggedInUser();
        } catch (Exception e) {
            Assertions.assertTrue(true);
            return;
        }
        Assertions.assertTrue(false);
    }

    @Test
    public void testProfileMenu(){
        assertEquals(s,"PROFILE_SCENE");
    }

}
