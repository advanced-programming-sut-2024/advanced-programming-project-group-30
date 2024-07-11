package gwent.controller;

import controller.LoginMenuController;
import enums.MenuScene;
import enums.SecurityQuestion;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.App;
import model.Result;
import model.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class LoginMenuControllerTest {

    LoginMenuController loginMenuController = new LoginMenuController();

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

    }


    @Test
    public void testAuthentication() throws Exception {
        Result result = loginMenuController.checkInformationForLogin(
                "pishi","p"
        );
        if(result.isNotSuccessful()){
            throw new Exception("AUTHENTICATION NOT HANDLED PROPERLY", null);
        }
        System.out.println("AUTHENTICATION HANDLED PERFECTLY");
    }

    @Test
    public void testEmptyString1() throws Exception {
        Result res = loginMenuController.getEmptyError(
                "", "abam");
        assertEquals(res.isNotSuccessful(), true);
    }

    @Test
    public void testEmptyString2() throws Exception {
        Result res = loginMenuController.getEmptyError(
                "r2", "abam");
        assertEquals(res.isNotSuccessful(), false);
    }

    @Test
    public void testLogin() throws Exception {
        App.setCurrentMenuScene(MenuScene.LOGIN_SCENE);
        App.loadUsers();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = new Stage();
                App.setPrimaryStage(stage);
                stage.setScene(App.getCurrentMenuScene().getScene());
                stage.setTitle("Gwent");
                App.setLoggedInUser(new User(
                                "1","2","3","4",SecurityQuestion.QUESTION_4,"34"),
                        true);
                loginMenuController.login("pishi", false);
            }
        });
    }



}
