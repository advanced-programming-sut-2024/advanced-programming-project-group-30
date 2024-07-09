package enums;

public enum GameNotification {
    PASS_TURN("Round Passed", CssAddress.PASS_TURN_IMAGE),
    YOUR_TURN("Your Turn", CssAddress.NOTIF_ME_TURN),
    OPPONENT_TURN("Opponent Turn", CssAddress.NOTIF_OPP_TURN),
    WIN_ROUND("You Won The Round", CssAddress.NOTIF_WIN_ROUND),
    LOSE_ROUND("You Lost The Round", CssAddress.NOTIF_LOSE_ROUND),
    DRAW_ROUND("Draw", CssAddress.NOTIF_DRAW_ROUND),
    ROUND_STARTS("Round Starts", CssAddress.NOTIF_ROUND_START);
    private final String notification;
    private final CssAddress notificationImage;

    GameNotification(String notification, CssAddress notificationImage) {
        this.notification = notification;
        this.notificationImage = notificationImage;
    }

    public String getNotification() {
        return notification;
    }

    public String getNotificationImage() {
        return notificationImage.getStyleClass();
    }
}