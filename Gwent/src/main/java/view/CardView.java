package view;

import enums.CssAddress;
import enums.RegularCardPositionType;
import enums.SizeData;
import enums.cardsData.CardData;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.card.Card;
import model.card.RegularCard;


public class CardView extends Pane {
    private Label point;
    private final Rectangle position;
    private final CardData cardData;
    private final Card card;
    private Group items;
    private static final double HERO_POINT_X = -2;
    private static final double HERO_POINT_Y = -2;
    private static final double HERO_POINT_LABEL_X = -1.1;
    private static final double HERO_POINT_LABEL_Y = 0;
    private static final double NORMAL_POINT_X = 0;
    private static final double NORMAL_POINT_Y = -0.3;
    private static final double NORMAL_POINT_LABEL_X = 1.4;
    private static final double NORMAL_POINT_LABEL_Y = 2.2;

    private static final double POSITION_X = 39.4;
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
        point.setPrefWidth(SizeData.GAME_SMALL_CARD_POINT_LABEL.getWidth());
        point.setText(String.valueOf(card.getPoint()));
        ImageView imageView = getSmCardImage();
        //TODO: separate hand and other cards
        this.getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        items.getChildren().addAll(rectangle, imageView, getPointImage(), getPositionImage(), point);
        ImageView abilityView = getRegularCardAbilityImage();
        if (abilityView != null)
            items.getChildren().add(abilityView);
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
        if (((RegularCard)card).isHero()) {
            pointView.getStyleClass().add(CssAddress.POWER_HERO_ICON.getStyleClass());
            pointView.setLayoutX(HERO_POINT_X);
            pointView.setLayoutY(HERO_POINT_Y);
            point.setLayoutX(HERO_POINT_LABEL_X);
            point.setLayoutY(HERO_POINT_LABEL_Y);
            point.getStyleClass().add(CssAddress.HERO_CARD_POINT_LABEL.getStyleClass());
        }
        else {
            pointView.getStyleClass().add(CssAddress.NORMAL_CARD_POINT_ICON.getStyleClass());
            pointView.setLayoutX(NORMAL_POINT_X);
            pointView.setLayoutY(NORMAL_POINT_Y);
            point.setLayoutX(NORMAL_POINT_LABEL_X);
            point.setLayoutY(NORMAL_POINT_LABEL_Y);
            point.getStyleClass().add(CssAddress.NORMAL_CARD_POINT_LABEL.getStyleClass());
        }
        pointView.setFitHeight(SizeData.GAME_SMALL_CARD_POINT.getHeight());
        pointView.setFitWidth(SizeData.GAME_SMALL_CARD_POINT.getWidth());
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
    private ImageView getRegularCardAbilityImage(){
        ImageView abilityImage = new ImageView();
        String abilityName = cardData.getAbilityName();

        if (abilityName.isEmpty() && !((RegularCard)card).getPositionType().equals(RegularCardPositionType.AGILE)){
            return null;
        }
        if (abilityName.isEmpty() && ((RegularCard)card).getPositionType().equals(RegularCardPositionType.AGILE)) {
            abilityImage.getStyleClass().add(CssAddress.AGILE_ABILITY_ICON.getStyleClass());
        }
        else {
            System.out.println(abilityName + "AbilityIcon");
            abilityImage.getStyleClass().add(CssAddress.getCssAddress(abilityName + "AbilityIcon"));
        }
        abilityImage.setFitHeight(SizeData.GAME_SMALL_CARD_ABILITY.getHeight());
        abilityImage.setFitWidth(SizeData.GAME_SMALL_CARD_ABILITY.getWidth());
        abilityImage.setLayoutX(23);
        abilityImage.setLayoutY(60);
        return abilityImage;
    }
}
