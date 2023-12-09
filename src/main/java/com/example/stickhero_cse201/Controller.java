package com.example.stickhero_cse201;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {
    private static int serializationCount = 0;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private ScrollPane scrollPane;

    private AnchorPane createSaveGameFile(Player player) throws IOException{
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMaxSize(378.0, 93.0);
        anchorPane.setMinSize(292.0, 67.0);
        anchorPane.setPrefSize(378.0, 93.0);
        anchorPane.setStyle("-fx-background-color: d9d9d9; -fx-border-color: #808080; -fx-border-width: 2; -fx-border-radius: 7; -fx-background-radius: 7;");

        // Create the Text nodes
        Text text1 = new Text("SCORE:");
        text1.setLayoutX(12.0);
        text1.setLayoutY(31.0);
        text1.setFont(new Font("AXIS Extra Bold", 20.5));

        // Text text2 = new Text("18-11-23 13:26");
        // text2.setLayoutX(13.0);
        // text2.setLayoutY(54.0);
        // text2.setFont(new Font("AXIS Extra Bold", 14.0));

        
        String score = Integer.toString(player.getScore());
        Text text3 = new Text("xx");
        text3.setLayoutX(265.0);
        text3.setLayoutY(42.0);
        text3.setFont(new Font("AXIS Extra Bold", 17.0));

        Text text4 = new Text(score);
        text4.setId("score");
        text4.setLayoutX(101.0);
        text4.setLayoutY(30.0);
        text4.setFont(new Font("AXIS Extra Bold", 20.5));

        // Create the ImageView
        // ImageView imageView = new ImageView();
        // imageView.setFitHeight(45.0);
        // imageView.setFitWidth(64.0);
        // imageView.setLayoutX(311.0);
        // imageView.setLayoutY(11.0);
        // imageView.setImage(new Image("@/../../../../resources/com/example/stickhero_cse201/images/arrow.png"));

        // Create the Buttons
        Button button1 = new Button("Load");
        button1.setId("load");
        button1.setLayoutX(136.0);
        button1.setLayoutY(57.0);

        button1.setOnAction(event -> {
            playControl.setCount(0);
            playControl.setCorrect(player.getScore());
            try {
                switchToPlayScreenCont(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Button button2 = new Button("Delete");
        // button2.setId("delete");
        // button2.setLayoutX(191.0);
        // button2.setLayoutY(57.0);

        // button2.setOnAction(event -> {
        //     StickHeroMain.getPlayerList().remove(player);
        //     try {
        //         switchToLoadHomeScreen(event);
        //     } catch (IOException | ClassNotFoundException e) {
        //         e.printStackTrace();
        //     }
        // });


        // Add the nodes to the AnchorPane
        anchorPane.getChildren().addAll(text1, text3, text4, button1);

        return anchorPane;
    }
     

    @FXML
    private void switchToPlayScreenReset(ActionEvent event) throws IOException {
        playControl.setCount(0);
        playControl.setCorrect(0);
        Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToPlayScreenCont(ActionEvent event) throws IOException {
        playControl.setCount(0);
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
        playControl.setCorrect(0);
        Parent root = FXMLLoader.load(getClass().getResource("home_page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToLoadHomeScreen(ActionEvent event) throws IOException, ClassNotFoundException {
        playControl.setCount(0);
        playControl.setCorrect(0);

        if(serializationCount == 0){
            StickHeroMain.Deserialize();
            serializationCount++;    
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("load_home.fxml"));




        for(Node node: root.getChildrenUnmodifiable()){
            if(node.getClass().getSimpleName().equals("AnchorPane")){
                if(node.getId()!= null && node.getId().equals("mainAnchor")){

                    for(Node node2: ((AnchorPane)node).getChildrenUnmodifiable()){


                        if(node2 instanceof ScrollPane && node2.getId().equals("scrollPane")){

                                if(((ScrollPane)node2).getContent() instanceof VBox && ((ScrollPane)node2).getContent().getId().equals("vbox")){

                                    for(Player player: StickHeroMain.getPlayerList()){
                                        AnchorPane anchorPane = createSaveGameFile(player);
                                        ((VBox)((ScrollPane)node2).getContent()).getChildren().add(anchorPane);
                                    }
                                }
                            
                        }
                    }
                }

            }
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void saveGame(ActionEvent event) throws IOException {
        playControl.setCount(0);

        StickHeroMain.addNewPlayer(new Player(playControl.getCorrect()));
        StickHeroMain.Serialize(StickHeroMain.getPlayerList());

        Parent root = FXMLLoader.load(getClass().getResource("home_page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        
    }


}

