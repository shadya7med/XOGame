/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.ws.handler.MessageContext;
import static xogame.SnakeLogic.DOWN;
import static xogame.SnakeLogic.HEIGHT;
import static xogame.SnakeLogic.LEFT;
import static xogame.SnakeLogic.RIGHT;
import static xogame.SnakeLogic.ROWS;
import static xogame.SnakeLogic.UP;
import static xogame.SnakeLogic.WIDTH;

/**
 *
 * @author win.7
 */
public class XOGame extends Application {
    
    Scene homeScene, optionsScene, gameScene, hostGuestScene, snakeScene,gameoverSnakeScene, xScene, winnerScene;
    Scene replayListScene,tieScene;
    Stage window;
    DbHandler dbHandler ; 
    GameLogic glc ;
    Game g;
    Options o ;
    Home h ;
    HostGuest hg;
    SnakeLogic s;
    ReplayList r;
    GameOverSnake gameOver_snake;
    Canvas canvas ;
    Group root;
       XWinner x_win;
       Winner w;
       xoGameOver tie ;
    private int gameId; 
    private boolean recorded ;
    private boolean online ;
    private boolean isReplay;
    private boolean isClicked ; 
    private int row = 0;
    private ArrayList<String> gameTurns ;        
    private ConnectionHandler conHandler ;
    private Thread serverTh,clientTh,serverInitTh,clientInitTh;
    private String appNetworkMode ;
    private String clientInput,serverInput ;
    private PauseTransition[] delay ;
    private String mode ;
    private int delayInc ;
    Timeline timeline ;
    private ArrayList<Integer> replayList ;
    private Button replayListButton;
    
