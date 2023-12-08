package com.example.stickhero_cse201;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
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
    private Button pauseButton;

    @FXML
    private Rectangle perfectLand;

    @FXML
    private Rectangle platform;

    @FXML
    private Text score;

    @FXML
    private Line stick;

    @FXML
    private double controlLine(MouseEvent event) {

        // Create a new timeline for each mouse press
        Timeline timeline = new Timeline();

        // Define the keyframe with the desired duration and endY value
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), new KeyValue(stick.endYProperty(), stick.getEndY() - 1000));

        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Set the cycle count to indefinite to keep the animation running
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Play the timeline when the mouse is pressed
        timeline.play();

        // Stop the timeline and rotate the stick when the mouse is released
        areaToPress.setOnMouseReleased(mouseReleasedEvent -> {
            timeline.stop();

            // Rotate the stick 90 degrees
            // Get the top of the stick (end point, Y-coordinate)
            double topY = stick.getStartY();

            // Rotate the stick 90 degrees around its top
            Rotate rotate = new Rotate(90, stick.getStartX(), topY);
            stick.getTransforms().add(rotate);
            Timeline rotateTimeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(rotate.angleProperty(), 90))
            );

            // Play the rotation animation
            rotateTimeline.play();

        });
        System.out.println(stick.getEndY());
        return stick.getStartY();

    }



    @FXML
    void switchToPauseScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pause.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    }

