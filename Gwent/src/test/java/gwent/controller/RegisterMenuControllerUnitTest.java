package gwent.controller;

import controller.RegisterMenuController;
import enums.SecurityQuestion;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.App;
import model.Result;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterMenuControllerUnitTest {

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
    }


    RegisterMenuController registerMenuController = new RegisterMenuController();

    @org.junit.jupiter.api.Test
    void testGenerateRandomPassword() throws Exception {
        String randomString = registerMenuController.createRandomPassword();
        System.out.println(randomString);
        if(!randomString.matches("^.*[a-z]+.*$")){
            throw new Exception("no lowercase letters!", null);
        }
        if(!randomString.matches("^.*[A-Z]+.*$")){
            throw new Exception("no uppercase letters!", null);
        }
        if(!randomString.matches("^.*[0-9]+.*$")){
            throw new Exception("no numbers!", null);
        }
        if(randomString.matches("^[A-Za-z0-9]+$")){
            throw new Exception("no symbols!", null);
        }
        System.out.println("RANDOM PASSWORD GENERATION HANDLED PERFECTLY.");
    }


    @org.junit.jupiter.api.Test
    void testSecurityQuestion1() throws Exception {
        Result test = registerMenuController.checkSecurityQuestion(
                SecurityQuestion.QUESTION_1.toString(), "");
        if (!test.isNotSuccessful()) {
            throw new Exception("empty answer not handled", null);
        }
        if (!test.isNotSuccessful()) {
            throw new Exception("null question not handled", null);
        }
        System.out.println("SECURITY QUESTION TEST HANDLED PERFECTLY.");

    }

    @org.junit.jupiter.api.Test
    void testSecurityQuestion2() throws Exception {
        Result test = registerMenuController.checkSecurityQuestion(
                null, "");
        if (!test.isNotSuccessful()) {
            throw new Exception("empty answer not handled", null);
        }
        if (!test.isNotSuccessful()) {
            throw new Exception("null question not handled", null);
        }
        System.out.println("SECURITY QUESTION TEST HANDLED PERFECTLY.");

    }

    @org.junit.jupiter.api.Test
    void testSecurityQuestion3() throws Exception {
        Result test = registerMenuController.checkSecurityQuestion(
                SecurityQuestion.QUESTION_1.toString(), "Something");
        if(test.isNotSuccessful()){
            throw new Exception("TEST 3 NOT HANDLED PROPERLY!");
        }
        System.out.println("SECURITY QUESTION TEST HANDLED PERFECTLY.");

    }


    @Test
    void testUserRegistration(){
        App.loadUsers();
        registerMenuController.register(
                "mamadali",
                "Mam@d!1234",
                "abam",
                "abam@sharif.edu",
                SecurityQuestion.QUESTION_4.toString(),
                "Tokhmorgh"
        );
        String storedNickname = (App.getUserByUsername("mamadali").getNickName());
        assertEquals(storedNickname, "abam");
    }



}
