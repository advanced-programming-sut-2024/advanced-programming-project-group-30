package view;

import controller.server.ProfileMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import model.App;
import model.Result;
import network.Client;
import network.ClientMessage;

import java.util.ArrayList;
import java.util.List;


public class ProfileMenu implements Menu {
    private final Client client = ClientView.getClient();
    private final ProfileMenuController profileMenuController = new ProfileMenuController();
    @FXML
    private Label passwordConfirmationErrorField;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button editUsernameButton;
    @FXML
    private Button editNicknameButton;
    @FXML
    private Button editEmailButton;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Pane changePasswordPane;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField gameHistoryCount;
    @FXML
    private Label usernameErrorField;
    @FXML
    private Label emailErrorField;
    @FXML
    private Label nicknameErrorField;
    @FXML
    private Label gameHistoryNumberErrorField;
    @FXML
    private Button setNewUsernameButton;
    @FXML
    private Button setNewNicknameButton;
    @FXML
    private Button setNewEmailButton;
    @FXML
    private Button cancelChangingUsernameButton;
    @FXML
    private Button cancelChangingNicknameButton;
    @FXML
    private Button cancelChangingEmailButton;
    @FXML
    private Label rank;
    @FXML
    private Label highestScore;
    @FXML
    private Label gameCount;
    @FXML
    private Label wins;
    @FXML
    private Label losses;
    @FXML
    private Label draws;
    @FXML
    private Label newPasswordError;
    @FXML
    private ScrollPane gameHistoryScrollPane;
    @FXML
    private VBox scrollPaneVbox;
    @FXML
    private VBox editInformationBox;

    @FXML
    public void initialize() {
        gameHistoryCount.setFocusTraversable(false);
        usernameTextField.textProperty().addListener((observableValue, s, t1) -> handleUsernameSetup());
        nicknameTextField.textProperty().addListener((observableValue, s, t1) -> handleNicknameSetup());
        emailTextField.textProperty().addListener((observableValue, s, t1) -> handleEmailSetup());
        newPassword.textProperty().addListener((observableValue, s, t1) -> handelNewPasswordError());
        oldPassword.setPromptText("old password");
    }

    public void setFields(String username, String nickname, String email, String rank, String highestScore,
                          String gameCount, String wins, String losses, String draws) {
        usernameTextField.setPromptText(username);
        nicknameTextField.setPromptText(nickname);
        emailTextField.setPromptText(email);
        setUserInformation(rank, highestScore, gameCount, wins, losses, draws);
        // TODO
//        Result result = profileMenuController.showDefaultGameHistory();
//        if (result.isNotSuccessful()) gameHistoryNumberErrorField.setText(result.toString());
//        else {
//            Text text = new Text(result.toString());
//            text.getStyleClass().add("profileMenu-scrollbar-textArea");
//            scrollPaneVbox.getChildren().add(text);
//            gameHistoryScrollPane.setContent(scrollPaneVbox);
//        }
    }

    @FXML
    private void backToMainMenu() {
        resetFields();
        App.getSceneManager().goToMainMenu(App.getLoggedInUsersUsername(), App.getLoggedInUsersNickname());
    }

    private void setUserInformation(String rank, String highestScore, String gameCount, String wins, String losses, String draws) {
        this.rank.setText(rank + "TH");
        this.highestScore.setText(highestScore + " is your best score");
        this.gameCount.setText("Games Played: " + gameCount);
        this.wins.setText("Wins: " + wins);
        this.losses.setText("Losses: " + losses);
        this.draws.setText("Draws: " + draws);
    }

    private void handleUsernameSetup() {
        if (!editUsernameButton.isVisible()) {
            ClientMessage clientMessage = new ClientMessage("UserInformationController", "checkUsername",
                    new ArrayList<>(List.of(new String[]{usernameTextField.getText()})));
            client.sendMessageToServer(clientMessage);
            usernameErrorField.setText(client.getLastServerData(Result.class).toString());
        } else usernameErrorField.setText("");
        usernameTextField.setPromptText(App.getLoggedInUsersUsername());
    }

    private void handleNicknameSetup() {
        if (!editNicknameButton.isVisible()) {
            ClientMessage clientMessage = new ClientMessage("UserInformationController", "checkNickname",
                    new ArrayList<>(List.of(new String[]{nicknameTextField.getText()})));
            client.sendMessageToServer(clientMessage);
            nicknameErrorField.setText(client.getLastServerData(Result.class).toString());
        } else nicknameErrorField.setText("");
        nicknameTextField.setPromptText(App.getLoggedInUsersNickname());
    }

    private void handleEmailSetup() {
        if (!editEmailButton.isVisible()) {
            ClientMessage clientMessage = new ClientMessage("UserInformationController", "checkEmail",
                    new ArrayList<>(List.of(new String[]{emailTextField.getText()})));
            client.sendMessageToServer(clientMessage);
            emailErrorField.setText(client.getLastServerData(Result.class).toString());
        } else emailErrorField.setText("");
        emailTextField.setPromptText(App.getLoggedInUsersEmail());
    }

    private void handelNewPasswordError() {
        ClientMessage clientMessage = new ClientMessage("UserInformationController", "checkPassword",
                new ArrayList<>(List.of(new String[]{newPassword.getText()})));
        client.sendMessageToServer(clientMessage);
        newPasswordError.setText(client.getLastServerData(Result.class).toString());
    }

