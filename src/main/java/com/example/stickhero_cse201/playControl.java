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
    private ImageView player;

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
    static int count;
    double lineLength;
    @FXML
    private void controlLine(MouseEvent event) throws InterruptedException {

        if (count != 1) {
            lineLength = Stick.elongate(stick, areaToPress);

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), player);
            translateTransition.setByX(-lineLength);
            translateTransition.play();
            count++;

            System.out.println("initial " + count);
        }
        if (count == 1) {

            count--;
            System.out.println(count);

        }


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

