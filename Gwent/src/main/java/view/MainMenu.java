package view;

import enums.FactionType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.App;
import network.Client;
import network.ClientRequest;

import java.util.ArrayList;
import java.util.Collections;

public class MainMenu implements Menu {
    private final Client client = ClientView.getClient();
    @FXML
    private Pane mainPane;
    @FXML
    private VBox root;
    @FXML
    private Label infoLabel;

    public void initialize() {
        root.widthProperty().addListener((Void) -> scalePane(mainPane));
        root.heightProperty().addListener((Void) -> scalePane(mainPane));
    }

    public void setUserInfo(String username, String nickname) {
        this.infoLabel.setText("Welcome " + nickname + " :> -- (" + username + ")");
        infoLabel.requestFocus();
    }

    @FXML
    private void goToProfileMenu() {
        ClientRequest clientRequest = new ClientRequest("MainMenuController", "getProfileData",
                new ArrayList<>(Collections.singleton(App.getLoggedInUsersUsername())));
        client.sendMessageToServer(clientRequest);
        String[] fields = (String[]) client.getLastServerData(String[].class);
        App.getSceneManager().goToProfileMenu(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8]);
    }

    @FXML
    private void goToPregameMenu() {
        ClientRequest clientRequest = new ClientRequest("UserInformationController", "getUserFaction",
                new ArrayList<>(Collections.singleton(App.getLoggedInUsersUsername())));
        client.sendMessageToServer(clientRequest);
        App.getSceneManager().goToPregameMenu((FactionType) client.getLastServerData(FactionType.class));
    }

    @FXML
    private void logout() {
        ClientRequest clientRequest = new ClientRequest("MainMenuController", "logout",
                new ArrayList<>(Collections.singleton(App.getLoggedInUsersUsername())));
        client.sendMessageToServer(clientRequest);
        App.getSceneManager().goToLoginMenu();
        App.setLoggedInUser(null, "", "", false);
    }

    private void scalePane(Pane pane) {
        Double scale = App.getSceneManager().getScale(root.getWidth(), root.getHeight(), 1000, 625, 1);
        if (scale == null) return;
        pane.setScaleX(scale);
        pane.setScaleY(scale);
        pane.setLayoutX((root.getWidth() - 1150) / 2);
        pane.setLayoutY((root.getHeight() - 660) / 2 + 5 * (double) 1);
    }
}