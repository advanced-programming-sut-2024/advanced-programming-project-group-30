package gwent.controller;


import controller.ForgetPasswordMenuController;
import controller.GameMenuController;
import controller.LoginMenuController;
import controller.PregameMenuController;
import enums.FactionType;
import enums.MenuScene;
import enums.SecurityQuestion;
import enums.cardsData.DeckCardData;
import enums.cardsData.LeaderCardData;
import enums.cardsData.SpecialCardsData;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.*;
import model.card.SpecialCard;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import view.GameMenu;
import view.PregameCardView;
import view.PregameMenu;
import view.SelectionPage;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PregameMenuControllerTest extends ApplicationTest {

    static PregameMenuController pregameMenuController;
    static PregameData pregameData;
    static Result startGameResult;
    static boolean preAnswer = false;

    @Override
    public void start(Stage stage) {
        App.loadUsers();
        App.testSetup();
        App.isTesting = true;
        User user1 = App.getUserByUsername("pishi");
        User user2 = App.getUserByUsername("jojo");
        user1.setSelectedFaction(FactionType.MONSTERS);
        user2.setSelectedFaction(FactionType.MONSTERS);

        pregameData = new PregameData(
                user1, user2
        );

        App.setCurrentMenuScene(MenuScene.PREGAME_SCENE);
        stage.setScene(App.getCurrentMenuScene().getScene());
        App.setPrimaryStage(stage);

        PregameMenu pregameMenu = new PregameMenu();
        pregameMenu.setup(pregameData);

        pregameMenuController = new PregameMenuController(pregameMenu);
        pregameMenuController.setup(pregameData);

        preAnswer = pregameData.getSpecialCardsNumber() > 20;

        startGameResult = pregameMenuController.startGame();
    }

    @Test
    @Order(1)
    public void startGameTest()  {
        // test if not throws anything
        assertEquals(startGameResult.isNotSuccessful(), preAnswer);
    }



    @Test
    @Order(4)
    public void changeFactionTest(){
        pregameMenuController.changeFation(FactionType.NORTHERN_REALMS);
        FactionType f = pregameMenuController.getPregameData().getFaction();
        assertEquals(FactionType.NORTHERN_REALMS, f);

    }

    @Test
    @Order(5)
    public void changeLeaderTest(){
        pregameMenuController.selectLeader(LeaderCardData.MONSTERS_EREDIN_GOLD);
    }



}
