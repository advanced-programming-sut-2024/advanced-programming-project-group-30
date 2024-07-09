package view;

import enums.Ability;
import enums.CssAddress;
import enums.RegularCardPositionType;
import enums.SizeData;
import enums.cardsData.CardData;
import enums.cardsData.RegularCardData;
import enums.cardsData.SpecialCardsData;
import enums.cardsData.WeatherCardsData;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.card.Card;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;

//TODO: handle Card and CardView loop in saving
public class CardView extends Pane {
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
    private static final double ABILITY_X = 23;
    private static final double ABILITY_Y = 60;
    private final CardData cardData;
    private final Card card;
    private final Label point;

    public CardView(Card card) {
        this.card = card;
        this.cardData = card.getCardData();
        this.point = new Label();
        if (card instanceof RegularCard) setUpRegularCardView((RegularCard) card);
        if (card instanceof SpecialCard) setUpSpecialCardView();
        if (card instanceof WeatherCard) setUpWeatherCardView();
    }

    public Card getCard() {
        return card;
    }

    public void updatePoint() {
        setPointColor();
        this.getChildren().remove(point);
        point.setText(String.valueOf(((RegularCard) card).getPointInGame()));
        this.getChildren().add(point);
    }

    private void setPointColor() {
        int point = ((RegularCard) card).getPoint();
        int pointInGame = ((RegularCard) card).getPointInGame();
        if (pointInGame > point) this.point.getStyleClass().add(CssAddress.EXTRA_POINT.getStyleClass());
        if (pointInGame < point) {
            this.point.getStyleClass().add(CssAddress.POINT_LOSS.getStyleClass());
        }
        if (pointInGame == point) this.point.getStyleClass().add(CssAddress.REGULAR_POINT.getStyleClass());
    }

    private void setUpWeatherCardView() {
        ImageView imageView = getSmCardImage();
        ImageView abilityView = getWeatherCardAbilityView();
        this.getChildren().addAll(imageView, abilityView);
    }

    private void setUpSpecialCardView() {
        ImageView imageView = getSmCardImage();
        ImageView abilityView = getSpecialCardAbilityView();
        this.getChildren().addAll(imageView, abilityView);
    }

    private void setUpRegularCardView(RegularCard card) {
        point.setPrefWidth(SizeData.GAME_SMALL_CARD_POINT_LABEL.getWidth());
        point.setText(String.valueOf(card.getPoint()));
        ImageView imageView = getSmCardImage();
        this.getChildren().addAll(imageView, getPointImage(), getPositionImage(), point);
        ImageView abilityView = getRegularCardAbilityImage();
        if (abilityView != null) this.getChildren().add(abilityView);
    }

    private ImageView getSmCardImage() {
        ImageView imageView = new ImageView(cardData.getSmImage());
        imageView.setFitHeight(SizeData.GAME_SM_CARD.getHeight());
        imageView.setFitWidth(SizeData.GAME_SM_CARD.getWidth());
        return imageView;
    }

    private ImageView getPointImage() {
        ImageView pointView = new ImageView();
        if (((RegularCard) card).isHero()) {
            pointView.getStyleClass().add(CssAddress.POWER_HERO_ICON.getStyleClass());
            pointView.setLayoutX(HERO_POINT_X);
            pointView.setLayoutY(HERO_POINT_Y);
            point.setLayoutX(HERO_POINT_LABEL_X);
            point.setLayoutY(HERO_POINT_LABEL_Y);
            point.getStyleClass().add(CssAddress.HERO_CARD_POINT_LABEL.getStyleClass());
        } else {
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

    private ImageView getPositionImage() {
        RegularCardPositionType positionType = ((RegularCard) card).getPositionType();
        ImageView positionImage = new ImageView(positionType.getIconImage());
        positionImage.setFitHeight(SizeData.GAME_SMALL_CARD_POSITION.getHeight());
        positionImage.setFitWidth(SizeData.GAME_SMALL_CARD_POSITION.getWidth());
        positionImage.setLayoutX(POSITION_X);
        positionImage.setLayoutY(POSITION_Y);
        return positionImage;
    }

    private ImageView getRegularCardAbilityImage() {
        ImageView abilityImage = new ImageView();
        Ability ability = ((RegularCardData) cardData).getAbility();
        if (ability == null) {
            if (!((RegularCard) card).getPositionType().equals(RegularCardPositionType.AGILE)) {
                return null;
            }
            if (((RegularCard) card).getPositionType().equals(RegularCardPositionType.AGILE)) {
                abilityImage.getStyleClass().add(CssAddress.AGILE_ABILITY_ICON.getStyleClass());
            }
        } else {
            abilityImage.getStyleClass().add(ability.getStyleClass());
        }
        abilityImage.setFitHeight(SizeData.GAME_SMALL_CARD_ABILITY.getHeight());
        abilityImage.setFitWidth(SizeData.GAME_SMALL_CARD_ABILITY.getWidth());
        abilityImage.setLayoutX(ABILITY_X);
        abilityImage.setLayoutY(ABILITY_Y);
        return abilityImage;
    }

    private ImageView getSpecialCardAbilityView() {
        ImageView specialAbilityImage = new ImageView();
        specialAbilityImage.getStyleClass().add(Ability.getStyleClassByName(((SpecialCardsData) cardData).getAbility().getAbilityMethodName()));
        specialAbilityImage.setFitHeight(SizeData.GAME_SMALL_CARD_SPECIAL_ABILITY.getHeight());
        specialAbilityImage.setFitWidth(SizeData.GAME_SMALL_CARD_SPECIAL_ABILITY.getWidth());
        specialAbilityImage.setLayoutX(NORMAL_POINT_X);
        specialAbilityImage.setLayoutY(NORMAL_POINT_Y);
        return specialAbilityImage;
    }

    private ImageView getWeatherCardAbilityView() {
        ImageView weatherAbilityImage = new ImageView();
        weatherAbilityImage.getStyleClass().add(Ability.getStyleClassByName(((WeatherCardsData) cardData).getAbility().getAbilityMethodName()));
        weatherAbilityImage.setFitHeight(SizeData.GAME_SMALL_CARD_SPECIAL_ABILITY.getHeight());
        weatherAbilityImage.setFitWidth(SizeData.GAME_SMALL_CARD_SPECIAL_ABILITY.getWidth());
        weatherAbilityImage.setLayoutX(NORMAL_POINT_X);
        weatherAbilityImage.setLayoutY(NORMAL_POINT_Y);
        return weatherAbilityImage;
    }
}
