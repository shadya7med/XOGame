package xogame;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class Game extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Pane pane;
    protected final ImageView imageView;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button btn00;
    protected final Button btn01;
    protected final Button btn20;
    protected final Button btn10;
    protected final Button btn11;
    protected final Button btn21;
    protected final Button btn02;
    protected final Button btn12;
    protected final Button btn22;
    protected final Button btn_back;
    protected ImageView x_img ;
    protected ImageView o_img ;
     protected final Label label;
    protected final Label label0;
    protected final ImageView imageView0;
    protected final Label label1;
    protected final ImageView imageView1;
     protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final Label label2;


    public Game() {

        anchorPane = new AnchorPane();
        pane = new Pane();
        imageView = new ImageView("resources/grid.png");
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        btn00 = new Button();
        btn01 = new Button();
        btn20 = new Button();
        btn10 = new Button();
        btn11 = new Button();
        btn21 = new Button();
        btn02 = new Button();
        btn12 = new Button();
        btn22 = new Button();
        btn_back = new Button();
          label = new Label();
        label0 = new Label();
        imageView0 = new ImageView();
        label1 = new Label();
        imageView1 = new ImageView("resources/x.png");
        imageView2 = new ImageView();
        imageView3 = new ImageView("resources/o.png");
        label2 = new Label();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        anchorPane.setMaxHeight(USE_PREF_SIZE);
        anchorPane.setMaxWidth(USE_PREF_SIZE);
        anchorPane.setMinHeight(USE_PREF_SIZE);
        anchorPane.setMinWidth(USE_PREF_SIZE);
        anchorPane.setPrefHeight(590.0);
        anchorPane.setPrefWidth(660.0);
        anchorPane.setStyle("-fx-background-color: #f2f4f1;");

        pane.setLayoutX(85.0);
        pane.setLayoutY(40.0);

        imageView.setFitHeight(519.0);
        imageView.setFitWidth(449.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        //imageView.setImage(new Image(getClass().getResource("../resources/grid.png").toExternalForm()));

        gridPane.setPrefHeight(449.0);
        gridPane.setPrefWidth(442.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        btn00.setId("btn00");
        btn00.setMnemonicParsing(false);
        btn00.setOpacity(0);
        btn00.setPrefHeight(150.0);
        btn00.setPrefWidth(142.0);
        btn00.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px; -fx-focus-color: transparent;");

        GridPane.setRowIndex(btn01, 1);
        btn01.setId("btn01");
        btn01.setMnemonicParsing(false);
        btn01.setOpacity(0);
        btn01.setPrefHeight(142.0);
        btn01.setPrefWidth(144.0);
        btn01.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        GridPane.setColumnIndex(btn20, 2);
        btn20.setId("btn20");
        btn20.setMnemonicParsing(false);
        btn20.setOpacity(0);
        btn20.setPrefHeight(145.0);
        btn20.setPrefWidth(149.0);
        btn20.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        GridPane.setColumnIndex(btn10, 1);
        btn10.setId("btn10");
        btn10.setMnemonicParsing(false);
        btn10.setOpacity(0);
        btn10.setPrefHeight(150.0);
        btn10.setPrefWidth(141.0);
        btn10.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        GridPane.setColumnIndex(btn11, 1);
        GridPane.setRowIndex(btn11, 1);
        btn11.setId("btn11");
        btn11.setMnemonicParsing(false);
        btn11.setOpacity(0);
        btn11.setPrefHeight(141.0);
        btn11.setPrefWidth(141.0);
        btn11.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        GridPane.setColumnIndex(btn21, 2);
        GridPane.setRowIndex(btn21, 1);
        btn21.setId("btn21");
        btn21.setMnemonicParsing(false);
        btn21.setOpacity(0);
        btn21.setPrefHeight(141.0);
        btn21.setPrefWidth(163.0);
        btn21.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        GridPane.setRowIndex(btn02, 2);
        btn02.setId("btn02");
        btn02.setMnemonicParsing(false);
        btn02.setOpacity(0);
        btn02.setPrefHeight(138.0);
        btn02.setPrefWidth(144.0);
        btn02.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        GridPane.setColumnIndex(btn12, 1);
        GridPane.setRowIndex(btn12, 2);
        btn12.setId("btn12");
        btn12.setMnemonicParsing(false);
        btn12.setOpacity(0);
        btn12.setPrefHeight(142.0);
        btn12.setPrefWidth(144.0);
        btn12.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        GridPane.setColumnIndex(btn22, 2);
        GridPane.setRowIndex(btn22, 2);
        btn22.setId("btn22");
        btn22.setMnemonicParsing(false);
        btn22.setOpacity(0);
        btn22.setPrefHeight(140.0);
        btn22.setPrefWidth(161.0);
        btn22.setStyle("-fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

        btn_back.setId("btn_back");
        btn_back.setLayoutX(14.0);
        btn_back.setLayoutY(460.0);
        btn_back.setMnemonicParsing(false);
        btn_back.setOpacity(1);
        btn_back.setPrefHeight(50.0);
        btn_back.setPrefWidth(72.0);
        btn_back.setStyle("-fx-background-image: url('resources/back.png'); -fx-background-size: 100% 100%; -fx-border-image-repeat: no-repeat; -fx-background-radius: 15px;-fx-focus-color: transparent;");

         label.setLayoutX(28.0);
        label.setLayoutY(1.0);

        label0.setLayoutX(290.0);
        label0.setLayoutY(4.0);
        label0.setPrefHeight(33.0);
        label0.setPrefWidth(49.0);
        label0.setText(" VS");
        label0.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#e11b1b"));
        label0.setFont(new Font("System Bold", 24.0));

        imageView0.setFitHeight(43.0);
        imageView0.setFitWidth(72.0);
        imageView0.setLayoutX(14.0);
        imageView0.setLayoutY(15.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);

        label1.setLayoutX(97.0);
        label1.setLayoutY(4.0);
        label1.setPrefHeight(43.0);
        label1.setPrefWidth(87.0);
        label1.setText("Player 1 ");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#18baf0"));
        label1.setFont(new Font("System Bold", 18.0));

        imageView1.setFitHeight(43.0);
        imageView1.setFitWidth(49.0);
        imageView1.setLayoutX(37.0);
        imageView1.setLayoutY(4.0);
        //imageView1.setImage(new Image(getClass().getResource("resources/x.png").toExternalForm()));
        
        imageView2.setFitHeight(43.0);
        imageView2.setFitWidth(72.0);
        imageView2.setLayoutX(550.0);
        imageView2.setLayoutY(4.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);

        imageView3.setFitHeight(43.0);
        imageView3.setFitWidth(49.0);
        imageView3.setLayoutX(530.0);
        imageView3.setLayoutY(4.0);
        //imageView3.setImage(new Image(getClass().getResource("resources/o.png").toExternalForm()));
 label2.setLayoutX(450.0);
        label2.setLayoutY(8.0);
        label2.setPrefHeight(35.0);
        label2.setPrefWidth(72.0);
        label2.setText("Player 2");
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#1aab24"));
        label2.setFont(new Font("System Bold", 18.0));
        
        pane.getChildren().add(imageView);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(btn00);
        gridPane.getChildren().add(btn01);
        gridPane.getChildren().add(btn20);
        gridPane.getChildren().add(btn10);
        gridPane.getChildren().add(btn11);
        gridPane.getChildren().add(btn21);
        gridPane.getChildren().add(btn02);
        gridPane.getChildren().add(btn12);
        gridPane.getChildren().add(btn22);
        pane.getChildren().add(gridPane);
        anchorPane.getChildren().add(pane);
        anchorPane.getChildren().add(btn_back);
         anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(imageView1);
          anchorPane.getChildren().add(imageView2);
        anchorPane.getChildren().add(imageView3);
        anchorPane.getChildren().add(label2);
        getChildren().add(anchorPane);
        

    }
}