    @FXML
    private void editUsername() {
        usernameTextField.setEditable(true);
        setNewUsernameButton.setVisible(true);
        cancelChangingUsernameButton.setVisible(true);
        editUsernameButton.setVisible(false);
        usernameTextField.setPromptText("Enter new username");
    }

    @FXML
    private void editNickname() {
        nicknameTextField.setEditable(true);
        nicknameTextField.setPromptText("Enter new nickname");
        editNicknameButton.setVisible(false);
        setNewNicknameButton.setVisible(true);
        cancelChangingNicknameButton.setVisible(true);
    }

    @FXML
    private void editEmail() {
        emailTextField.setEditable(true);
        emailTextField.setPromptText("Enter new email");
        editEmailButton.setVisible(false);
        setNewEmailButton.setVisible(true);
        cancelChangingEmailButton.setVisible(true);
    }

    @FXML
    private void checkAndSetNewUsername() {
        Result result = profileMenuController.changeUsername(usernameTextField.getText());
        checkAndSetNewInfo(result, usernameTextField, editUsernameButton, cancelChangingUsernameButton, setNewUsernameButton, usernameErrorField);
    }

    @FXML
    private void checkAndSetNewNickname() {
        Result result = profileMenuController.changeNickname(nicknameTextField.getText());
        checkAndSetNewInfo(result, nicknameTextField, editNicknameButton, cancelChangingNicknameButton, setNewNicknameButton, nicknameErrorField);
    }

    @FXML
    private void checkAndSetNewEmail() {
        Result result = profileMenuController.changeEmail(emailTextField.getText());
        checkAndSetNewInfo(result, emailTextField, editEmailButton, cancelChangingEmailButton, setNewEmailButton, emailErrorField);
    }

    @FXML
    private void cancelChangingUsername() {
//        cancelEditingTextField(usernameTextField, editUsernameButton, setNewUsernameButton,
//                cancelChangingUsernameButton, usernameErrorField, App.getLoggedInUsersUsername().getUsername());
    }

    @FXML
    private void cancelChangingNickname() {
//        cancelEditingTextField(nicknameTextField, editNicknameButton, setNewNicknameButton,
//                cancelChangingNicknameButton, nicknameErrorField, App.getLoggedInUsersUsername().getNickName());
    }

    @FXML
    private void cancelChangingEmail() {
//        cancelEditingTextField(emailTextField, editEmailButton, setNewEmailButton,
//                cancelChangingEmailButton, emailErrorField, App.getLoggedInUsersUsername().getEmail());
    }

    @FXML
    private void editPassword() {
        changePasswordButton.setVisible(false);
        editInformationBox.setVisible(false);
        changePasswordPane.setVisible(true);
    }

    @FXML
    private void setNewPassword() {
        Result result = profileMenuController.changePassword(newPassword.getText(), oldPassword.getText());
        if (result.isNotSuccessful()) passwordConfirmationErrorField.setText(result.toString());
        else {
            newPassword.clear();
            oldPassword.clear();
            passwordConfirmationErrorField.setText("");
            newPasswordError.setText("");
            changePasswordPane.setVisible(false);
            editInformationBox.setVisible(true);
            changePasswordButton.setVisible(true);
        }
    }

    @FXML
    private void backToEditInformation() {
        newPassword.clear();
        oldPassword.clear();
        newPasswordError.setText("");
        passwordConfirmationErrorField.setText("");
        editInformationBox.setVisible(true);
        changePasswordButton.setVisible(true);
        changePasswordPane.setVisible(false);
    }

    @FXML
    private void setGameHistoryCount() {
        Result result = profileMenuController.checkGameHistory(gameHistoryCount.getText());
        if (result.isNotSuccessful()) {
            gameHistoryNumberErrorField.setText(result.toString());
        } else {
            scrollPaneVbox.getChildren().clear();
            Text text = new Text(profileMenuController.showGameHistoryByUserRequest(gameHistoryCount.getText()).toString());
            text.getStyleClass().add("profileMenu-scrollbar-textArea");
            scrollPaneVbox.getChildren().add(text);
            gameHistoryScrollPane.setContent(scrollPaneVbox);
            gameHistoryNumberErrorField.setText("");
        }
    }

    private void checkAndSetNewInfo(Result result, TextField textField, Button editButton, Button cancel,
                                    Button check, Label errorField) {
        if (result.isNotSuccessful()) {
            errorField.setText(result.toString());
        } else {
            textField.setEditable(false);
            check.setVisible(false);
            textField.clear();
            errorField.setText("");
            cancel.setVisible(false);
            editButton.setVisible(true);
        }
    }

    private void cancelEditingTextField(TextField textField, Button editButton, Button checkButton,
                                        Button cancelButton, Label errorField, String promptText) {
        textField.setEditable(false);
        textField.clear();
        errorField.setText("");
        textField.setPromptText(promptText);
        checkButton.setVisible(false);
        cancelButton.setVisible(false);
        editButton.setVisible(true);

    }

    private void resetFields() {
        backToEditInformation();
        cancelChangingUsername();
        cancelChangingNickname();
        cancelChangingEmail();
        gameHistoryCount.setText("");
        usernameErrorField.setText("");
        emailErrorField.setText("");
        nicknameErrorField.setText("");
        passwordConfirmationErrorField.setText("");
        newPasswordError.setText("");
        gameHistoryNumberErrorField.setText("");
    }
}