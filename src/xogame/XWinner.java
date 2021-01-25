package xogame;

import java.net.URL;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class XWinner extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Button btn_back;
    protected final Button btn_new;
    Winner w;

    public XWinner() {

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        btn_back = new Button();
        btn_new = new Button();
        w = new Winner();
        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #2a3646;");
        //getStyleClass().add("mainFxmlClass");
        //getStylesheets().add("/xogame/winner.css");

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutX(94.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(46.0);
        label.setPrefWidth(412.0);
        label.setText("CONGRATS");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#f2f4f1"));
        label.setFont(new Font("Arial Bold", 49.0));

        label0.setLayoutX(62.0);
        label0.setLayoutY(59.0);
        label0.setPrefHeight(114.0);
        label0.setPrefWidth(63.0);
        label0.setScaleX(2.0);
        label0.setText("X");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#00b2cc"));
        label0.setFont(new Font("Arial Bold", 96.0));

        label1.setLayoutX(482.0);
        label1.setLayoutY(59.0);
        label1.setPrefHeight(114.0);
        label1.setPrefWidth(63.0);
        label1.setScaleX(2.0);
        label1.setText("X");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#00b2cc"));
        label1.setFont(new Font("Arial Bold", 96.0));

        label2.setLayoutX(278.0);
        label2.setLayoutY(228.0);
        label2.setPrefHeight(114.0);
        label2.setPrefWidth(63.0);
        label2.setScaleX(2.0);
        label2.setText("X");
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#00b2cc"));
        label2.setFont(new Font("Arial Bold", 96.0));

        btn_back.setId("btn_back");
        btn_back.setLayoutX(30.0);
        btn_back.setLayoutY(400.0);
        btn_back.setMnemonicParsing(false);
        btn_back.setOpacity(1);
        btn_back.setPrefHeight(80.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/x_back.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px; -fx-focus-color: transparent;");

        btn_new.setId("btn_new");
        btn_new.setLayoutX(506.0);
        btn_new.setLayoutY(400.0);
        btn_new.setMnemonicParsing(false);
        btn_new.setOpacity(1);
        btn_new.setPrefHeight(80.0);
        btn_new.setPrefWidth(72.0);
//        file:/C:/Users/MCC/Downloads/XOGame%20(1)/XOGame/src/resources/x_new.png
        btn_new.setStyle("-fx-background-image: url('resources/x_new.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px; -fx-focus-color: transparent;");

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(btn_back);
        getChildren().add(btn_new);
        /**
         * ************************animation************************
         */
        w.moveO(-180, 220, label1, true);
        w.moveO(180, 220, label0, true);
        w.moveO(0, -150, label2, false);

    }
}
