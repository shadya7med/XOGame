/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author win.7
 */
public class XOGame extends Application {
    
    Scene homeScene, optionsScene, gameScene, hostGuestScene;
    Stage window;
    DbHandler dbHandler ; 
    GameLogic glc ;
    Game g;
    Options o ;
    Home h ;
    HostGuest hg;
    private int gameId; 
    private boolean recorded ;
    private boolean online ;
    private boolean isReplay;
    private boolean isClicked ;
    private int col = 0;
    private int row = 0;
    private ArrayList<String> gameTurns ;        
    private ConnectionHandler conHandler ;
    private Thread serverTh,clientTh,serverInitTh,clientInitTh;
    private String appNetworkMode ;
    private String clientInput,serverInput ;
    //static boolean replay;
    @Override
    public void init(){
        dbHandler = new DbHandler();
        dbHandler.startCon();
        
        glc = new GameLogic(); 
        conHandler = new ConnectionHandler();
        
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        window = stage;
        o=new Options();
        g=new Game();
        h=new Home();
        hg=new HostGuest();
        recorded =false ;
        online = false ;
        int replayId = 3;
        isClicked = true ;
        homeScene = new Scene(h, 600, 520);
        optionsScene = new Scene(o, 600, 520);
        gameScene = new Scene(g, 600, 520);
        hostGuestScene = new Scene(hg,600,520);
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
        
        //-------------------opening xo game--------------------//
        h.xo_game.setOnMousePressed((e)
                -> {
            window.setScene(optionsScene);

        });
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
                //get the Starting gameId
                gameId = dbHandler.getGameId();
                //start Insert Statement
                dbHandler.startInsert();
            }
            //load Grid image
            window.setScene(gameScene);
            //Create 2-Player Offline Game
            glc.newGame(false);
            //
           // replay = false;
            
            
        });
        o.btn_online.setOnAction((ActionEvent e) -> {  
            window.setScene(hostGuestScene);
        });
        
        o.btn_offline.setOnAction((ActionEvent e) -> {
            //unlock mouseEvents
            isReplay = false ;
            //terminate current Game
            //terminateCurrentGame();
            if(recorded)
            {
                //get the Starting gameId
                gameId = dbHandler.getGameId();
                //start Insert Statement
                dbHandler.startInsert();
            }
            //load Grid image
            window.setScene(gameScene);
            //Create 2-Player Offline Game
            glc.newGame(true);
            //
            //replay = false;

        });
        o.replay.setOnAction(e ->{
            //unlock replay mode
            isReplay = true ;
            //turn recording off
            recorded = false ;
            //terminate current Game
            //terminateCurrentGame();
            //load Grid image
            window.setScene(gameScene);            
            //Create new Replay
            glc.newReplay(true);
            //
            //replay = true;
            //get game Turns
            gameTurns = dbHandler.getGameMoves(replayId);
            //make  the buttons invisible
            hideAllButtons();
            //play the selected game
            for(String move:gameTurns)
            {
                fireButton(move.substring(1));
                sleep(1);
            }
            //add andimation for result
            System.out.println("Ok");

        });
        hg.btn_host.setOnAction(e->{
            appNetworkMode = "host";
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
                    window.setScene(gameScene);
                    }});
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
                         System.out.println("Client closed");
                         //fire back_btn
                         g.btn_back.fire();
                         }else{
                         Platform.runLater(new Runnable() {
                         @Override
                        public void run() {
                             System.out.println(serverInput);
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
                    window.setScene(gameScene);
                    }});
                    //start reading thread for client
                    clientTh = new Thread()
                    {
                    @Override
                    public void run(){
                    
                    while(true)
                    {
                        
                        clientInput = conHandler.readClientBlocking();
                        if(clientInput!=null){
                        if(clientInput.equals("close"))
                         {
                         System.out.println("Server closed");
                         //fire back_btn
                         g.btn_back.fire();
                         }else{
                            
                            Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(clientInput);
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
        //-------------------back Buttons Actions--------------------//
        o.btn_back.setOnAction((ActionEvent e) -> {
            
            window.setScene(homeScene);
        });
        g.btn_back.setOnAction((ActionEvent e) -> {
            //
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
                    if(clientInitTh != null)
                    {
                    clientInitTh.stop();
                    }
                    if(clientTh != null)
                    {
                     clientTh.stop();       
                    }
                    break;
                }
            }   
            //terminate current Game
            terminateCurrentGame();
            //
            window.setScene(optionsScene);
        });
        hg.btn_back.setOnAction(e ->{
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
                    if(clientInitTh != null)
                    {
                    clientInitTh.stop();
                    }
                    if(clientTh != null)
                    {
                     clientTh.stop();       
                    }
                    break;
                }
            }
            window.setScene(optionsScene);
        });
        //-------------------show Home Screen--------------------//
        window.setScene(homeScene);
        window.setResizable(false);
        
        window.show();
        /*if(replay)
        {
            //get game Turns
            gameTurns = dbHandler.getGameMoves(replayId);
            //play the selected game
            for(int index=0;index < (gameTurns.size() - 1);index ++)
            {
                col = Integer.parseInt(gameTurns.get(index).substring(1,2));
                row = Integer.parseInt(gameTurns.get(index).substring(2));;
                turnPlay(col, row);
                try{
                Thread.sleep(1000);
                }catch(InterruptedException waitEx)
                {
                    waitEx.printStackTrace();
                }
            }
        }*/
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void stop(){
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
                    if(clientInitTh != null)
                    {
                    clientInitTh.stop();
                    }
                    if(clientTh != null)
                    {
                     clientTh.stop();       
                    }
                    break;
            }
        }   
        if(recorded)
        {
            dbHandler.stopCon();
        }
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
            System.out.println("Game Ended\n"+curTurn +"Won !!");
            if(!isReplay){
            window.setScene(homeScene);
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
                terminateCurrentGame();
                return ;}
            //let computer take turn or switch turns for next player
            String nextTurn = glc.nextTurn();
            //update ui if Computer played
            if(!nextTurn.equals(""))
            {   int col = Integer.parseInt(nextTurn.substring(0,1));
                int row = Integer.parseInt(nextTurn.substring(1));
                move = "o"+col+row;
                System.out.println("col:"+col+",row:"+row);
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
                
                //go back to options
                System.out.println("Game Ended\n"+glc.getTurn() +"Won !!");
                if(!isReplay){
                window.setScene(homeScene);
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
                    terminateCurrentGame();
                    return ;
                } 
            }
            
            /**/
            
            System.out.println(glc);
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
                    if(clientInitTh != null)
                    {
                    clientInitTh.stop();
                    }
                    if(clientTh != null)
                    {
                     clientTh.stop();       
                    }
                    break;
            }
        }  
        
        
        try{
            start(window);
            }catch(Exception twoplayerStart)
            {
                twoplayerStart.printStackTrace();
            }
        //glc.newGame(false);
        if(recorded)
        {
            //stop insert statement
            if(dbHandler.getInsertState())
            {
                dbHandler.stopInsert();
            }
        }
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
    public void sleep(long ms)
    {
        for(long i = 0;i < 1000000000;i++);
    
    }
    
    
    
}
