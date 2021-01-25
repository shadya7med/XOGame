/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame;

import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Shady
 */
public class DbHandler {
    private Connection  con ;
    private PreparedStatement insertStmnt ;
    private Statement selectStmnt ;
    private ResultSet selectRS ;
    public void loadSQLDriver(){
        try{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        }catch(SQLException driverRegisterEx)
        {
            driverRegisterEx.printStackTrace();
        }
    }
    public void startCon()
    {
        try{
          
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/history","root","37701060Toka_MJJ");
        }catch(SQLException startEx)
        {
            startEx.printStackTrace();
        }
    }
    public void stopCon()
    {
        try{
        con.close(); 
        }catch(SQLException stopEx)
        {
            stopEx.printStackTrace();
        }  
    }
    public int getGameId()
    {
    int gameId = 0;
        
        try{
        selectStmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        selectRS = selectStmnt.executeQuery("select gameID from history.games");
        if(selectRS.next() == false)
        {
            gameId = 1 ;
        }else{
        selectRS.last();
        gameId = selectRS.getInt(1) + 1;
        }
        }catch(SQLException getGameEX)
        {
           getGameEX.printStackTrace();
        }finally{
            try{
                selectRS.close();
                selectStmnt.close();
            }catch(SQLException closeEx)
            {
                closeEx.printStackTrace();
            }
        }
        return gameId ;
    }
    public ArrayList<Integer> getDistinctGameId()
    {
        ArrayList<Integer> gameId = new ArrayList<Integer>();
        
        try{
        selectStmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        selectRS = selectStmnt.executeQuery("select distinct gameID from history.games");
        if(selectRS.next() == false)
        {
            System.out.println("Empty Result Set");
        }
        else{
        selectRS.beforeFirst();
        while(selectRS.next())
        {
            gameId.add(selectRS.getInt(1));
        }
        }
        }catch(SQLException getGameEX)
        {
           getGameEX.printStackTrace();
        }finally{
            try{
                selectRS.close();
                selectStmnt.close();
            }catch(SQLException closeEx)
            {
                closeEx.printStackTrace();
            }
        }
        return gameId ;
    }
    public void startInsert()
    {
        try{
        String query  = "insert into history.games(gameID,playerName,move,result) values(?,?,?,?)";
        
        insertStmnt = con.prepareStatement(query);
        }catch(SQLException insertEx)
        {
            insertEx.printStackTrace();
        }
    }
    public boolean getInsertState(){
        boolean retState = true ;
        if(insertStmnt == null)
        {
            retState = false; 
        }
        return retState ;
    }
    public void stopInsert()
    {
        try{
            insertStmnt.close();
        }catch(SQLException stopEx)
        {
            stopEx.printStackTrace();
        }
    }
    public void updateInsertQuery(int gameId,String playerName,String move,String result)
    {
        
        try{
        insertStmnt.setInt(1,gameId);
        insertStmnt.setString(2, playerName);
        insertStmnt.setString(3,move);
        insertStmnt.setString(4,result);
        insertStmnt.executeUpdate();
        }catch(SQLException updateEx)
        {
            updateEx.printStackTrace();
        }
    }
    public ArrayList<String> getGameMoves(int gameId)
    {   ArrayList<String> gameTurns = new ArrayList<>();
        int index =0;
         try{
              
            selectStmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            selectRS = selectStmnt.executeQuery("select playerName, move,result from history.games where gameID ="+gameId);
            if(selectRS.next() == false)
            {
                System.out.println("Empty Result Set");
            }else{
            selectRS.beforeFirst();
            while(selectRS.next())
            {
                gameTurns.add( selectRS.getString(2)) ;
                //selectRS.getString(1) + "  "+ selectRS.getString(2) + "  "+ selectRS.getString(3);
                index ++ ;
            }
            selectRS.last();
            gameTurns.add(selectRS.getString(3)); 
            //System.out.println(index);
           selectRS.close();
           selectStmnt.close();
            }
        }catch(SQLException showEx)
        {
            showEx.printStackTrace();
        }
        return  gameTurns ;
    }
}
