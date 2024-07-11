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
public class PregameMenuControllerSetupTest extends ApplicationTest {

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

        preAnswer = pregameData.getSpecialCardsNumber() >= 20;

        startGameResult = pregameMenuController.startGame();
    }

    @Test
    @Order(2)
    public void setupTest() throws InterruptedException {

        String factionName = pregameMenuController.getPregameData().getFaction().getName();

        assertEquals(factionName, FactionType.MONSTERS.getName());
    }



    @Test
    @Order(3)
    public void changeTurnTest(){
        Result result = pregameMenuController.changeTurn();
        assertEquals(result.isNotSuccessful(), preAnswer);
    }



}
