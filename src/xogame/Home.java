package xogame;

import java.io.File;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Home extends BorderPane {

    protected final FlowPane flowPane;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final FlowPane flowPane0;
    protected final AnchorPane anchorPane;
    protected final ImageView xo_game;
    protected final AnchorPane anchorPane0;
    protected final ImageView imageView;
    protected final AnchorPane anchorPane1;
    protected final ImageView imageView0;
    protected final Pane pane;
    protected final Label label2;
    //protected final    Media aud;
    // protected final    MediaPlayer med;
    
    public Home() {

        flowPane = new FlowPane();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        flowPane0 = new FlowPane();
        anchorPane = new AnchorPane();
        xo_game = new ImageView("resources/xo.png");
        anchorPane0 = new AnchorPane();
        imageView = new ImageView("resources/snake.png");
        anchorPane1 = new AnchorPane();
        imageView0 = new ImageView("resources/checkers.png");
        pane = new Pane();
        label2 = new Label();
        //aud = new Media(new File("src/audio/click.mp3").toURI().toString());
        //med = new MediaPlayer(aud);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #f2f4f1;");

        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        flowPane.setColumnHalignment(javafx.geometry.HPos.CENTER);
        flowPane.setHgap(40.0);
        flowPane.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        flowPane.setPrefHeight(210.0);
        flowPane.setPrefWidth(900.0);
        flowPane.setPrefWrapLength(20.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setPrefHeight(32.0);
        label.setPrefWidth(84.0);
        label.setText("XO");
        label.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        label.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        label.setFont(new Font("Arial Bold", 29.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label0.setPrefHeight(38.0);
        label0.setPrefWidth(270.0);
        label0.setText("Snake and Ladder");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        label0.setFont(new Font("Arial Bold", 24.0));

        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label1.setPrefHeight(23.0);
        label1.setPrefWidth(92.0);
        label1.setText("Checkers");
        label1.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        label1.setFont(new Font("Arial Bold", 18.0));
        FlowPane.setMargin(label1, new Insets(0.0));
        setBottom(flowPane);

        BorderPane.setAlignment(flowPane0, javafx.geometry.Pos.CENTER);
        flowPane0.setPrefHeight(222.0);
        flowPane0.setPrefWidth(600.0);

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        xo_game.setFitHeight(153.0);
        xo_game.setFitWidth(157.0);
        xo_game.setId("xo_game");
        xo_game.setLayoutX(28.0);
        xo_game.setLayoutY(28.0);
        xo_game.setPickOnBounds(true);
        xo_game.setPreserveRatio(true);
//        xo_game.setImage(new Image(getClass().getResource("../resources/xo.png").toExternalForm()));

        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);

        imageView.setFitHeight(120.0);
        imageView.setFitWidth(197.0);
        imageView.setLayoutX(22.0);
        imageView.setLayoutY(55.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
//        imageView.setImage(new Image(getClass().getResource("../resources/snake.png").toExternalForm()));

        anchorPane1.setPrefHeight(200.0);
        anchorPane1.setPrefWidth(200.0);

        imageView0.setFitHeight(137.0);
        imageView0.setFitWidth(131.0);
        imageView0.setLayoutX(35.0);
        imageView0.setLayoutY(62.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        //  imageView0.setImage(new Image(getClass().getResource("../resources/checkers.png").toExternalForm()));
        setCenter(flowPane0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(70.0);
        pane.setPrefWidth(600.0);
        pane.setStyle("-fx-background-color: #70b48f;");
        BorderPane.setMargin(pane, new Insets(0.0, 0.0, 30.0, 0.0));

        label2.setAlignment(javafx.geometry.Pos.CENTER);
        label2.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label2.setLayoutX(100.0);
        label2.setLayoutY(5.0);
        label2.setPrefHeight(46.0);
        label2.setPrefWidth(412.0);
        /*--------------------------------------transition games----------------------*/
        label2.setText("Games");
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1500), label2);
//        translateTransition.setFromX(-100);
//        translateTransition.setToX(120);
  /*******************************animation****************************/
 ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(600), label2);
        scaleTransition.setByX(0.5);
        scaleTransition .setCycleCount(50);

//      translateTransition.setAutoReverse(false);
        scaleTransition .play();
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        label2.setFont(new Font("Arial Bold", 49.0));
        setTop(pane);

        flowPane.getChildren().add(label);
        flowPane.getChildren().add(label0);
        flowPane.getChildren().add(label1);
        anchorPane.getChildren().add(xo_game);
        flowPane0.getChildren().add(anchorPane);
        anchorPane0.getChildren().add(imageView);
        flowPane0.getChildren().add(anchorPane0);
        anchorPane1.getChildren().add(imageView0);
        flowPane0.getChildren().add(anchorPane1);
        pane.getChildren().add(label2);

    }


    void rotateImage(EventHandler<ActionEvent> value,Node n) {
      RotateTransition  rotateTransition = new RotateTransition(Duration.millis(100), n);
        rotateTransition.setByAngle(25);
        rotateTransition.setAutoReverse(false);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), n);
        scaleTransition.setByX(0.3);
     ParallelTransition   parallelTransition = new ParallelTransition(rotateTransition, scaleTransition);
        parallelTransition.setCycleCount(1);
        parallelTransition.setAutoReverse(false);
        parallelTransition.setOnFinished(value);

        parallelTransition.play();
        
    }


}
