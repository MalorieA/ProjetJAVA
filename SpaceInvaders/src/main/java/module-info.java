module fr.chalodss.spaceinvaders {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens fr.chalodss.spaceinvaders to javafx.fxml;
    exports fr.chalodss.spaceinvaders;
    exports fr.chalodss.spaceinvaders.classes;
    opens fr.chalodss.spaceinvaders.classes to javafx.fxml;
}