package com.example.stickhero_cse201;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Stick {
    private int minLen;
    private int maxLen;
    private int len;

    public void elongate(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));

        Line stick = (Line) root.lookup("#stick");
        // Create a Timeline for the animation
        Timeline timeline = new Timeline();

        // Define the keyframe with the desired duration and endY value
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(stick.endYProperty(), stick.getEndY() + 50));

        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Play the timeline
        timeline.play();
    }
}
