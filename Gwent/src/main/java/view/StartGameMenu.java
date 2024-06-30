package view;

import javafx.scene.control.ScrollPane;
import model.PreGameData;
import model.User;

public class StartGameMenu implements Menu {

    public ScrollPane cardCollectionScrollPane;

    public void initialize(){
        cardCollectionScrollPane.setContent(new PreGameData(new User("","","","",null,"")).getCardCollection());
    }
}