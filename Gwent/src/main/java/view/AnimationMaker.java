package view;

import com.sun.source.tree.BreakTree;
import enums.Ability;
import enums.cardsData.WeatherCardsData;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.card.DecksCard;
import model.card.WeatherCard;

public class AnimationMaker {
    private static AnimationMaker animationMaker;
    public static AnimationMaker getInstance(){
        if (animationMaker == null){
            animationMaker = new AnimationMaker();
        }
        return animationMaker;
    }
    public void cardPlaceAnimation(DecksCard card, HBox destinationHBox, HBox sourceHBox, Game game){
        Bounds nodeBounds = card.getCardView().localToScene(card.getCardView().getBoundsInLocal());
        TranslateTransition translate = getTranslate(card, nodeBounds, destinationHBox);
        translate.setOnFinished(event -> {
            sourceHBox.getChildren().remove(card.getCardView());
            destinationHBox.getChildren().add(card.getCardView());
            card.getCardView().setTranslateX(0);
            card.getCardView().setTranslateY(0);
            if (card instanceof WeatherCard weatherCard) {
                weatherCard.run(game);
            }
        });
        translate.play();
    }
    public void discardAnimation(DecksCard card, HBox source, Game game){
        Bounds nodeBounds = card.getCardView().localToScene(card.getCardView().getBoundsInLocal());
        HBox destinationHBox = game.getCurrentPlayer().getPlayerView().getDiscardPileView();
        TranslateTransition translate = getTranslate(card, nodeBounds, destinationHBox);
        translate.setOnFinished(event -> {
            destinationHBox.getChildren().clear();
            source.getChildren().remove(card.getCardView());
            destinationHBox.getChildren().add(card.getCardView());
            card.getCardView().setTranslateX(0);
            card.getCardView().setTranslateY(0);
            game.getCurrentPlayer().discardCard(card);

        });
        translate.play();
    }

    public static TranslateTransition getTranslate(DecksCard card, Bounds nodeBounds, HBox destinationHBox) {
        double startX = nodeBounds.getMinX();
        double startY = nodeBounds.getMinY();
        Bounds destinationBounds = destinationHBox.localToScene(destinationHBox.getBoundsInLocal());
        double targetX = destinationBounds.getMinX() + (destinationBounds.getWidth() - card.getCardView().getWidth()) / 2;
        double targetY = destinationBounds.getMinY() + (destinationBounds.getHeight() - card.getCardView().getHeight()) / 2;
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.4), card.getCardView());
        translate.setFromX(0);
        translate.setFromY(0);
        translate.setToX(targetX - startX);
        translate.setToY(targetY - startY);
        return translate;
    }


}
