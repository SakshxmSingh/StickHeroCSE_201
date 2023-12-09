package com.example.stickhero_cse201;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;

import javafx.util.Duration;


public class playControl {
    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Rectangle areaToPress;

    @FXML
    private ImageView cherryObject;

    @FXML
    private ImageView player;

    @FXML
    private Button pauseButton;

    @FXML
    private Rectangle perfectLand, perfectLand2;

    @FXML
    private Rectangle platform, platformBase;

    @FXML
    private Text score;

    @FXML
    private AnchorPane root;

    @FXML
    private Line stick;

    private static int count = 0;

    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        playControl.count = count;
    }

    private double lineLength = 0;

    private Timeline playerWalk;

    private Timeline platformMove;

    private double x1;
    private double x2;
    private double startX;
    private int correct;
    private boolean fallBool = false;

    @FXML
    private void onClick(MouseEvent event) throws InterruptedException {

        if (count == 0) {
            Timeline timeline = new Timeline();

            // Define the keyframe with the desired duration and endY value
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), new KeyValue(stick.endYProperty(), stick.getEndY() - 1000));

            // Add the keyframe to the timeline
            timeline.getKeyFrames().add(keyFrame);

            // Set the cycle count to indefinite to keep the animation running
            timeline.setCycleCount(1);

            // Play the timeline when the mouse is pressed
            timeline.play();
            playControl.setCount(playControl.getCount() + 1);

            // Stop the timeline and rotate the stick when the mouse is released

            areaToPress.setOnMouseReleased(mouseReleasedEvent -> {
                timeline.stop();

                // Rotate the stick 90 degrees
                // Get the top of the stick (end point, Y-coordinate)
                
                if(playControl.getCount() == 1){
                    
                    lineLength = stick.getEndY() - stick.getStartY();

                    double topY = stick.getStartY();
                        // Rotate the stick 90 degrees around its top
                    Rotate rotate = new Rotate(90, stick.getStartX(), topY);
                    stick.getTransforms().add(rotate);
                    lineLength = stick.getEndY() - stick.getStartY();
                    Timeline rotateTimeline = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                            new KeyFrame(Duration.seconds(0.5), new KeyValue(rotate.angleProperty(), 90))
                    );

                    // Play the rotation animation
                    rotateTimeline.play();
                    playControl.setCount(playControl.getCount() + 1);

                    rotateTimeline.setOnFinished(finishEvent1 -> {
                        startX = platformBase.getX() + (platformBase.getWidth()/2);
                        x1 = platform.getLayoutX() -  (platform.getWidth()/2);
                        x2 = platform.getLayoutX() +  (platform.getWidth()/2);
                        System.out.println("the start and final" + x1 +" " + x2);
                        if((-lineLength) + startX < x1 || (-lineLength) + startX > x2){
                            // try {
                            //     switchToFailScreen(root);
                            // } catch (IOException e) {
                            //     throw new RuntimeException(e);
                            // }
                            fallBool = true;
                        }
                        else{
                            correct++;
                            score.setText(String.valueOf(correct));


                        }
                        if (playControl.getCount() == 2) {
                            // Move the player to the end of the stick
                            // playerWalk = new TranslateTransition(Duration.seconds(1), player);
                            // playerWalk.setByX(-lineLength);
                            // playerWalk.play();
                            // playControl.setCount(playControl.getCount() + 1);
                            // System.out.println("final count" + playControl.getCount());
                            // System.out.println("final length" + lineLength);

                            playerWalk = new Timeline();

                            KeyFrame playerKeyFrameRight = new KeyFrame(Duration.seconds(0.75), new KeyValue(player.translateXProperty(), -lineLength + player.getFitWidth()));
                            System.out.println("the line length is " + lineLength);
                            playerWalk.getKeyFrames().add(playerKeyFrameRight);
                            playerWalk.setCycleCount(1);
                            playerWalk.play();
                            playControl.setCount(playControl.getCount() + 1);

                            // Set the onFinished event for the platformMove
                            playerWalk.setOnFinished(finishEvent2 -> {
                                // player.setLayoutX(player.getLayoutX() - lineLength);
                                if (playControl.getCount() == 3) {
                                    platformMove = new Timeline();
                                    playerWalk.stop();

                                    if(fallBool){
                                        KeyFrame playerFallFrame = new KeyFrame(Duration.seconds(1), new KeyValue(player.translateYProperty(), 1000));
                                        KeyFrame playerRoFrame = new KeyFrame(Duration.seconds(1), new KeyValue(player.rotateProperty(), 360));
                                        platformMove.getKeyFrames().add(playerFallFrame);
                                        platformMove.getKeyFrames().add(playerRoFrame);
                                        platformMove.setCycleCount(1);
                                        platformMove.play();
                                        playControl.setCount(playControl.getCount() + 1);


                                    }
                                    else{  
                                        System.out.println(platform.getX() + "  " + platformBase.getX() + " " + platform.getLayoutX());
                                        KeyFrame platformKeyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(platform.translateXProperty(), -(platform.getLayoutX() )));
                                        KeyFrame cherryKeyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(cherryObject.translateXProperty(), -(platform.getLayoutX() )));
                                        KeyFrame perfectLandKeyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(perfectLand.translateXProperty(), -(platform.getLayoutX() )));
                                        KeyFrame platformBaseKeyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(platformBase.translateXProperty(), -(platform.getLayoutX() )));
                                        KeyFrame playerKeyFrameLeft = new KeyFrame(Duration.seconds(1), new KeyValue(player.translateXProperty(), -(platform.getLayoutX() ) - lineLength + player.getFitWidth()));
                                        stick.setVisible(false);

                                        // Add the keyframe to the timeline
                                        platformMove.getKeyFrames().add(platformBaseKeyFrame);
                                        platformMove.getKeyFrames().add(platformKeyFrame);
                                        platformMove.getKeyFrames().add(cherryKeyFrame);
                                        platformMove.getKeyFrames().add(perfectLandKeyFrame);
                                        platformMove.getKeyFrames().add(playerKeyFrameLeft);
                                
                                        // Set the cycle count to 1
                                        platformMove.setCycleCount(1); //changed here to see
                                        platformMove.play();
                                
                                        playControl.setCount(playControl.getCount() + 1);
                                        System.out.println("final count" + playControl.getCount());
                                        System.out.println("final length" + lineLength);
                                        
                                    }
                       
                                    platformMove.setOnFinished(finishEvent3 ->{
                                        if(playControl.getCount()==4){
                                            platformMove.stop();
                                            if(fallBool){
                                                try {
                                                    switchToFailScreen(root);
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                finally{
                                                    fallBool = false;
                                                }
                                            }

                                            int index = 0;
                                            index = root.getChildren().indexOf(platformBase);
//                                            root.getChildren().remove((Node)platformBase);

//                                            platformBase = new Rectangle(0, platform.getLayoutY(), platform.getWidth(), platform.getHeight());
                                            platformBase.setX(0);
                                            platformBase.setId("platformBase"); // Set the ID
                                            platformBase.setFill(Color.web("#666666"));

//                                            root.getChildren().add(index,(Node)platformBase);

                                            index = root.getChildren().indexOf(platform);

//                                            root.getChildren().remove((Node)platform);

//                                            platform = new Rectangle(200, platformBase.getY(), 100, platformBase.getHeight());
                                            Random random = new Random();
                                            platform.setWidth(random.nextInt(10,100));
                                            platform.setLayoutX(random.nextInt(200,500));



//                                            root.getChildren().add(index,(Node)platform);

                                            perfectLand2 = new Rectangle(platform.getX() + platform.getWidth()/2 - 4, platform.getY(), 8, 8);
                                            perfectLand2.setFill(Color.web("#ff0000"));
                                            

                                            stick = new Line(-9 + player.getFitWidth() + player.getLayoutX(), 564, -9 + player.getFitWidth() + player.getLayoutX(), 564);
                                            stick.setId("stick"); // Set the ID
                                            stick.setStrokeWidth(7);
                                            stick.setStroke(Color.web("#e10000"));
                                            stick.setStrokeLineCap(StrokeLineCap.ROUND);
                                            stick.setStrokeType(StrokeType.CENTERED);

                                            


                                            // ImageView playerTemp = new ImageView(String.valueOf(getClass().getResource("images/ninja_initial.png")));
                                            // playerTemp.setFitHeight(57);
                                            // playerTemp.setFitWidth(50);
                                            // playerTemp.setLayoutX(player.getLayoutX() + player.getTranslateX());
                                            // playerTemp.setLayoutY(player.getLayoutY() + player.getTranslateY());


                                            // player

                                            // root.getChildren().remove((Node)player);

                                            // player = playerTemp;


                                        

                                            //addd the platform to the scene
                                            // root.getChildren().add((Node)platform);
                                            // root.getChildren().add((Node)platformBase);
                                            root.getChildren().add((Node)stick);
                                            root.getChildren().remove((Node)perfectLand);
                                            root.getChildren().add((Node)perfectLand2);




                                            playControl.setCount(0);
                                            lineLength = 0;
                                            platformBase.setTranslateX(0);
                                            platform.setTranslateX(0);
                                            perfectLand2.setTranslateX(0);
                                            player.setLayoutX(player.getLayoutX() + player.getTranslateX());
                                            player.setTranslateX(0);

                                            rotateTimeline.getKeyFrames().clear();
                                            System.out.println(platformMove.getKeyFrames());
                                            playerWalk.getKeyFrames().clear();

                                            System.out.println("the count animation is " + platformMove.getCycleCount());


                                            

                                        }
                                    });
                                }
                            });

                        }
                    });
                }
                

            });

        }
        System.out.println("initial count " + playControl.getCount());
        System.out.println("initial length " + lineLength);

    }
        

    @FXML
    void switchToPauseScreen(ActionEvent event) throws IOException {
        playControl.setCount(0);
        root = FXMLLoader.load(getClass().getResource("pause.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent)root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void switchToFailScreen(Parent root) throws IOException {
        Stage stage = (Stage) ((Node) root).getScene().getWindow(); // Get the existing stage
        root = FXMLLoader.load(getClass().getResource("failed.fxml"));
        Scene scene = new Scene(root);
        Text scoreText = (Text) root.lookup("#finalScore");
        scoreText.setText(String.valueOf(correct));

        stage.setScene(scene);
        stage.show();


    }

}

