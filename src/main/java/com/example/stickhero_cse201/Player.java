package com.example.stickhero_cse201;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Player implements Serializable{

    private int score;

    public Player(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }


    public static boolean turnUpsideDown(ImageView player,boolean check){
        double h;
        if(check){
            h = - player.getFitHeight();
            check = !check;
        }
        else{
            h = player.getFitHeight();
            check = !check;
        }
        player.setScaleY(player.getScaleY() * -1);
        player.setLayoutY(player.getLayoutY() - h);
        return check;

    }



}
