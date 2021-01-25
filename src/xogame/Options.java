package xogame;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.Blend;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Options extends AnchorPane {

    protected final BorderPane borderPane;
    protected final FlowPane flowPane;
    protected final Pane pane;
    protected final ImageView imageView;
    protected final Button btn_oneplayer;
    protected final Blend blend;
    protected final Button btn_online;
    protected final Button btn_offline;
    protected final Blend blend0;
    protected final Button replay;
    protected final CheckBox checkBox;
    protected final FlowPane flowPane0;
    protected final Pane pane0;
    protected final Button btn_back;
     protected final Home h;

    public Options() {

        borderPane = new BorderPane();
        flowPane = new FlowPane();
        pane = new Pane();
        imageView = new ImageView("resources/xo.png");
        btn_oneplayer = new Button();
        blend = new Blend();
        btn_online = new Button();
        btn_offline = new Button();
        blend0 = new Blend();
        replay = new Button();
        checkBox = new CheckBox();
        flowPane0 = new FlowPane();
        pane0 = new Pane();
        btn_back = new Button();
        h = new Home();
        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        borderPane.setMaxHeight(USE_PREF_SIZE);
        borderPane.setMaxWidth(USE_PREF_SIZE);
        borderPane.setMinHeight(USE_PREF_SIZE);
        borderPane.setMinWidth(USE_PREF_SIZE);
        borderPane.setPrefHeight(484.0);
        borderPane.setStyle("-fx-background-color: #f2f4f1;");

        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setAlignment(javafx.geometry.Pos.CENTER);
        flowPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane.setPrefHeight(266.0);
        flowPane.setPrefWidth(519.0);
        flowPane.setRowValignment(javafx.geometry.VPos.TOP);
        flowPane.setVgap(25.0);

        imageView.setFitHeight(99.0);
        imageView.setFitWidth(104.0);
        imageView.setLayoutX(-147.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        //imageView.setImage(new Image(getClass().getResource("../resources/xo.png").toExternalForm()));

        btn_oneplayer.setId("btn_oneplayer");
        btn_oneplayer.setMnemonicParsing(false);
        btn_oneplayer.setPrefHeight(38.0);
        btn_oneplayer.setPrefWidth(281.0);
        btn_oneplayer.setStyle("-fx-background-color: #70b48f; -fx-background-radius: 30px;");
        btn_oneplayer.setText("ONE PLAYER");
        btn_oneplayer.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));

        blend.setMode(javafx.scene.effect.BlendMode.OVERLAY);
        btn_oneplayer.setEffect(blend);
        btn_oneplayer.setFont(new Font("System Bold", 18.0));

        btn_online.setId("btn_online");
        btn_online.setMnemonicParsing(false);
        btn_online.setPrefHeight(40.0);
        btn_online.setPrefWidth(285.0);
        btn_online.setStyle("-fx-background-color: #00b2cc; -fx-background-radius: 30px;");
        btn_online.setText("TWO PLAYERS ONLINE");
        btn_online.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        btn_online.setFont(new Font("Arial Bold", 18.0));

        btn_offline.setId("btn_offline");
        btn_offline.setMnemonicParsing(false);
        btn_offline.setPrefHeight(37.0);
        btn_offline.setPrefWidth(281.0);
        btn_offline.setStyle("-fx-background-color: #70b48f; -fx-background-radius: 30px;");
        btn_offline.setText("TWO PLAYERS OFFLINE");
        btn_offline.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));

        blend0.setMode(javafx.scene.effect.BlendMode.OVERLAY);
        btn_offline.setEffect(blend0);
        btn_offline.setFont(new Font("System Bold", 18.0));

        replay.setId("replay");
        replay.setMnemonicParsing(false);
        replay.setPrefHeight(34.0);
        replay.setPrefWidth(283.0);
        replay.setStyle("-fx-background-color: #00b2cc; -fx-background-radius: 30px;");
        replay.setText("REPLAY");
        replay.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        FlowPane.setMargin(replay, new Insets(0.0));
        replay.setFont(new Font("System Bold", 18.0));

        checkBox.setMnemonicParsing(false);
        checkBox.setPrefHeight(34.0);
        checkBox.setPrefWidth(220.0);
        checkBox.setStyle("-fx-background-radius: 20px;");
        //checkBox.getStylesheets().add("/xogame/../../../XO/src/xo/css.css");
        checkBox.setText("Record");
        checkBox.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        checkBox.setFont(new Font("Arial Bold", 18.0));
        BorderPane.setMargin(flowPane, new Insets(0.0));
        borderPane.setCenter(flowPane);

        BorderPane.setAlignment(flowPane0, javafx.geometry.Pos.CENTER);
        flowPane0.setPrefHeight(66.0);
        flowPane0.setPrefWidth(651.0);

        FlowPane.setMargin(pane0, new Insets(15.0, 0.0, 0.0, 10.0));

        btn_back.setId("btn_back");
        btn_back.setMnemonicParsing(false);
        btn_back.setOpacity(1);
        btn_back.setPrefHeight(50.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/back.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");
        borderPane.setBottom(flowPane0);

        pane.getChildren().add(imageView);
        flowPane.getChildren().add(pane);
        flowPane.getChildren().add(btn_oneplayer);
        flowPane.getChildren().add(btn_online);
        flowPane.getChildren().add(btn_offline);
        flowPane.getChildren().add(replay);
        flowPane.getChildren().add(checkBox);
        pane0.getChildren().add(btn_back);
        flowPane0.getChildren().add(pane0);
        getChildren().add(borderPane);

    }

    /**
     * *****************************animation***************************
     */
    void scaleButton(EventHandler<ActionEvent> value, Node n) {
        ParallelTransition parallelTransition;
        RotateTransition rotateTransition;
        rotateTransition = new RotateTransition(Duration.millis(100), n);
        rotateTransition.setByAngle(3);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), n);
        scaleTransition.setByX(0.01);
        parallelTransition = new ParallelTransition(rotateTransition, scaleTransition);

        //h.med.play();
        parallelTransition.setOnFinished(value);
        parallelTransition.play();
    }

    void backAnim(EventHandler<ActionEvent> value, Node n) {
        ParallelTransition parallelTransition;
        RotateTransition rotateTransition;
        rotateTransition = new RotateTransition(Duration.millis(100), n);
        rotateTransition.setByAngle(-3);
        rotateTransition.setAutoReverse(false);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), n);
        scaleTransition.setByX(0.01);
        parallelTransition = new ParallelTransition(rotateTransition, scaleTransition);
        parallelTransition.setAutoReverse(false);
        parallelTransition.setOnFinished(value);
        parallelTransition.play();
    }
}
