package com.example.stickhero_cse201;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class StickHeroMain extends Application implements Serializable{

    private static ArrayList<Player> playerList = new ArrayList<>();

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public static void setPlayerList(ArrayList<Player> playerList) {
        StickHeroMain.playerList = playerList;
    }


    private Stage stage;

    public static void addNewPlayer(Player player){
        playerList.add(player);

    }
    public static void removePlayer(Player player){
        playerList.remove(player);
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

    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch();
    }

    public static void Serialize(ArrayList<Player> playerList) throws IOException {
        //serialize to savedGames.txt in resources
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("src/main/resources/com/example/stickhero_cse201/savedGames.txt"));
            out.writeObject(playerList);
        }
        finally{
            if (out != null) out.close();
        }
    }

    public static void Deserialize() throws IOException, ClassNotFoundException {
        //deserialize from savedGames.txt in resources
        ObjectInputStream in = null;
        ArrayList<Player> playerList2 = null;
        try{
            in = new ObjectInputStream(new FileInputStream("src/main/resources/com/example/stickhero_cse201/savedGames.txt"));
            playerList2 = (ArrayList<Player>) in.readObject();
        }
        finally{
            if(in != null) in.close();
        }
        for(Player player: playerList2){
            System.out.println(player.getScore());
            addNewPlayer(player);
        }
        
    }


}
