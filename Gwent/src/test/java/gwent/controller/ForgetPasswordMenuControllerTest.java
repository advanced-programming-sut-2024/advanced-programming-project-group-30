package gwent.controller;


import controller.ForgetPasswordMenuController;
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
public class ForgetPasswordMenuControllerTest {

    ForgetPasswordMenuController forgetPasswordMenuController = new ForgetPasswordMenuController();

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
        App.loadUsers();
        App.testSetup();
    }

    @Test
    public void checkUsernameTest(){
        Result res = forgetPasswordMenuController.checkUsername("pishi");
        assertFalse(res.isNotSuccessful());
    }

    @Test
    public void getPasswordTest1(){
        Result res = forgetPasswordMenuController.getPassword(
                "pishi", SecurityQuestion.QUESTION_1.toString(), "p2");
        assertTrue(res.isNotSuccessful());
    }

    @Test
    public void getPasswordTest2(){
        Result res = forgetPasswordMenuController.getPassword(
                "pishi", SecurityQuestion.QUESTION_1.toString(), "p");
        assertFalse(res.isNotSuccessful());
    }

}
