package com.example.stickhero_cse201;
import java.io.Serializable;

public class Player implements Serializable{
    // private String playerName;
    // private boolean isAlive;
    private int score;
    // private Cherry cherry;
    // private int orientation;

    // private ImageView playerAvatar;
    // private Text scoreGame;

    public Player(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }

    public void saveGame(){

    }
    public void turnUpsideDown(){

    }
    public void pauseGame(){

    }

    public void revive(){

    }


}
