package xogame;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    protected final Button btn_back;
    protected final Pane pane0;
    protected final ImageView imageView;
    protected final FlowPane flowPane0;
    protected final Group group;
    protected final Button btn_host;
    protected final ImageView loading_img;
    protected final Group group0;
    protected final Label waiting_label;
    protected final Button btn_guest;
    protected final Blend blend;

    public HostGuest() {

        borderPane = new BorderPane();
        flowPane = new FlowPane();
        pane = new Pane();
        btn_back = new Button();
        pane0 = new Pane();
        imageView = new ImageView("resources/xo.png");
        flowPane0 = new FlowPane();
        group = new Group();
        btn_host = new Button();
        loading_img = new ImageView("resources/img_loading.png");
        group0 = new Group();
        waiting_label = new Label();
        btn_guest = new Button();
        blend = new Blend();

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
        flowPane.setPrefHeight(66.0);
        flowPane.setPrefWidth(651.0);

        FlowPane.setMargin(pane, new Insets(15.0, 0.0, 0.0, 10.0));

        btn_back.setId("btn_back");
        btn_back.setMnemonicParsing(false);
        btn_back.setOpacity(1);
        btn_back.setPrefHeight(50.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/back.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");
        borderPane.setBottom(flowPane);

        BorderPane.setAlignment(pane0, javafx.geometry.Pos.CENTER);

        imageView.setFitHeight(99.0);
        imageView.setFitWidth(104.0);
        imageView.setLayoutX(21.0);
        imageView.setLayoutY(7.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        //imageView.setImage(new Image(getClass().getResource("../resources/xo.png").toExternalForm()));
        borderPane.setTop(pane0);

        BorderPane.setAlignment(flowPane0, javafx.geometry.Pos.CENTER);
        flowPane0.setAlignment(javafx.geometry.Pos.CENTER);
        flowPane0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane0.setPrefHeight(200.0);
        flowPane0.setPrefWidth(200.0);
        flowPane0.setVgap(10.0);

        btn_host.setId("btn_host");
        btn_host.setLayoutX(-1.0);
        btn_host.setLayoutY(-104.0);
        btn_host.setMnemonicParsing(false);
        btn_host.setPrefHeight(40.0);
        btn_host.setPrefWidth(285.0);
        btn_host.setStyle("-fx-background-color: #00b2cc; -fx-background-radius: 30px;");
        btn_host.setText("Host");
        btn_host.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        btn_host.setFont(new Font("Arial Rounded MT Bold", 30.0));

        loading_img.setFitHeight(142.0);
        loading_img.setFitWidth(164.0);
        loading_img.setId("loading_img");
        loading_img.setLayoutX(140.0);
        loading_img.setLayoutY(-175.0);
        loading_img.setPickOnBounds(true);
        loading_img.setPreserveRatio(true);
        loading_img.setVisible(false);
        //loading_img.setImage(new Image(getClass().getResource("../resources/img_loading.png").toExternalForm()));

        waiting_label.setId("waiting_label");
        waiting_label.setLayoutX(117.0);
        waiting_label.setLayoutY(49.0);
        waiting_label.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));
        waiting_label.setFont(new Font("Arial Rounded MT Bold", 26.0));

        btn_guest.setAlignment(javafx.geometry.Pos.CENTER);
        btn_guest.setId("btn_guest");
        btn_guest.setLayoutY(29.0);
        btn_guest.setMnemonicParsing(false);
        btn_guest.setPrefHeight(37.0);
        btn_guest.setPrefWidth(281.0);
        btn_guest.setStyle("-fx-background-color: #70b48f; -fx-background-radius: 30px;");
        btn_guest.setText("Guest");
        btn_guest.setTextFill(javafx.scene.paint.Color.valueOf("#2a3646"));

        blend.setMode(javafx.scene.effect.BlendMode.OVERLAY);
        btn_guest.setEffect(blend);
        btn_guest.setFont(new Font("Arial Rounded MT Bold", 30.0));
        borderPane.setCenter(flowPane0);

        pane.getChildren().add(btn_back);
        flowPane.getChildren().add(pane);
        pane0.getChildren().add(imageView);
        group.getChildren().add(btn_host);
        group.getChildren().add(loading_img);
        flowPane0.getChildren().add(group);
        group0.getChildren().add(waiting_label);
        group0.getChildren().add(btn_guest);
        flowPane0.getChildren().add(group0);
        getChildren().add(borderPane);

    }
}
