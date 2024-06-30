package view;

import javafx.scene.control.ScrollPane;
import model.PreGameData;
import model.User;

public class StartGameMenu implements Menu {

    public ScrollPane cardCollectionScrollPane;
    public ScrollPane cardInDeckScrollPane;

    public void initialize(){
        PreGameData preGameData = new PreGameData(new User("","","","",null,""));
        cardCollectionScrollPane.setContent(preGameData.getCardCollection());
        cardInDeckScrollPane.setContent(preGameData.getCardInDeck());
    }
}