    //static boolean replay;
    @Override
    public void init(){
        dbHandler = new DbHandler();
        dbHandler.loadSQLDriver();
        
        glc = new GameLogic(); 
        conHandler = new ConnectionHandler();
        
        s=new SnakeLogic();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        window = stage;
        o=new Options();
        g=new Game();
        h=new Home();
        hg=new HostGuest();
        x_win = new XWinner();
        w = new Winner();
        tie = new xoGameOver();
        r = new ReplayList();
        gameOver_snake=new GameOverSnake();
        recorded =false ;
        online = false ;
        mode = "None";
        delay = new PauseTransition[10];
        isClicked = true ;
        homeScene = new Scene(h, 600, 520);
        optionsScene = new Scene(o, 600, 520);
        gameScene = new Scene(g, 600, 520);
        hostGuestScene = new Scene(hg,600,520);
        replayListScene = new Scene(r,600,520);
         xScene = new Scene(x_win, 600, 520);
          winnerScene = new Scene(w, 600, 520);
          tieScene = new Scene(tie,600,520);
        gameoverSnakeScene = new Scene(gameOver_snake,600,540);
        
        //snakeScene
        root = new Group();
        canvas = new Canvas(WIDTH,HEIGHT);
        root.getChildren().add(canvas);
        snakeScene = new Scene(root);
        
        
        //------------- Setting Buttons Actions------------------//
       
        g.btn00.setOnAction((ActionEvent e)->{
            
            //g.gridPane.getChildren().remove(g.btn00);
            turnPlay(0, 0);
            
        });
        g.btn01.setOnAction((ActionEvent e)->{
            //g.gridPane.getChildren().remove(g.btn01);
            turnPlay(0, 1);
          
        });
        g.btn02.setOnAction(e->{
            
            //g.gridPane.getChildren().remove(g.btn02);
            turnPlay(0, 2);
            
        });
        g.btn10.setOnAction((ActionEvent e)->{
            
            //g.gridPane.getChildren().remove(g.btn10);
            turnPlay(1, 0);
           
        });
        g.btn20.setOnAction((ActionEvent e)->{
              
            //g.gridPane.getChildren().remove(g.btn20);
            turnPlay(2, 0);
        });
        g.btn11.setOnAction((ActionEvent e)->{
            
            //g.gridPane.getChildren().remove(g.btn11);
            turnPlay(1, 1);
                       
        });
        g.btn12.setOnAction((ActionEvent e)->{
            
            //g.gridPane.getChildren().remove(g.btn12);
            turnPlay(1, 2);
            
        });
        g.btn22.setOnAction((ActionEvent e)->{
            
            //g.gridPane.getChildren().remove(g.btn22);
            turnPlay(2, 2);
                        
        });
        g.btn21.setOnAction((ActionEvent e)->{
            
            //g.gridPane.getChildren().remove(g.btn21);
            turnPlay(2, 1);            
        });
        
        //-------------------opening snake game--------------------//
        h.imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                h.rotateImage((k) -> {
                    //h.med.play();
                    window.setScene(snakeScene);
                    
                },h.imageView);
                playSnakeScene();
            }
        });
          h.xo_game.setOnMouseMoved((e) -> homeScene.setCursor(Cursor.HAND));
        //-------------------opening xo game--------------------//
        h.xo_game.setOnMousePressed((MouseEvent e) -> {
            /**
             * *****************************animation***************************
             */
            /*h.rotateImage((k) -> {
                h.med.play();
                window.setScene(optionsScene);
                
            },h.xo_game);*/
            window.setScene(optionsScene);

        });
        
            h.xo_game.setOnMouseMoved((e) -> homeScene.setCursor(Cursor.HAND));
        o.checkBox.setOnAction((e)->{
            //isRecorded
            recorded = o.checkBox.isSelected();
        });
        o.btn_oneplayer.setOnAction((ActionEvent e) -> {
            //unlock mouseEvents
            isReplay = false ;
            //terminate current Game
            //terminateCurrentGame();
            if(recorded)
            {
                dbHandler.startCon();
                //get the Starting gameId
                gameId = dbHandler.getGameId();
                //start Insert Statement
                dbHandler.startInsert();
            }
            //load Grid image

        /* o.scaleButton((k) -> {

                window.setScene(gameScene);

            }, o.btn_oneplayer);*/
        window.setScene(gameScene);
//               
                g.label1.setText("Player");
                
                g.label2.setText("Robot");
                g.label2.setLayoutX(470);
            
        //Create 2-Player Offline Game
        glc.newGame(false);
            //
        // replay = false;
         
        //set mode
        mode = "SinglePlayer";
            
        });
       o.btn_online.setOnAction((ActionEvent e) -> {
            /**
             * *****************************animation***************************
             */
            /*o.scaleButton((k) -> {
                window.setScene(hostGuestScene);
                
            }, o.btn_online);*/
            window.setScene(hostGuestScene);
        });
        
        o.btn_offline.setOnAction((ActionEvent e) -> {
            //unlock mouseEvents
            isReplay = false ;
            //terminate current Game
            //terminateCurrentGame();
            if(recorded)
            {
                dbHandler.startCon();
                //get the Starting gameId
                gameId = dbHandler.getGameId();
                //start Insert Statement
                dbHandler.startInsert();
            }
            //load Grid image
            /*o.scaleButton((k) -> {
                window.setScene(gameScene);

            }, o.btn_offline);*/
            window.setScene(gameScene); 
            
            
            //Create 2-Player Offline Game
            glc.newGame(true);
            //
            //replay = false;
        //set mode
        mode = "TwoPlayerOffline";
        });
        
        o.replay.setOnAction(e ->{
            dbHandler.startCon();
            replayList = dbHandler.getDistinctGameId() ;
            dbHandler.stopCon();
            if(replayList.size() == 0)
            {
                //System.out.println("size:0");
                r.replayLabel.setVisible(true);
            }else{
                for(int index:replayList)
                {
                replayListButton = new Button();
                replayListButton.setMnemonicParsing(false);
                replayListButton.setPrefHeight(35.0);
                replayListButton.setPrefWidth(499.0);
                replayListButton.setText(String.valueOf(index));
                replayListButton.setOnAction((event) -> {
                    //unlock replay mode
                    isReplay = true ;
                    //turn recording off
                    recorded = false ;
                    //terminate current Game
                    //terminateCurrentGame();
                    //load Grid image
                    /*o.scaleButton((k) -> {
                    window.setScene(gameScene);

                    }, o.replay);*/ 
                    window.setScene(gameScene);
            
                    //Create new Replay
                    glc.newReplay(true);
            
                    //replay = true;
                    //start DB Conn
                    dbHandler.startCon();
                    //get game Turns
                    gameTurns = dbHandler.getGameMoves(index);
                    //System.out.println("game turns:"+gameTurns.size());
                    //stop DB Conn
                    dbHandler.stopCon();
                    //make  the buttons invisible
                    hideAllButtons();
                    //play the selected game
                    delayInc = 1 ;
                    //play the selected game
                    for(String move:gameTurns)
                    {
                        delay[delayInc  - 1] = new PauseTransition(Duration.seconds(delayInc));
                        delay[delayInc  - 1].setOnFinished( e2 -> fireButton(move.substring(1)) );
                        delay[delayInc  - 1].play();
                        delayInc ++ ;
                    }
                    //add andimation for result
                    //System.out.println("Ok");
                    //set mode
                    mode = "Replay";
                    
                });
                r.vBox.getChildren().add(replayListButton);
                
                }
            }
            
            window.setScene(replayListScene);
        });
        hg.btn_host.setOnAction(e->{
            appNetworkMode = "host";
            window.setScene(hostGuestScene);
            //set Buttons Visibility
            hg.btn_guest.setVisible(false);
            hg.btn_host.setVisible(false);
            //set Loading image and label visible
            hg.loading_img.setVisible(true);
            hg.waiting_label.setText("Waiting for Guest");
            serverInitTh = new Thread(){ 
            @Override
                public void run(){
                    conHandler.serverInitBlocking(8600);
                    //online confirmation flag
                     online = true;
                    //unlock mouseEvents
                    isReplay = false ;
                    //terminate current Game
                    //terminateCurrentGame();
                    if(recorded)
                    {
                     dbHandler.startCon();
                    //get the Starting gameId
                    gameId = dbHandler.getGameId();
                    //start Insert Statement
                    dbHandler.startInsert();
                    }
                    //Create 2-Player Game
                    glc.newGame(true);
                    
                    Platform.runLater(() -> {
                        //load Grid image
                        /*o.scaleButton((k) -> {
                            window.setScene(gameScene);
                            
                        }, hg.btn_host);*/
                        window.setScene(gameScene);
                        
                        g.label1.setText("Host");
                        
                        g.label2.setText("Guest");
                        g.label2.setLayoutX(475);
                    });
                    //set mode
                    mode = "TwoPlayerHost";
                    //start reading thread for server
                    serverTh = new Thread()
                    {
                    @Override
                    public void run(){
                    
                    while(true)
                    {
                        
                        serverInput = conHandler.readServerBlocking();
                        
                        if(serverInput!=null){
                        if(serverInput.equals("close"))
                         {
                         //System.out.println("Client closed");
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                         //fire back_btn
                         g.btn_back.fire();
                         }});
                         }else{
                         Platform.runLater(new Runnable() {
                         @Override
                        public void run() {
                             //System.out.println(serverInput);
                             isClicked = false ;
                         //fire button corresponding to client move   
                         fireButton(serverInput);
                         //allow client to play
                         showAllButtons();
                         isClicked = true ;
                        }});
                         }
                        }
                
                      }
                    }
            
                    };
                    serverTh.start();
                    
                    
                    }};
            serverInitTh.start();
            
        });
        hg.btn_guest.setOnAction(e->{
            appNetworkMode ="guest";
            window.setScene(hostGuestScene);
            //setting buttons visibility
            hg.btn_guest.setVisible(false);
            hg.btn_host.setVisible(false);
            //set Loading image and label visible
            hg.loading_img.setVisible(true);
            hg.waiting_label.setText("Waiting for Host");
            clientInitTh = new Thread(){ 
            @Override
                public void run(){
                     conHandler.clientInitBlocking("127.0.0.1",8600);
                     //online confirmation flag
                     online = true;
                     //unlock mouseEvents
                    isReplay = false ;
                    //terminate current Game
                    //terminateCurrentGame();
                    if(recorded)
                    {
                     dbHandler.startCon();
                    //get the Starting gameId
                    gameId = dbHandler.getGameId();
                    //start Insert Statement
                    dbHandler.startInsert();
                    }
                    //Create 2-Player Game
                    glc.newGame(true);
                    
                    Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                    //load Grid image
                     /*o.scaleButton((k) -> 
                            {
                                window.setScene(gameScene);

                            }, hg.btn_guest);*/
                     
                       window.setScene(gameScene);
                       
                       g.label1.setText("Host");
                       g.label2.setText("Guest");
       
                    }});
                    //set mode
                    mode = "TwoPlayerGuest";
                    
                    hideAllButtons();
                    //start reading thread for client
                    clientTh = new Thread()
                    {
                    @Override
                    public void run(){
                    
                    while(true)
                    {
                        
                        clientInput = conHandler.readClientBlocking();
                        //System.out.println(clientInput);
                        if(clientInput!=null){
                        if(clientInput.equals("close"))
                         {
                         //System.out.println("Server closed");
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                         //fire back_btn
                         g.btn_back.fire();
                         }});
                         }else{
                            
                            Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //System.out.println(clientInput);
                            isClicked = false ;
                            //fire button corresponding to server move   
                            fireButton(clientInput);
                            //allow client to play
                            showAllButtons();
                            isClicked = true ;
                        }});
                         }
                        }
                
                      }
                    }
            
                    };
                    clientTh.start();
                    
                    
                    
                    
                    }};
            clientInitTh.start();
            
            
        });
        //-------------------new Buttons Actions--------------------//
        //X Wins
        x_win.btn_new.setOnAction(e->{
           // w.med.stop();
           //get flags State
           String temp = mode ;
           boolean tempRecorded = recorded;
           //restart the game
           terminateCurrentGame();
           //set flags State
           recorded = tempRecorded ;
           //fire corresponding mode
           switch(temp)
           {
               case "SinglePlayer":o.btn_oneplayer.fire();break;
               case "TwoPlayerOffline":o.btn_offline.fire();break;
               case "TwoPlayerHost":hg.btn_host.fire();break;
               case "TwoPlayerGuest":hg.btn_guest.fire();break;
               case "Replay":o.replay.fire();break;    
                   
           }
           //w.med.stop();
        });
        //O Wins
        w.btn_new.setOnAction(e->{
           // w.med.stop();
           //get flags State
           String temp = mode ;
           boolean tempRecorded = recorded;
           //restart the game
           terminateCurrentGame();
           //set flags State
           recorded = tempRecorded ;
           //fire corresponding mode
           switch(temp)
           {
               case "SinglePlayer":o.btn_oneplayer.fire();break;
               case "TwoPlayerOffline":o.btn_offline.fire();break;
               case "TwoPlayerHost":hg.btn_host.fire();break;
               case "TwoPlayerGuest":hg.btn_guest.fire();break;
               case "Replay":o.replay.fire();break;    
                   
           }
           
        });
        //TIE
        tie.btn_newGame.setOnAction(e->{
          //get flags State
           String temp = mode ;
           boolean tempRecorded = recorded;
           //restart the game
           terminateCurrentGame();
           //set flags State
           recorded = tempRecorded ;
           //fire corresponding mode
           switch(temp)
           {
               case "SinglePlayer":o.btn_oneplayer.fire();break;
               case "TwoPlayerOffline":o.btn_offline.fire();break;
               case "TwoPlayerHost":hg.btn_host.fire();break;
               case "TwoPlayerGuest":hg.btn_guest.fire();break;
               case "Replay":o.replay.fire();break;    
                   
           }
             
        });
        //-------------------back Buttons Actions--------------------//
        o.btn_back.setOnAction((ActionEvent e) -> {
            
            window.setScene(homeScene);
        });
         x_win.btn_back.setOnAction((ActionEvent e) -> { 
            //terminate current Game
            terminateCurrentGame();
            window.setScene(optionsScene);
             //System.out.println("X win back pressed");
           // w.med.stop();

        });
        w.btn_back.setOnAction((ActionEvent e) -> {
            //terminate current Game
            terminateCurrentGame();

            window.setScene(optionsScene);

        });
        tie.btn_back.setOnAction(e->{
            //terminate current Game
            terminateCurrentGame();

            window.setScene(optionsScene);
        });
        g.btn_back.setOnAction((ActionEvent e) -> {
            //terminate current Game
            terminateCurrentGame();
            //
            window.setScene(optionsScene);
            //System.out.println("game back button pressed");
        });
        hg.btn_back.setOnAction(e ->{
            if(serverInitTh != null)
                {
                //System.out.println("server intit stopping");
                serverInitTh.stop();
                //System.out.println("server intit stopped");
                }
                if(serverTh != null)
                {
                //System.out.println("client read stopping");
                serverTh.stop();  
                //System.out.println("client read stopped");
                }
                    
            if(clientInitTh != null)
                {
                clientInitTh.stop();
                }
                if(clientTh != null)
                {
                clientTh.stop();       
                }
          
            conHandler.stopServer();
               
            conHandler.stopClient();
            
            //set Buttons Visibility 
            hg.btn_guest.setVisible(true);
            hg.btn_host.setVisible(true);
            //hide Loading image and label
            hg.loading_img.setVisible(false);
            hg.waiting_label.setText("");
            
            window.setScene(optionsScene);
        });
        r.btn_back.setOnAction(e->{
            //reset All values
            terminateCurrentGame();
            //set OptionsScene
            window.setScene(optionsScene);
        });
        //-------------------Snake game btn actions --------------------//
        gameOver_snake.btn_back.setOnAction(e ->{
            
            window.setScene(homeScene);
        });
        
        gameOver_snake.btn_newGame.setOnAction(e ->{
            
            s.stopGame();
            window.setScene(snakeScene);
            playSnakeScene();
        });
        
        //-------------------show Home Screen--------------------//
        window.setScene(homeScene);
        window.setResizable(false);
        
        window.show();
       
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void stop(){
        if(serverInitTh != null)
            {
            serverInitTh.stop();
            //System.out.println("server stopped 1");
            }
        if(serverTh != null)
            {
            serverTh.stop();
            //System.out.println("server stopped 2");
            }
                    
        if(clientInitTh != null)
            {
            clientInitTh.stop();
            }
        if(clientTh != null)
            {
            clientTh.stop();       
            }
        if(online)
        {
            switch(appNetworkMode)
            {
                case "host":
                    conHandler.stopServer();
                    
                    break;
                case "guest":
                    conHandler.stopClient();
                   
                    break;
            }
        }   
        /*if(recorded)
        {
            dbHandler.stopCon();
        }*/
        //System.out.println("stop called");
    }
    
    public void turnPlay(int i,int j)
    {
            String move = "";
            String result =""; 
            String playerName ="";
            boolean gameEnd = false ; 
            char curTurn = glc.getTurn();
            //update grid
            glc.playAt(i, j);
            //update Ui and Send over the network if in online mode
            switch(curTurn)
            {
                case 'x':
                 
                    //x is always Player 1
                    playerName = "Player 1";
                    move = "x" ;
                    ImageView x_img = new ImageView(new Image("resources/x.png"));
                    x_img.setFitHeight(120);
                    x_img.setFitWidth(120);
                    GridPane.setMargin(x_img, new Insets(0.0, 0.0, 0.0, 12.0));
                    g.gridPane.add(x_img,i,j);
                    if(online)
                    {
                        if(isClicked){
                        hideAllButtons();
                        conHandler.writeData(""+i+j);
                        }
                    }
                    break;
                    
                case 'o':
                    //o is always Player 2
                    playerName = "Player 2";
                    move = "o";
                    ImageView o_img = new ImageView(new Image("resources/o.png"));
                    o_img.setFitHeight(120);
                    o_img.setFitWidth(120);
                    GridPane.setMargin(o_img, new Insets(0.0, 0.0, 0.0, 12.0));
                   g.gridPane.add(o_img,i,j);
                   if(online)
                   {
                       if(isClicked){
                        hideAllButtons();
                       conHandler.writeData(""+i+j);
                       }
                   }
                   break;  
            }
            
            move += i +"" +j;
            //check if there's a winner
            if(glc.doChecks())
            {
                //
                result = glc.getWinner();
                //animation
                
                //go back to options
                switch (result) {
                    case "X":
                        /*
                        w.med.setOnEndOfMedia(() -> {
                            w.med.seek(Duration.ZERO);
                        });
                        w.med.play();
                        */
                        window.setScene(xScene);
                        break;
                    case "O":
                        /*
                        w.med.setOnEndOfMedia(() -> {
                            w.med.seek(Duration.ZERO);
                        });
                        w.med.play();
                        */
                        window.setScene(winnerScene);
                        break;
                    case"T":
                        window.setScene(tieScene);
                        break;
                }
           // System.out.println("Game Ended\n"+curTurn +"Won !!");
            if(!isReplay){
          //  window.setScene(homeScene);
            gameEnd =true;
            }
            }

            if(recorded)
            {
                //update DB
                dbHandler.updateInsertQuery(gameId,playerName,move,result);
            }
            //end the turn
            if(gameEnd){
                //end the Current Game
                //terminateCurrentGame();
                return ;}
            //let computer take turn or switch turns for next player
            String nextTurn = glc.nextTurn();
            //update ui if Computer played
            if(!nextTurn.equals(""))
            {   int col = Integer.parseInt(nextTurn.substring(0,1));
                int row = Integer.parseInt(nextTurn.substring(1));
                move = "o"+col+row;
                //System.out.println("col:"+col+",row:"+row);
                ImageView o_img = new ImageView(new Image("resources/o.png"));
                o_img.setFitHeight(120);
                o_img.setFitWidth(120);
                GridPane.setMargin(o_img, new Insets(0.0, 0.0, 0.0, 12.0));
                g.gridPane.add(o_img,col,row); 
               //check if there's a winner after Ai had played
                if(glc.doChecks())
                {
                    result  = glc.getWinner();
                /*switch(curTurn)
                {
                    case 'x':result="X";break;
                    case 'o':result="O";break;
                }*/
                //animation
                switch (result) {
                    case "X":
                        /*
                        w.med.setOnEndOfMedia(() -> {
                            w.med.seek(Duration.ZERO);
                        });
                        w.med.play();
                        */
                        window.setScene(xScene);
                        break;
                    case "O":
                        /*
                        w.med.setOnEndOfMedia(() -> {
                            w.med.seek(Duration.ZERO);
                        });
                        w.med.play();
                        */
                        window.setScene(winnerScene);
                        break;
                    case"T":
                        window.setScene(tieScene);
                        break;
                }
                //go back to options
              //  System.out.println("Game Ended\n"+glc.getTurn() +"Won !!");
                if(!isReplay){
            //    window.setScene(homeScene);
                gameEnd = true ;
                }
                }
                if(recorded)
                {
                    //update DB
                    dbHandler.updateInsertQuery(gameId,"Ai",move,result);
                }
                if(gameEnd)
                {
                    //terminateCurrentGame();
                    return ;
                } 
            }
            
            /**/
            
            //System.out.println(glc);
    }
    public void terminateCurrentGame()
    {
       if(online)
        {
            switch(appNetworkMode)
            {
                case "host":
                    conHandler.stopServer();
                    if(serverInitTh != null)
                    {
                    serverInitTh.stop();
                    }
                    if(serverTh != null)
                    {
                     serverTh.stop();       
                    }
                    break;
                case "guest":
                    conHandler.stopClient();
                    if(clientTh != null)
                    {
                      //System.out.println("clientStopping");
                     clientTh.stop();   
                     //System.out.println("clientStopped");
                    }
                    if(clientInitTh != null)
                    {
                    clientInitTh.stop();
                        //System.out.println("clientInitStopped");
                    }
                    
                    break;
            }
        }  
        if(isReplay){
            for(PauseTransition currentAnimation:delay)
            {
                if(currentAnimation != null)
                {
                    currentAnimation.stop();
                }
            }
        }
        //System.out.println("inTer");
        try{
            //System.out.println("inTer before Start");
            start(window);
            //System.out.println("inTer after Start");
            }catch(Exception restart)
            {
                restart.printStackTrace();
            }
        glc.newGame(false);
        if(recorded)
        {
            //stop insert statement
            if(dbHandler.getInsertState())
            {
                dbHandler.stopInsert();
                dbHandler.stopCon();
            }
            
        }
        //System.out.println("terminate done");
    }
    private void hideAllButtons()
    {
        g.btn00.setVisible(false);
        g.btn01.setVisible(false);
        g.btn02.setVisible(false);
        g.btn10.setVisible(false);
        g.btn11.setVisible(false);
        g.btn12.setVisible(false);
        g.btn20.setVisible(false);
        g.btn21.setVisible(false);
        g.btn22.setVisible(false);
    }
    private void showAllButtons()
    {
        g.btn00.setVisible(true);
        g.btn01.setVisible(true);
        g.btn02.setVisible(true);
        g.btn10.setVisible(true);
        g.btn11.setVisible(true);
        g.btn12.setVisible(true);
        g.btn20.setVisible(true);
        g.btn21.setVisible(true);
        g.btn22.setVisible(true);
    }
    private void fireButton(String move)
    {
        switch(move)
                {
                    case "00":g.btn00.fire();break;
                    case "01":g.btn01.fire();break;
                    case "02":g.btn02.fire();break;
                    case "10":g.btn10.fire();break;
                    case "11":g.btn11.fire();break;
                    case "12":g.btn12.fire();break;
                    case "20":g.btn20.fire();break;
                    case "21":g.btn21.fire();break;
                    case "22":g.btn22.fire();break;
                }
    }
  
    
    public void playSnakeScene()
    {
        //s.bg_mediaPlayer.play();
            s.gc = canvas.getGraphicsContext2D();

            snakeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    KeyCode code = event.getCode();
                    if (code == KeyCode.RIGHT || code == KeyCode.D) {
                        if (s.currentDirection != LEFT) {
                            s.currentDirection = RIGHT;
                        }
                    } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                        if (s.currentDirection != RIGHT) {
                            s.currentDirection = LEFT;
                        }
                    } else if (code == KeyCode.UP || code == KeyCode.W) {
                        if (s.currentDirection != DOWN) {
                            s.currentDirection = UP;
                        }
                    } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                        if (s.currentDirection != UP) {
                            s.currentDirection = DOWN;
                        }
                    }
                }
            });

            for (int i = 0; i < 3; i++) {
                s.snakeBody.add(new Point(5, ROWS / 2));
            }
            s.snakeHead = s.snakeBody.get(0);
            s.generateFood();

            timeline = new Timeline(new KeyFrame(Duration.millis(130), e1 -> runGame(s.gc)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            
    }
    
    private void runGame(GraphicsContext gc) {
       
         
        if (s.gameOver) {
            gameOver_snake.score.setText(""+s.getScore());
            //s.bg_mediaPlayer.stop();
            timeline.stop();
            
            window.setScene(gameoverSnakeScene);
            s.stopGame();
            
            return;
            
        }
        
        s.drawBackground(gc);
        s.drawFood(gc);
        s.drawSnake(gc);
        s.drawScore();

        for (int i = s.snakeBody.size() - 1; i >= 1; i--) {
            s.snakeBody.get(i).x = s.snakeBody.get(i - 1).x;
            s.snakeBody.get(i).y = s.snakeBody.get(i - 1).y;
        }

        switch (s.currentDirection) {
            case RIGHT:
                s.moveRight();
                break;
            case LEFT:
                s.moveLeft();
                break;
            case UP:
                s.moveUp();
                break;
            case DOWN:
                s.moveDown();
                break;
        }

        s.gameOver();
        s.eatFood();
        
        //eat_mediaPlayer.play();
    }
    
    
    
}
