package view;

import enums.GameNotification;
import enums.cardsData.DeckCardData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.WeatherCard;

public class AnimationMaker {
    private static AnimationMaker animationMaker;

    public static AnimationMaker getInstance() {
        if (animationMaker == null) {
            animationMaker = new AnimationMaker();
        }
        return animationMaker;
    }
    //TODO: changed this
    public void cardPlaceAnimation(DecksCard card, HBox destinationHBox, HBox sourceHBox, Game game, GameMenu gameMenu) {
        Bounds nodeBounds = card.getCardView().localToScene(card.getCardView().getBoundsInLocal());
        gameMenu.getPane().setDisable(true);
        try {
            TranslateTransition translate = getTranslate(card, nodeBounds, destinationHBox);
            translate.setOnFinished(event -> {
                sourceHBox.getChildren().remove(card.getCardView());
                destinationHBox.getChildren().add(card.getCardView());
                card.getCardView().setTranslateX(0);
                card.getCardView().setTranslateY(0);
                if (card instanceof WeatherCard weatherCard) {
                    weatherCard.run(game);
                } else if (card instanceof RegularCard regularCard) {
                    if (((DeckCardData) card.getCardData()).getAbility() != null) regularCard.run(game);
                }
                gameMenu.updateGame(game);
            });
            translate.play();
        } catch (RuntimeException e) {
            System.err.println("duplicate children in cardPlaceAnimation method");
        }
    }
    //TODO:changed this

    public void discardAnimation(DecksCard card, HBox source, HBox destinationHBox, Game game, GameMenu gameMenu) {
        Bounds nodeBounds = card.getCardView().localToScene(card.getCardView().getBoundsInLocal());
        TranslateTransition translate = getTranslate(card, nodeBounds, destinationHBox);
        try {
            translate.setOnFinished(event -> {
                destinationHBox.getChildren().clear();
                destinationHBox.getChildren().add(card.getCardView());
                card.getCardView().setTranslateX(0);
                card.getCardView().setTranslateY(0);
                gameMenu.updateGame(game);
                game.getCurrentPlayer().discardCard(card);
            });
            translate.play();
        } catch (RuntimeException e) {
            System.err.println("duplicate children in discardAnimation method");
        }
    }

    public TranslateTransition getTranslate(DecksCard card, Bounds nodeBounds, HBox destinationHBox) {
        double startX = nodeBounds.getMinX();
        double startY = nodeBounds.getMinY();
        Bounds destinationBounds = destinationHBox.localToScene(destinationHBox.getBoundsInLocal());
        double targetX = destinationBounds.getMinX() + (destinationBounds.getWidth() - card.getCardView().getWidth()) / 2;
        double targetY = destinationBounds.getMinY() + (destinationBounds.getHeight() - card.getCardView().getHeight()) / 2;
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3), card.getCardView());
        translate.setFromX(0);
        translate.setFromY(0);
        translate.setToX(targetX - startX);
        translate.setToY(targetY - startY);
        return translate;
    }

    public Timeline getNotificationTimeline(Pane pane, Pane notifPane, ImageView notifImageView, Label notifLabel, GameNotification gameNotification) {
        notifImageView.getStyleClass().add(gameNotification.getNotificationImage());
        notifLabel.setText(gameNotification.getNotification());
        pane.getChildren().add(notifPane);
        pane.setDisable(true);
        pane.getStyleClass().add("rootPaneNotifStyle");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            pane.getStyleClass().remove("rootPaneNotifStyle");
            notifImageView.getStyleClass().remove(gameNotification.getNotification());
            notifLabel.setText("");
            pane.setDisable(false);
            pane.getChildren().remove(notifPane);
        }));
        timeline.setCycleCount(1);
        return timeline;
    }


}
