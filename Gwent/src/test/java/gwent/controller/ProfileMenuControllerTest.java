package gwent.controller;

import controller.ProfileMenuController;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileMenuControllerTest {

    ProfileMenuController profileMenuController = new ProfileMenuController();

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        if(!TestsUtilites.javafxInit){
            TestsUtilites.javafxInit = true;
            final CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(() -> {
                App.setPrimaryStage(new Stage());
                latch.countDown();
            });
            latch.await();
        }
        App.testSetup();
        App.setLoggedInUser(App.getUserByUsername("pishi"), true);

    }

    @Test
    public void testChangeUsername(){
        Result result = profileMenuController.changeUsername("skywalker");
        assertEquals(App.getLoggedInUser().getUsername(),"skywalker");
    }

    @Test
    public void changeNicknameTest(){
        Result result = profileMenuController.changeNickname("luke");
        assertEquals(App.getLoggedInUser().getNickname(),"luke");
    }

    @Test
    public void changeEmailTest(){
        Result result = profileMenuController.changeEmail("luke@jedi.edu");
        assertEquals(App.getLoggedInUser().getEmail(),"luke@jedi.edu");
    }

    @Test
    public void changePasswordTest(){
        Result result = profileMenuController.changePassword("Luke!1234", "p");
        assertEquals(App.getLoggedInUser().getPassword(),"Luke!1234");
    }

    @Test
    public void historyTest1(){
        App.getLoggedInUser().addToLosses();
        App.isTesting = true;
        assertEquals(profileMenuController.showDefaultGameHistory().toString(), "** There are no games to show.");
    }

    @Test
    public void historyTest2(){
        App.getLoggedInUser().addToLosses();
        App.isTesting = true;
        assertEquals(profileMenuController.showGameHistoryByUserRequest("1").toString(), "** you don't have that many games.");
    }

    @Test
    public void historyTest3(){
        App.getLoggedInUser().addToLosses();
        App.isTesting = true;
        assertEquals(profileMenuController.checkGameHistory("2").toString(), "** you don't have that many games.");
    }

}
