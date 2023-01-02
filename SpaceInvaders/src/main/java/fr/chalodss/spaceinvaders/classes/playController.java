package fr.chalodss.spaceinvaders.classes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static fr.chalodss.spaceinvaders.utils.Images.icon;

public class playController  {
    private static Stage stage;


    private static Scene scene;
    public double sceneWidth;
    private Parent root;


    public playController(){


    }


    public void gotoplay(ActionEvent event) throws IOException {


        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();


        root = FXMLLoader.load(getClass().getResource("/Area.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,screenWidth -10, screenHeight -40);

        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setTitle("DUEL Invaders!");

        stage.getIcons().add(icon);

        double sceneHeight = scene.getHeight();
        System.out.println(sceneHeight);
        System.out.println(sceneWidth);
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.show();


    }
    public void RestartGame(ActionEvent event) throws IOException {
        Collisions.stage.close();
        playController.stage.close();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();


        root = FXMLLoader.load(getClass().getResource("/Area.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,screenWidth -10, screenHeight -40);
        stage.setTitle("DUEL Invaders!");

        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }


    @FXML
    private void closeGame() {
        Platform.exit();

    }



}


