package xogame;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class xoGameOver extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final Button btn_back;
    protected final Button btn_newGame;

    public xoGameOver() {

        anchorPane = new AnchorPane();
        imageView = new ImageView("resources/gameover.png");
        btn_back = new Button();
        btn_newGame = new Button();

        setId("AnchorPane");
        setPrefHeight(520.0);
        setPrefWidth(600.0);

        anchorPane.setId("AnchorPane");
        anchorPane.setLayoutY(-6.0);
        anchorPane.setPrefHeight(520.0);
        anchorPane.setPrefWidth(600.0);
        anchorPane.setStyle("-fx-background-color: #2a3646;");

        imageView.setFitHeight(301.0);
        imageView.setFitWidth(460.0);
        imageView.setLayoutX(150.0);
        imageView.setLayoutY(64.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        //imageView.setImage(new Image(getClass().getResource("../resources/gameover.png").toExternalForm()));
        
    
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(600), imageView);
        scaleTransition.setByX(0.5);
        scaleTransition .setCycleCount(50);


        scaleTransition.play();

        
        btn_back.setId("btn_back");
        btn_back.setLayoutX(30.0);
        btn_back.setLayoutY(432.0);
        btn_back.setMnemonicParsing(false);
        btn_back.setOpacity(1);
        btn_back.setPrefHeight(50.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/x_back.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px; -fx-focus-color: transparent;");

        btn_newGame.setId("btn_newGame");
        btn_newGame.setLayoutX(499.0);
        btn_newGame.setLayoutY(432.0);
        btn_newGame.setMnemonicParsing(false);
        btn_newGame.setOpacity(1);
        btn_newGame.setPrefHeight(50.0);
        btn_newGame.setPrefWidth(72.0);
        btn_newGame.setStyle("-fx-background-image: url('resources/o_new.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px; -fx-focus-color: transparent;");

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(btn_back);
        anchorPane.getChildren().add(btn_newGame);
        getChildren().add(anchorPane);

    }
}
