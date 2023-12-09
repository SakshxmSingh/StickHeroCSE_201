package com.example.stickhero_cse201;


import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Platform {
    // private int maxWidth;
    // private int minWidth;
    // private double width;

    public Platform(){

    }


    public static void randomWidth(Rectangle platform){
        Random random = new Random();
        platform.setWidth(random.nextInt(75,150));
        platform.setLayoutX(random.nextInt(200,500));
        return;

    }

}
