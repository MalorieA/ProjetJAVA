package fr.chalodss.spaceinvaders;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static fr.chalodss.spaceinvaders.utils.Images.icon;


public class Run extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/garde.fxml"));
        Scene scene = new Scene(root);




        stage.setTitle("DUEL Invaders!");

         stage.getIcons().add(icon);
        stage.setResizable(true);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}