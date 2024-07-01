package view;

import enums.CssAddress;
import enums.RegularCardPositionType;
import enums.SizeData;
import enums.cardsData.CardData;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.card.Card;
import model.card.RegularCard;

import java.util.Objects;


public class CardView extends Pane {
    private Label point;
    private final Rectangle position;
    private final CardData cardData;
    private final Card card;
    private Group items;
    private static final double POINT_X = 3;
    private static final double POINT_Y = 2;
    private static final double POINT_LABEL_X = 3.5;
    private static final double POINT_LABEL_Y = 3;
    private static final double POSITION_X = 37;
    private static final double POSITION_Y = 60;

    public CardView(Card card) {
        items = new Group();
        this.card = card;
        this.position = new Rectangle();
        this.cardData = card.getCardData();
        this.point = new Label();
        if (card instanceof RegularCard){
            setUpRegularCardView((RegularCard) card);
        }
    }
    private void setUpRegularCardView(RegularCard card){
        Rectangle rectangle = new Rectangle();
        point.setText(String.valueOf(card.getPoint()));
        point.setLayoutX(POINT_LABEL_X);
        point.setLayoutY(POINT_LABEL_Y);
        point.setPrefWidth(SizeData.GAME_SMALL_CARD_POSITION.getWidth());
        ImageView imageView = getSmCardImage();
        //TODO: separate hand and other cards
        this.getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        point.setText(String.valueOf(card.getPoint()));
        point.getStyleClass().add(CssAddress.SM_CARD_POINT_LABEL.getStyleClass());
        items.getChildren().addAll(rectangle, imageView, getPointImage(), getPositionImage(), point);
        this.getChildren().addAll(items);
    }
    private ImageView getSmCardImage(){
        ImageView imageView = new ImageView(cardData.getSmImage());
        imageView.setFitHeight(SizeData.GAME_SM_CARD.getHeight());
        imageView.setFitWidth(SizeData.GAME_SM_CARD.getWidth());
        return imageView;
    }
    private ImageView getPointImage(){
        ImageView pointView = new ImageView();
        pointView.getStyleClass().add(CssAddress.SM_CARD_POINT_ICON.getStyleClass());
        pointView.setFitHeight(SizeData.GAME_SMALL_CARD_POINT.getHeight());
        pointView.setFitWidth(SizeData.GAME_SMALL_CARD_POINT.getWidth());
        pointView.setLayoutX(POINT_X);
        pointView.setLayoutY(POINT_Y);
        return pointView;
    }
    private ImageView getPositionImage(){
        RegularCardPositionType positionType = ((RegularCard)card).getPositionType();
        ImageView positionImage = new ImageView();
        switch (positionType){
            case CLOSE_COMBAT:
                positionImage.getStyleClass().add(CssAddress.CARD_CLOSE_COMBAT_ROW_ICON.getStyleClass());
                break;
            case RANGED_COMBAT:
                positionImage.getStyleClass().add(CssAddress.CARD_RANGED_COMBAT_ROW_ICON.getStyleClass());
                break;
            case SIEGE:
                positionImage.getStyleClass().add(CssAddress.CARD_SIEGE_ROW_ICON.getStyleClass());
                break;
            default:
                positionImage.getStyleClass().add(CssAddress.CARD_AGILE_ICON.getStyleClass());
        }
        positionImage.setFitHeight(SizeData.GAME_SMALL_CARD_POSITION.getHeight());
        positionImage.setFitWidth(SizeData.GAME_SMALL_CARD_POSITION.getWidth());
        positionImage.setLayoutX(POSITION_X);
        positionImage.setLayoutY(POSITION_Y);
        return positionImage;
    }
}
