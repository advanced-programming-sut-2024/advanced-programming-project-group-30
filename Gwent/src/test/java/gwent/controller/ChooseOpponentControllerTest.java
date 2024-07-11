package gwent.controller;

import controller.ChooseOpponentController;
import enums.cardsData.DeckCardData;
import enums.cardsData.LeaderCardData;
import enums.cardsData.WeatherCardsData;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.App;
import model.PregameData;
import model.Result;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChooseOpponentControllerTest {

    ChooseOpponentController chooseOpponentController = new ChooseOpponentController();

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
    public void checkOpponentInformationTest() throws Exception{
        App.setLoggedInUser(App.getUserByUsername("jojo"), false);
        Result res = chooseOpponentController.checkOpponentInformation("pishi","p");
        assertEquals(res.isNotSuccessful(), false);
    }

    @Test
    public void createPregameDataTest() {
        App.setLoggedInUser(App.getUserByUsername("jojo"), false);
        PregameData pregameData = chooseOpponentController.createPregameData(
                "pishi");
        pregameData.addToPreDeck(WeatherCardsData.CLEAR_WEATHER);
        String oppsUsername = pregameData.getUser().getUsername();
        assertEquals(oppsUsername, "jojo");
    }

}
