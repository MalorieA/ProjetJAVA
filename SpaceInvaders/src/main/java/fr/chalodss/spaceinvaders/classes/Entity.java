package fr.chalodss.spaceinvaders.classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static fr.chalodss.spaceinvaders.classes.EState.*;
    /**
     * Class qui représente l'entité
     * */
public final class Entity extends ImageView {

    EState state;


/**
 * @param x
 *          position x d'un element
 * @param y
 *         position y d'un element
 * @param img
 *          image d'un element
 * */
    public Entity(double x, double y, Image img) {
        this.setX(x);
        this.setY(y);
        this.state = ALIVE;
        this.setImage(img);
    }

        /**
         *fonction qui deplace un element seulon la valeur de X
         * */
    void moveX(double velX) {
        setX(getX() + velX);
    }
        /**
         *fonction qui deplace un element seulon la valeur de Y
         * */
    void moveY(double velY) {
        setY(getY() + velY);
    }
        /**
         *fonction qui transforme la status d'un element a DEAD
         * */
    boolean isDead() {
        return state == DEAD;
    }

}