package com.example.stickhero_cse201;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
     

    @FXML
    private void switchToPlayScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToPauseScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pause.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    @FXML
    private void switchToHomePage(ActionEvent event) throws IOException {
        playControl.setCount(0);
        Parent root = FXMLLoader.load(getClass().getResource("home_page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToLoadHomeScreen(ActionEvent event) throws IOException {
        playControl.setCount(0);
        Parent root = FXMLLoader.load(getClass().getResource("load_home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void saveGame(ActionEvent event) throws IOException {
        System.out.println("Game Saved");
        System.out.println("Score: " + playControl.getCorrect());
    }


}

