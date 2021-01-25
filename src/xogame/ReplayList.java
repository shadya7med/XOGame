package xogame;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

public class ReplayList extends AnchorPane {

    protected final BorderPane borderPane;
    protected final FlowPane flowPane;
    protected final Pane pane;
    protected final Button btn_back;
    protected final Pane pane0;
    protected final ImageView imageView;
    protected final Label label;
    protected final AnchorPane anchorPane;
    protected final Group group;
    protected final ScrollPane scrollPane;
    protected final VBox vBox;
    protected final Label replayLabel;

    public ReplayList() {

        borderPane = new BorderPane();
        flowPane = new FlowPane();
        pane = new Pane();
        btn_back = new Button();
        pane0 = new Pane();
        imageView = new ImageView("resources/xo.png");
        label = new Label();
        anchorPane = new AnchorPane();
        group = new Group();
        scrollPane = new ScrollPane();
        vBox = new VBox();
        replayLabel = new Label();

        setId("AnchorPane");
        setPrefHeight(520.0);
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
        btn_back.setPrefHeight(50.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/back.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");
        borderPane.setBottom(flowPane);

        BorderPane.setAlignment(pane0, javafx.geometry.Pos.CENTER);

        imageView.setFitHeight(99.0);
        imageView.setFitWidth(104.0);
        imageView.setLayoutX(14.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        //imageView.setImage(new Image(getClass().getResource("../resources/xo.png").toExternalForm()));

        label.setLayoutX(285.0);
        label.setLayoutY(26.0);
        label.setText("Replays");
        label.setFont(new Font(24.0));
        Stop[] stops = new Stop[] { new Stop(0,javafx.scene.paint.Color.valueOf("#187196") ), new Stop(1, javafx.scene.paint.Color.valueOf("#1bab76"))};
        LinearGradient lg1 = new LinearGradient(0, 0, 0.3, 0.5, true, CycleMethod.REPEAT, stops);
        label.setTextFill(lg1);
        borderPane.setTop(pane0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setMaxHeight(USE_PREF_SIZE);
        anchorPane.setMaxWidth(USE_PREF_SIZE);
        anchorPane.setMinHeight(USE_PREF_SIZE);
        anchorPane.setMinWidth(USE_PREF_SIZE);
        anchorPane.setPrefHeight(400.0);
        anchorPane.setPrefWidth(400.0);

        scrollPane.setPrefHeight(460.0);
        scrollPane.setPrefWidth(395.0);

        vBox.setPrefHeight(400.0);
        vBox.setPrefWidth(393.0);
        scrollPane.setContent(vBox);

        replayLabel.setAlignment(javafx.geometry.Pos.CENTER);
        replayLabel.setId("replayLabel");
        replayLabel.setPrefHeight(411.0);
        replayLabel.setPrefWidth(394.0);
        replayLabel.setText("No Games Found");
        replayLabel.setVisible(false);
        replayLabel.setFont(new Font(24.0));
        replayLabel.setTextFill(lg1);
        borderPane.setCenter(anchorPane);

        pane.getChildren().add(btn_back);
        flowPane.getChildren().add(pane);
        pane0.getChildren().add(imageView);
        pane0.getChildren().add(label);
        group.getChildren().add(scrollPane);
        group.getChildren().add(replayLabel);
        anchorPane.getChildren().add(group);
        getChildren().add(borderPane);

    }
}
