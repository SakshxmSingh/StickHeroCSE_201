package com.example.stickhero_cse201;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Stick {
    private int minLen;
    private int maxLen;
    private static double len;

    public static double elongate(Line stick, Rectangle areaToPress)  {
        Timeline timeline = new Timeline();

        // Define the keyframe with the desired duration and endY value
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), new KeyValue(stick.endYProperty(), stick.getEndY() - 1000));

        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Set the cycle count to indefinite to keep the animation running
        timeline.setCycleCount(1);

        // Play the timeline when the mouse is pressed
        timeline.play();




        // Stop the timeline and rotate the stick when the mouse is released

        areaToPress.setOnMouseReleased(mouseReleasedEvent -> {

            timeline.stop();




            len = stick.getEndY();


            // Rotate the stick 90 degrees
            // Get the top of the stick (end point, Y-coordinate)
            double topY = stick.getStartY();


            // Rotate the stick 90 degrees around its top
            Rotate rotate = new Rotate(90, stick.getStartX(), topY);
            stick.getTransforms().add(rotate);
            len = stick.getEndY();
            Timeline rotateTimeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(rotate.angleProperty(), 90))
            );

            // Play the rotation animation

            rotateTimeline.play();



        });

        return len;


    }
}
