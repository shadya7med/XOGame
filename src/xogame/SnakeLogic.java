/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame;

/**
 *
 * @author ITI
 */

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeLogic {
    
    protected static final int WIDTH = 600;
    protected static final int HEIGHT = 540;
    protected static final int ROWS = 19;
    private static final int COLUMNS = 17;
    private static final int SQUARE_SIZE = WIDTH / 20;
    private static final String[] FOODS_IMAGE = new String[]{"resources/ic_orange.png", "resources/ic_apple.png", "resources/ic_cherry.png",
            "resources/ic_berry.png", "resources/ic_coconut_.png", "resources/ic_peach.png", "resources/ic_watermelon.png", "resources/ic_orange.png",
            "resources/ic_pomegranate.png"};

    protected static final int RIGHT = 0;
    protected static final int LEFT = 1;
    protected static final int UP = 2;
    protected static final int DOWN = 3;

    protected GraphicsContext gc;
    protected List<Point> snakeBody = new ArrayList();
    protected Point snakeHead;
    private Image foodImage;
    private int foodX;
    private int foodY;
    protected boolean gameOver;
    protected int currentDirection;
    private int gameScore = 0;
    
    /*
    private String bgSong= "src/audio/Snake.mp3";
    Media bg_media = new Media(new File(bgSong).toURI().toString());       
    //Instantiating MediaPlayer class   
    MediaPlayer bg_mediaPlayer = new MediaPlayer(bg_media);
    */
    
    public void drawBackground(GraphicsContext gc) {
        for (int i = 0; i <=ROWS; i++) {
            for (int j = 0; j <=COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public void generateFood() {
        start:
        while (true) {
            
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }

    public void drawFood(GraphicsContext gc) {
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    public void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web("4674E9"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
        }
    }

    public void moveRight() {
        snakeHead.x++;
    }

    public void moveLeft() {
        snakeHead.x--;
    }

    public void moveUp() {
        snakeHead.y--;
    }

    public void moveDown() {
        snakeHead.y++;
    }

    public void gameOver() {
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x * SQUARE_SIZE >= WIDTH || snakeHead.y * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }

        //destroy itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    public void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            //eat_mediaPlayer.play();
            //eat_mediaPlayer.stop();
            snakeBody.add(new Point(-1, -1));
            generateFood();
            gameScore += 5;
            //eat_mediaPlayer.stop();
        }
    }

    public void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + gameScore, 10, 35);
    }
    
    public void stopGame()
    {
        //snakeBody.clear();
        snakeBody.removeAll(snakeBody);
        gameOver=false;
        gameScore=0;
        foodX=0;
        foodY=0;
        foodImage.cancel();
        snakeHead.x=0;
        snakeHead.y=0;
    }
    
     
    public int getScore()
    {
        return gameScore;
    }
   
}
