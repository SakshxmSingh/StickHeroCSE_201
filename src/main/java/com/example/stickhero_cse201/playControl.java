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
    private Rectangle platform, platformBase;

    @FXML
    private Text score;

    @FXML
    private AnchorPane root;

    @FXML
    private Line stick;

    @FXML
    private Text perfect;

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
    private boolean check = true;
    private double startX;
    private static int correct = 0;

    public static int getCorrect() {
        return correct;
    }
    public static void setCorrect(int correct) {
        playControl.correct = correct;
    }


    private boolean fallBool = false;

    public void initialise(){
        perfect.setTextAlignment(TextAlignment.CENTER);
        perfect.setVisible(false);
        score.setText(String.valueOf(correct));
    }

    @FXML
    private void onClick(MouseEvent event) throws InterruptedException {
        perfect.setVisible(false);
        score.setText(String.valueOf(correct));
        if (count == 0) {

            Timeline timeline = new Timeline();


            KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), new KeyValue(stick.endYProperty(), stick.getEndY() - 1000));


            timeline.getKeyFrames().add(keyFrame);


            timeline.setCycleCount(1);


            timeline.play();
            playControl.setCount(playControl.getCount() + 1);



            areaToPress.setOnMouseReleased(mouseReleasedEvent -> {
                timeline.stop();


                
                if(playControl.getCount() == 1){
                    
                    lineLength = stick.getEndY() - stick.getStartY();


                    double topY = stick.getStartY();

                    Rotate rotate = new Rotate(90, stick.getStartX(), topY);
                    stick.getTransforms().add(rotate);
                    lineLength = stick.getEndY() - stick.getStartY();
                    Stick.setLen(lineLength);

                    Timeline rotateTimeline = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                            new KeyFrame(Duration.seconds(0.5), new KeyValue(rotate.angleProperty(), 90))
                    );


                    rotateTimeline.play();
                    playControl.setCount(playControl.getCount() + 1);

                    rotateTimeline.setOnFinished(finishEvent1 -> {
                        startX = platformBase.getX() + (platformBase.getWidth()/2);
                        x1 = platform.getLayoutX() -  (platform.getWidth()/2);
                        x2 = platform.getLayoutX() +  (platform.getWidth()/2);

                        if((-Stick.getLen()) + startX - 17  < x1 || (-Stick.getLen()) + startX - 10 > x2){

                            fallBool = true;
                        }
                        else if((-Stick.getLen()) + startX -17 < x1 + 50 || (-Stick.getLen()) + startX -17 > x2 - 50){
                            correct= correct + 2;
                            score.setText(String.valueOf(correct));
                            perfect.setVisible(true);
                        }
                        else{
                            correct++;
                            score.setText(String.valueOf(correct));


                        }
                        if (playControl.getCount() == 2) {


                            playerWalk = new Timeline();

                            KeyFrame playerKeyFrameRight = new KeyFrame(Duration.seconds(0.75), new KeyValue(player.translateXProperty(), -Stick.getLen() + player.getFitWidth()));

                            playerWalk.getKeyFrames().add(playerKeyFrameRight);
                            playerWalk.setCycleCount(1);
                            playerWalk.play();
                            playControl.setCount(playControl.getCount() + 1);


                            // Set the onFinished event for the platformMove
                            playerWalk.setOnFinished(finishEvent2 -> {

                                if(!check){
                                    fallBool = true;
                                    correct--;
                                }
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

                                        KeyFrame platformKeyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(platform.translateXProperty(), -(platform.getLayoutX() )));
                                        KeyFrame cherryKeyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(cherryObject.translateXProperty(), -(platform.getLayoutX() )));
                                        KeyFrame platformBaseKeyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(platformBase.translateXProperty(), -(platform.getLayoutX() )));
                                        KeyFrame playerKeyFrameLeft = new KeyFrame(Duration.seconds(1), new KeyValue(player.translateXProperty(), -(platform.getLayoutX() ) - lineLength + player.getFitWidth()));
                                        stick.setVisible(false);

                                        // Add the keyframe to the timeline
                                        platformMove.getKeyFrames().add(platformBaseKeyFrame);
                                        platformMove.getKeyFrames().add(platformKeyFrame);
                                        platformMove.getKeyFrames().add(cherryKeyFrame);
                                        platformMove.getKeyFrames().add(playerKeyFrameLeft);
                                
                                        // Set the cycle count to 1
                                        platformMove.setCycleCount(1);
                                        platformMove.play();
                                
                                        playControl.setCount(playControl.getCount() + 1);

                                        
                                    }
                       
                                    platformMove.setOnFinished(finishEvent3 ->{
                                        perfect.setVisible(false);
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



                                            platformBase.setX(0);
                                            platformBase.setId("platformBase"); // Set the ID
                                            platformBase.setFill(Color.web("#666666"));



                                            index = root.getChildren().indexOf(platform);

                                            Random random = new Random();
                                            Platform.randomWidth(platform);
                                            player.setLayoutX(46.333333333333336);


                                            cherryObject.setLayoutX(random.nextInt(100,457));
                                            cherryObject.setLayoutY(570);
                                            cherryObject.setVisible(true);


                                            stick = new Line( startX + 15, 564,  startX + 15, 564);
                                            stick.setId("stick"); // Set the ID
                                            stick.setStrokeWidth(7);
                                            stick.setStroke(Color.web("#e10000"));
                                            stick.setStrokeLineCap(StrokeLineCap.ROUND);
                                            stick.setStrokeType(StrokeType.CENTERED);



                                            root.getChildren().add((Node)stick);


                                            playControl.setCount(0);
                                            lineLength = 0;
                                            platformBase.setTranslateX(0);
                                            platform.setTranslateX(0);
                                            player.setLayoutX(player.getLayoutX() + player.getTranslateX());
                                            player.setTranslateX(0);

                                            rotateTimeline.getKeyFrames().clear();

                                            playerWalk.getKeyFrames().clear();




                                        }
                                    });
                                }
                            });

                        }
                    });
                }
                

            });
            areaToPress.setOnKeyPressed(keyReleasedEvent -> {
                check = Player.turnUpsideDown(player,check);
            });
            areaToPress.requestFocus();

        }

    }


    @FXML
    void switchToPauseScreen(ActionEvent event) throws IOException {
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

