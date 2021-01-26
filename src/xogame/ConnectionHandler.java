/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Platform;

/**
 *
 * @author win.7
 */
public class ConnectionHandler {
    
    private ServerSocket serverSocket;
    private Socket socket ;
    private DataInputStream dis;
    private PrintStream dos ;
    private Thread serverTh,clientTh,serverInitTh,clientInitTh;
    private boolean serverInit,clientInit ;
    String input="" ;
    
    public void serverInit(int port)
    {
        try{
        serverSocket = new ServerSocket(port);
        serverInitTh = new Thread(){
        @Override
        public void run(){
            try{
            socket = serverSocket.accept();
            dis = new DataInputStream(socket.getInputStream()) ;
            dos = new PrintStream(socket.getOutputStream());
            serverInit = true ;
            serverTh.start();
            //System.out.println("Connected");
            }catch(IOException acceptEx)
            {
                acceptEx.printStackTrace();
            }
        }
        };
        serverInitTh.start();
        
        }catch(IOException serverInitEx)
        {
            serverInitEx.printStackTrace();
        }
    }
    public void serverInitBlocking(int port)
    {
        try{
        serverSocket = new ServerSocket(port);
            try{
            socket = serverSocket.accept();
            dis = new DataInputStream(socket.getInputStream()) ;
            dos = new PrintStream(socket.getOutputStream());
            serverInit = true ;
            //System.out.println("Connected");
            }catch(IOException acceptEx)
            {
                acceptEx.printStackTrace();
            }
        
        }catch(IOException serverInitEx)
        {
            serverInitEx.printStackTrace();
        }
    }
    public void clientInit(String ip,int port)
    {
        
        clientInitTh = new Thread(){
            @Override
            public void run() {
                while(true)
                {
                    try{
                        socket = new Socket(ip,port);
                        dis = new DataInputStream(socket.getInputStream()) ;
                        dos = new PrintStream(socket.getOutputStream());
                        clientInit = true;
                        //holding start connection of client until server start
                        while(clientTh == null)
                        {
                            try{

                                Thread.sleep(120);
                            }catch(InterruptedException clientinit)
                            {
                                clientinit.printStackTrace();
                            }
                        }
                        clientTh.start();
                        break;


                    }catch(IOException clientEx)
                    {
                        //waiting for server 
                        //System.out.println("waiting for Host");
                        try{

                            Thread.sleep(100);
                        }catch(InterruptedException clientinit)
                        {
                            clientinit.printStackTrace();
                        }
                    }

                }
            }
        };
        clientInitTh.start();
    }
    public void clientInitBlocking(String ip,int port)
    {
        while(true)
                {
                    try{
                        socket = new Socket(ip,port);
                        dis = new DataInputStream(socket.getInputStream()) ;
                        dos = new PrintStream(socket.getOutputStream());
                        clientInit = true;
                        //holding start connection of client until server start
                        /*while(clientTh == null)
                        {
                            try{

                                Thread.sleep(120);
                            }catch(InterruptedException clientinit)
                            {
                                clientinit.printStackTrace();
                            }
                        }
                        clientTh.start();*/
                        break;


                    }catch(IOException clientEx)
                    {
                        //waiting for server 
                        //System.out.println("waiting for Host");
                        try{

                            Thread.sleep(100);
                        }catch(InterruptedException clientinit)
                        {
                            clientinit.printStackTrace();
                        }
                    }

                }
    }
    public void writeData(String data)
    {
        if(dos != null)
        {
            dos.println(data);
        }
    }
    public String readServer()
    {
        
        serverTh = new Thread(){
          @Override
          public void run(){
              
              while(true)
              {
                try{
                input = dis.readLine();
                if(input!=null){
                if(input.equals("close"))
                {
                    //System.out.println("Client closed");
                    Platform.exit();
                }else{
                //System.out.println(input);
                }
                }}catch(IOException readEx)
                {
                    readEx.printStackTrace();
                }
                
              }
          }
            
        };
        return input;
    }
    public String readServerBlocking()
    {
        String input = "";
        try{
                input = dis.readLine();
                }catch(IOException readEx)
                {
                    readEx.printStackTrace();
                }
                
        
        return input;
    }
    
    public void readClient()
    {
        clientTh = new Thread(() -> {
            while(true) {
                try {
                   // System.out.println("runClient");
                    input = dis.readLine();
                    
                    if(input!=null){
                    if(input.equals("close"))
                    {
                        //System.out.println("Server closed");
                        Platform.exit();
                    }else{

                        //System.out.println(input);
                    }
                    } }catch (IOException ex) {
                        ex.printStackTrace();
                    }

            }
        });
        
    }
    public String readClientBlocking()
    {
        String input = "";
            
                try {
                    //System.out.println("runClient");
                    input = dis.readLine();
                    }catch (IOException ex) {
                        ex.printStackTrace();
                    }
        return input ;
    }
    
    public void stopServer()
    {
        /*if(serverInitTh != null)
        {
            serverInitTh.stop();
        }
        if(serverTh != null)
        {
            serverTh.stop();
        }*/
        if(dos != null){
            dos.println("close");
            dos.close();
            //System.out.println("server sent close");
        }
        try{
        if(dis != null)
        {
            dis.close();  
        }
        if(socket != null)
        {
        socket.close();
        }
        if(serverSocket != null){
        serverSocket.close();
        }
        }catch(IOException closeEx)
        {
            closeEx.printStackTrace();
        }
        //System.out.println("serverClient done");
        
    }
    
    
    public void stopClient(){
            /*if(clientInitTh != null)
            {
                clientInitTh.stop();
            }
            if(clientTh != null)
            {
                clientTh.stop();
            }*/
            if(dos != null){
                dos.println("close");
                dos.close();
                //System.out.println("client sent close");
            }
        try {
            if(dis != null)
            {
               dis.close(); 
            }
            if(socket != null)
            {
                socket.close();
            }
        }catch(IOException closeEx)
        {
            closeEx.printStackTrace();
        }
        //System.out.println("stopClient done");
    }
    public boolean getServerInit()
    {
        return serverInit ;
    }
    public boolean getClientInit()
    {
        return clientInit ;
    }
    
}
