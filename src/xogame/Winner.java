package xogame;

import java.io.File;
import java.net.URL;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Winner extends AnchorPane {

    protected final Circle circle;
    protected final Circle circle0;
    protected final Circle circle1;
    protected final Label label;
    protected final Button btn_back;
     protected final Button btn_new;
    protected final Blend blend;
  //protected final Media aud;
  //protected final MediaPlayer med;
    public Winner() {

        circle = new Circle();
        circle0 = new Circle();
        circle1 = new Circle();
        label = new Label();
       btn_back = new Button();
       btn_new=new Button();
        blend = new Blend();

  //aud = new Media(new File("src/audio/win.mp3").toURI().toString());

      //med = new MediaPlayer(aud);
        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #2a3646;");
        //getStyleClass().add("mainFxmlClass");
        //getStylesheets().add("/xogame/winner.css");

        circle.setFill(javafx.scene.paint.Color.valueOf("#6fb08c00"));
        circle.setLayoutX(520.0);
        circle.setLayoutY(101.0);
        circle.setRadius(66.0);
        circle.setStroke(javafx.scene.paint.Color.valueOf("#70b48f"));
        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        circle.setStrokeWidth(20.0);

        circle0.setFill(javafx.scene.paint.Color.valueOf("#6fb08c00"));
        circle0.setLayoutX(300.0);
        circle0.setLayoutY(296.0);
        circle0.setRadius(66.0);
        circle0.setStroke(javafx.scene.paint.Color.valueOf("#70b48f"));
        circle0.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        circle0.setStrokeWidth(20.0);

        circle1.setFill(javafx.scene.paint.Color.valueOf("#6fb08c00"));
        circle1.setLayoutX(91.0);
        circle1.setLayoutY(101.0);
        circle1.setRadius(66.0);
        circle1.setStroke(javafx.scene.paint.Color.valueOf("#70b48f"));
        circle1.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        circle1.setStrokeWidth(20.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutX(94.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(46.0);
        label.setPrefWidth(412.0);
        label.setText("CONGRATS");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#f2f4f1"));
        label.setFont(new Font("Arial Bold", 49.0));

  btn_back.setId("btn_back");
        btn_back.setLayoutX(30.0);
        btn_back.setLayoutY(400.0);
        btn_back.setMnemonicParsing(false);
        btn_back.setOpacity(1);
        btn_back.setPrefHeight(80.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/o_back.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px; -fx-focus-color: transparent;");

        btn_new.setId("btn_new");
        btn_new.setLayoutX(506.0);
        btn_new.setLayoutY(400.0);
        btn_new.setMnemonicParsing(false);
        btn_new.setOpacity(1);
        btn_new.setPrefHeight(80.0);
        btn_new.setPrefWidth(72.0);
        btn_new.setStyle("-fx-background-image: url('resources/o_new.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px; -fx-focus-color: transparent;");
        blend.setMode(javafx.scene.effect.BlendMode.OVERLAY);
      // btn_back.setEffect(blend);

        getChildren().add(circle);
        getChildren().add(circle0);
        getChildren().add(circle1);
        getChildren().add(label);
        getChildren().add(btn_back);
         getChildren().add(btn_new);
        TranslateTransition t = new TranslateTransition(Duration.millis(1000), circle);
        t.setToX(-180);
        t.setToY(220);
        t.setAutoReverse(true);
        t.setCycleCount(50);
        t.play();

        moveO(-180, 220, circle, true);
        moveO(180, 220, circle1, true);
        moveO(0, -150, circle0, false);
        
    }

    void moveO(double x, double y, Node n, boolean toy) {
         
        TranslateTransition t = new TranslateTransition(Duration.millis(700), n);

        t.setToY(y);
        if (toy) {

            t.setToX(x);
        }
        t.setAutoReverse(true);
        t.setCycleCount(100);
       
     
        t.play();
         

    }
}
