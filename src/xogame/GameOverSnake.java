package xogame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class GameOverSnake extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label score;
    protected final Pane pane;
    protected final Button btn_back;
    protected final Button btn_newGame;
    protected final Blend blend;

    public GameOverSnake() {

        label = new Label();
        label0 = new Label();
        score = new Label();
        pane = new Pane();
        btn_back = new Button();
        btn_newGame = new Button();
        blend = new Blend();

        setId("AnchorPane");
        setPrefHeight(540.0);
        setPrefWidth(600.0);
        
        String bg="resources/snake_bg.png";
        setBackground(new Background(new BackgroundImage(new Image(bg), BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        label.setLayoutX(169.0);
        label.setLayoutY(68.0);
        label.setText("GAME OVER");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font(53.0));

        label0.setLayoutX(197.0);
        label0.setLayoutY(252.0);
        label0.setText("Your Score :");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font(25.0));

        score.setId("score");
        score.setLayoutX(338.0);
        score.setLayoutY(243.0);
        score.setTextFill(javafx.scene.paint.Color.WHITE);
        score.setFont(new Font(37.0));
      

        pane.setLayoutX(14.0);
        pane.setLayoutY(461.0);

        btn_back.setId("btn_back");
        btn_back.setMnemonicParsing(false);
        btn_back.setOpacity(1);
        btn_back.setPrefHeight(50.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/back_snake.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        btn_newGame.setId("btn_newGame");
        btn_newGame.setLayoutX(209.0);
        btn_newGame.setLayoutY(336.0);
        btn_newGame.setMnemonicParsing(false);
        btn_newGame.setPrefHeight(39.0);
        btn_newGame.setPrefWidth(182.0);
        btn_newGame.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 30px;");
        btn_newGame.setText("NEW GAME");
        btn_newGame.setTextFill(javafx.scene.paint.Color.valueOf("#97b400"));

        blend.setMode(javafx.scene.effect.BlendMode.OVERLAY);
        btn_newGame.setEffect(blend);
        btn_newGame.setFont(new Font("System Bold", 18.0));

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(score);
        pane.getChildren().add(btn_back);
        getChildren().add(pane);
        getChildren().add(btn_newGame);

    }
}
