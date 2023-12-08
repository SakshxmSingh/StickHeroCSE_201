package com.example.stickhero_cse201;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// import java.util.ArrayList;

public class StickHeroMain extends Application{

    // private ArrayList<Player> playerList;
    // private Player currentPlayer;
    // private Stick gameStick;
    // private Platform gamePlatform;
    // private int platformSpacing;
    private Stage stage;

    public void addNewPlayer(){

    }
    public void newGame(){

    }
    public void loadGame(){

    }
    public void exitGame(){

    }




    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(StickHeroMain.class.getResource("home_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }


}
