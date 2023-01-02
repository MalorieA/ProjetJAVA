package fr.chalodss.spaceinvaders.classes;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static fr.chalodss.spaceinvaders.classes.Controller.Width;

public final class Player extends ImageView {

        EState state;
        int     velX;
        boolean canShoot;
    boolean canShoot2;
        Entity   beam  ;

        KeyCode moveleft;
        KeyCode moveright;
        KeyCode shoot;

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



        void onKeyReleased1(KeyEvent e) {
            velX = 0;
        }

    }
