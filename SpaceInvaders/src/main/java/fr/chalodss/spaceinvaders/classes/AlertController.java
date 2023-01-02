package fr.chalodss.spaceinvaders.classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDialog;
import javafx.stage.Stage;


public class AlertController implements Initializable {

    @FXML // Father of all components
    private StackPane root;

    private VBox validPane, invalidPane;

    public static JFXDialog validDialog, invalidDialog;

    @FXML
    public static void validDialog() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            validPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ValidAlert.fxml")));

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        validDialog = new JFXDialog(root, validPane, JFXDialog.DialogTransition.CENTER);


    }


    private void onValid() {
        validDialog.show();
    }

    @FXML
    private void onInvalid() {
        invalidDialog.show();
    }

    @FXML
    private void onClose() {
        Stage stage = (Stage) (root.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/Main.fxml")));
            stage.setScene(new Scene(root));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
