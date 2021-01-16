package xogame;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

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
    protected final Label label2;

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
        label2 = new Label();

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
        flowPane.setPrefHeight(110.0);
        flowPane.setPrefWidth(600.0);
        flowPane.setPrefWrapLength(20.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setPrefHeight(32.0);
        label.setPrefWidth(84.0);
        label.setText("XO");
        label.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        label.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        label.setFont(new Font("Arial Bold", 14.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label0.setPrefHeight(31.0);
        label0.setPrefWidth(247.0);
        label0.setText("Snake and Ladder");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        label0.setFont(new Font("Arial Bold", 15.0));

        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(71.0);
        label1.setText("Checkers");
        label1.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        label1.setFont(new Font("Arial Bold", 15.0));
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

        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);

        imageView.setFitHeight(120.0);
        imageView.setFitWidth(197.0);
        imageView.setLayoutX(22.0);
        imageView.setLayoutY(55.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        anchorPane1.setPrefHeight(200.0);
        anchorPane1.setPrefWidth(200.0);

        imageView0.setFitHeight(137.0);
        imageView0.setFitWidth(131.0);
        imageView0.setLayoutX(35.0);
        imageView0.setLayoutY(62.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        setCenter(flowPane0);

        BorderPane.setAlignment(label2, javafx.geometry.Pos.CENTER);
        label2.setPrefHeight(57.0);
        label2.setPrefWidth(269.0);
        label2.setText("Entertainment Games");
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        BorderPane.setMargin(label2, new Insets(40.0, 0.0, 30.0, 0.0));
        label2.setFont(new Font("Arial Bold", 26.0));
        setTop(label2);

        flowPane.getChildren().add(label);
        flowPane.getChildren().add(label0);
        flowPane.getChildren().add(label1);
        anchorPane.getChildren().add(xo_game);
        flowPane0.getChildren().add(anchorPane);
        anchorPane0.getChildren().add(imageView);
        flowPane0.getChildren().add(anchorPane0);
        anchorPane1.getChildren().add(imageView0);
        flowPane0.getChildren().add(anchorPane1);

    }
}
