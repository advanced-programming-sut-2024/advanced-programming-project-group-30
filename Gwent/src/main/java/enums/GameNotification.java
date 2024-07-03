package enums;

public enum GameNotification {
    PASS_TURN("Round Passed", CssAddress.PASS_TURN_IMAGE);
    private final String notification;
    private final CssAddress notificationImage;
    GameNotification(String notification, CssAddress notificationImage) {
        this.notification = notification;
        this.notificationImage = notificationImage;
    }
    public String getNotification() {
        return notification;
    }
    public String  getNotificationImage() {
        return notificationImage.getStyleClass();
    }

}
