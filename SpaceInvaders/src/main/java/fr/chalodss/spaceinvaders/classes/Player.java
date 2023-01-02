package fr.chalodss.spaceinvaders.classes;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static fr.chalodss.spaceinvaders.classes.Controller.Width;
/**
 * Class pour les joueur
 * */
public final class Player extends ImageView {

        EState state;
        int     velX;
        boolean canShoot;
        boolean canShoot2;
        Entity   beam  ;

        KeyCode moveleft;
        KeyCode moveright;
        KeyCode shoot;
/**
 * Constructeur
 * @param x
 *          la position x de joueur
 * @param y
 *          la position Y de joueur
 * @param img
 *          l'image de joueur
 * @param moveleft
 *             KeyCode qui permet à un joueur de déplacer vers la gauche
 * @param moveright
 *              KeyCode qui permet à un joueur de déplacer vers la droite
 * @param shoot
 *              KeyCode qui permet à un joueur de lancer un tir
 * @param beam
 *              tir des joueur
 *
 * */
        public Player(double x, double y, Image img,KeyCode moveleft,KeyCode moveright, KeyCode shoot,Entity beam) {
            setX(x);
            setY(y);
            setImage(img);
            this.moveleft = moveleft;
            this.moveright = moveright;
            this.shoot = shoot;
            this.beam=beam;

            canShoot = true;
            canShoot2 = true;

        }

        /**
         * Fonction qui permet a un joueur de deplacer suivant x
         * @param velX
         *          position avant de deplacement de joueur
         * */

        void move(int velX) {
            this.velX = velX;
            setX(getX() + velX);
        }

        void shoot() {
            beam.setX(getX() + 23);
            beam.setY(getY() -40 );
            canShoot = false;
        }
    void shoot1() {
        beam.setX(getX()  +25);
        beam.setY(getY()+10);
        canShoot2 = false;
    }

    /**
     * Fonction qui gère les keycode lorsqu'ils sont appuyés
     * */
        EventHandler<KeyEvent> onKeyReleased=new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                velX = 0;
                }
        };
    EventHandler<KeyEvent> onKeyPressed =new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == moveleft) {
                if (getX() >= 7) {
                    move(-7);
                }
            } else if
                (e.getCode() == moveright) {
                    if (getX() <= Width-80) {
                        move(7);

                }
            }
            if (e.getCode() == KeyCode.I && canShoot2) {
                canShoot=false;
                shoot1();
            }
            if (e.getCode() == KeyCode.W && canShoot) {
                shoot();
            }
        }
    };


    /**
     * Fonction qui gère les keycode lorsqu'ils ne sont pas appuyés
     * */

        void onKeyReleased1(KeyEvent e) {
            velX = 0;
        }

    }
