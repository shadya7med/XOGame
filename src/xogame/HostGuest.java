package xogame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class HostGuest extends AnchorPane {

    protected final BorderPane borderPane;
    protected final FlowPane flowPane;
    protected final Pane pane;
    protected final ImageView imageView;
    protected final Button btn_host;
    protected final Button btn_guest;
    protected final Blend blend;
    protected final FlowPane flowPane0;
    protected final Pane pane0;
    protected final Button btn_back;

    public HostGuest() {

        borderPane = new BorderPane();
        flowPane = new FlowPane();
        pane = new Pane();
        imageView = new ImageView();
        btn_host = new Button();
        btn_guest = new Button();
        blend = new Blend();
        flowPane0 = new FlowPane();
        pane0 = new Pane();
        btn_back = new Button();

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
        imageView.setImage(new Image(getClass().getResource("../resources/xo.png").toExternalForm()));

        btn_host.setId("btn_host");
        btn_host.setMnemonicParsing(false);
        btn_host.setPrefHeight(40.0);
        btn_host.setPrefWidth(285.0);
        btn_host.setStyle("-fx-background-color: #00b2cc; -fx-background-radius: 30px;");
        btn_host.setText("Host");
        btn_host.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        btn_host.setFont(new Font("Arial Rounded MT Bold", 30.0));

        btn_guest.setId("btn_guest");
        btn_guest.setMnemonicParsing(false);
        btn_guest.setPrefHeight(37.0);
        btn_guest.setPrefWidth(281.0);
        btn_guest.setStyle("-fx-background-color: #70b48f; -fx-background-radius: 30px;");
        btn_guest.setText("Guest");
        btn_guest.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));

        blend.setMode(javafx.scene.effect.BlendMode.OVERLAY);
        btn_guest.setEffect(blend);
        btn_guest.setFont(new Font("Arial Narrow Bold", 30.0));
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
        flowPane.getChildren().add(btn_host);
        flowPane.getChildren().add(btn_guest);
        pane0.getChildren().add(btn_back);
        flowPane0.getChildren().add(pane0);
        getChildren().add(borderPane);

    }
}
