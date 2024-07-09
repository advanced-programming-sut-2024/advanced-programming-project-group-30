package view;

import enums.GameNotification;
import enums.cardsData.DeckCardData;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
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
            TranslateTransition translate = getTranslate(card, nodeBounds, destinationHBox, 0.4);
            SequentialTransition sequentialTransition = new SequentialTransition(translate);
            sequentialTransition.setOnFinished(event -> {
                sourceHBox.getChildren().remove(card.getCardView());
                destinationHBox.getChildren().add(card.getCardView());
                card.getCardView().setTranslateX(0);
                card.getCardView().setTranslateY(0);
                runAbility(card, game);
                gameMenu.updateGame(game);
                gameMenu.getPane().setDisable(false);
            });
            sequentialTransition.play();
        } catch (RuntimeException e) {
            System.err.println("duplicate children in cardPlaceAnimation method");
        }
    }
    //TODO:changed this

    public void discardAnimation(DecksCard card, HBox source, HBox destinationHBox, Game game, GameMenu gameMenu) {
        Bounds nodeBounds = card.getCardView().localToScene(card.getCardView().getBoundsInLocal());
        TranslateTransition translate = getTranslate(card, nodeBounds, destinationHBox, 0.4);
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

    public TranslateTransition getTranslate(DecksCard card, Bounds nodeBounds, HBox destinationHBox, double time) {
        double startX = nodeBounds.getMinX();
        double startY = nodeBounds.getMinY();
        Bounds destinationBounds = destinationHBox.localToScene(destinationHBox.getBoundsInLocal());
        double targetX = destinationBounds.getMinX() + (destinationBounds.getWidth() - card.getCardView().getWidth()) / 2;
        double targetY = destinationBounds.getMinY() + (destinationBounds.getHeight() - card.getCardView().getHeight()) / 2;
        TranslateTransition translate = new TranslateTransition(Duration.seconds(time), card.getCardView());
        translate.setFromX(0);
        translate.setFromY(0);
        translate.setToX(targetX - startX);
        translate.setToY(targetY - startY);
        return translate;
    }

    public Timeline getNotificationTimeline(Pane pane, Pane notifPane, ImageView notifImageView, Label notifLabel, GameNotification gameNotification) {
        notifImageView.getStyleClass().add(gameNotification.getNotificationImage());
        notifLabel.setText(gameNotification.getNotification());
        if (pane.getChildren().contains(notifPane)) {
            pane.getChildren().remove(notifPane);
        }
        pane.getChildren().add(notifPane);
        pane.setDisable(true);
        pane.getStyleClass().add("rootPaneNotifStyle");
        try {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                pane.getStyleClass().remove("rootPaneNotifStyle");
                notifImageView.getStyleClass().remove(gameNotification.getNotification());
                notifLabel.setText("");
                pane.setDisable(false);
                pane.getChildren().remove(notifPane);
            }));
            timeline.setCycleCount(1);
            return timeline;
        } catch (IllegalArgumentException e) {
            System.err.println("duplicate children in getNotificationTimeline method");
        }
        return null;
    }

    private void runAbility(DecksCard card, Game game) {
        if (((DeckCardData) card.getCardData()).getAbility() == null) return;
        if (card instanceof RegularCard regularCard) regularCard.run(game);
        else if (card instanceof WeatherCard weatherCard) weatherCard.run(game);

    }
}