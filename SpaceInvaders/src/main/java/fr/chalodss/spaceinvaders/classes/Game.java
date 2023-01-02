package fr.chalodss.spaceinvaders.classes;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static fr.chalodss.spaceinvaders.classes.Collisions.*;
import static fr.chalodss.spaceinvaders.classes.Controller.Height;
import static fr.chalodss.spaceinvaders.classes.Controller.Width;
import static fr.chalodss.spaceinvaders.classes.EState.DEAD;
import static fr.chalodss.spaceinvaders.utils.Images.*;
/**
 * Class qui gère le déroulement de jeu
 * */
public final class Game {

    final BooleanProperty end       = new SimpleBooleanProperty(false);
    final IntegerProperty score     = new SimpleIntegerProperty(0);

    final Player player;
    final Player player2;
    final List<Entity> invaders;
    final List<Entity>beams;

    final List<Entity>beams1;
    final Random rand      = new Random();
/**
 * direction de deplacement des alias
 * */
    int                   direction = 0;
    /**
     * Vitesse des alias seulon X
     * */
    double                deltaX    = 1;



    /**
     * Constructeur de class game
     * @param player
     *              premier joueur
     * @param player2
     *              deuxueme jouer
     * @param invaders
     *              list des alias
     * */
    public Game(Player player, Player player2, List<Entity> invaders) {
        this.player   = player;
        this.player2 = player2;
        this.invaders = invaders;
        this.beams    = new LinkedList<>();
        this.beams1    = new LinkedList<>();
    }

    /**
     * Fonction pour deplacer les players
     * */
    void handlePlayer() {
            player2.move(player2.velX);
            player.move(player.velX);
    }

    /**
     * Fonction pour mis a jours de movement et de tir des alias
     * */

    void updateInvaders() {
        invadersMove();
        invadersShoot();

        invadersShoot1();
        //updateSpeedInvaders();
    }


    /**
     * Fonction qui divise les alias seulon l'image donner en parametre
     * @param img
     *          image des alias
     * @return
     *         list de alias qui on la meme image
     * */
    public List<Entity> GetListOfImage(Image img){
        List<Entity> smallerLists = new LinkedList<>();
        for (Entity invader : invaders) {
            if (invader.getImage() == img) {
                smallerLists.add(invader);
            }
        }
        return smallerLists;
    }


    /**
     * Fonction qui deplace les alias
     * @param smallerLists
     *              liste de alias a deplacer
     * @param value
     *              vitesse de deplacement des alias
     *
     *  Lorsque les alias arrivent à la position des players la partie est fini
     * */
    public void MoveThemUporDown(List<Entity> smallerLists, int value){
        if ((direction & 1) == 0) {
            smallerLists.forEach(invader -> invader.moveX(deltaX));
        } else {
            smallerLists.forEach(invader -> invader.moveY(value));
            deltaX = -deltaX;
        }
        for (Entity invader : smallerLists) {
            if (invader.getX() <= 50 || invader.getX() >= Width-90) {
                direction ^= 1;
                break;
            }
        }

        //lorsque les alias arrive au ligne des canons
        smallerLists.forEach(invader ->

                {
                    //System.out.println(invader.getY());
                    if(invader.getY()> (Height-60) || invader.getY()<0){
                        Platform.exit();
                       }
                });



    }
    void invadersMove() {
        MoveThemUporDown(GetListOfImage(img1), -15);
        MoveThemUporDown(GetListOfImage(img), 15);
    }

    void updateSpeedInvaders() {
        if (invaders.size() > 33) {
            deltaX = (deltaX < 0) ? -1 : 1;
        } else if (invaders.size() > 11) {
            deltaX = (deltaX < 0) ? -2 : 2;
        } else if (invaders.size() > 5) {
            deltaX = (deltaX < 0) ? -5 : 5;
        } else if (invaders.size() > 1) {
            deltaX = (deltaX < 0) ? -10 : 10;
        } else {
            deltaX = (deltaX < 0) ? -25 : 25;
        }
    }
/**
 * Fonction pour gerer les tirs des alias en bas
 * */
    void invadersShoot() {

        List<Entity> smallerLists = new LinkedList<>();

        for (Entity invader : invaders) {
            if (invader.getImage() == img) {


                smallerLists.add(invader);
            }

            smallerLists.forEach(i -> {
                if (beams.size() < 5 && rand.nextInt(55) == 0) {
                    var beam = new Entity(i.getX() + 28, i.getY() + 50, BEAM_INVADER);
                    beams.add(beam);
                }
            });

        }}

    /**
     * Fonction pour gerer les tirs des alias en haut
     * */
    void invadersShoot1() {
        List<Entity> smallerLists1 = new LinkedList<>();
        for (Entity invader : invaders) {
            if (invader.getImage() == img1) {


                smallerLists1.add(invader);}
            smallerLists1.forEach(i -> {
                if (beams1.size() < 5 && rand.nextInt(55) == 0) {
                    var beam1 = new Entity(i.getX() + 28, i.getY() + 50, BEAM_INVADER);
                    beams1.add(beam1);
                }
            });
        }}

    void updateBeams() {
        updateBeamPlayer();
        updateBeamPlayer2();
        updateBeamsEnnemies();
        updateBeamsEnnemies1();
    }

    /**
     * Fonction pour gerer la deplacement des tirs de player 1
     * */
    private void updateBeamPlayer() {
        if (player.beam.getY() < 0) {
            player.beam.setY(-50);
            player.beam.setX(0);
            player.canShoot = true;
        } else if (player.beam.getY() > 0) {
            player.beam.moveY(-25);
        }
    }

    /**
     * Fonction pour gerer la deplacement des tirs de player 2
     * */

    private void updateBeamPlayer2() {
        if (player2.beam.getY() >Height) {
            player2.beam.setY(Height+50);
            player2.beam.setX(0);
            player2.canShoot2 = true;
        } else if (player2.beam.getY() > 0) {
            player2.beam.moveY(25);
        }
    }

    /**
     * Fonction pour gerer la deplacement des tirs des alias en bas
     * */
    private void updateBeamsEnnemies() {
        beams.forEach(beam -> {
            if (beam.getY() > Height-50) {
                beam.state = DEAD;
            } else {
                beam.setY(beam.getY() + 5);
            }
        });
        beams.removeIf(Entity::isDead);
    }
    /**
     * Fonction pour gerer la deplacement des tirs des alias en haut
     * */
    private void updateBeamsEnnemies1() {
        beams1.forEach(beam -> {
            if (beam.getY() <0 ) {
                beam.state = DEAD;
            } else {
                beam.setY(beam.getY() -5);
            }
        });
        beams1.removeIf(Entity::isDead);
    }

   void updateCollisions() {
      // collisionBeamEnnemiesPlayer(beams, beams1,player,player2, end);
        collisionBeamPlayerEnnemies(player,player2, invaders, score,end);
       collisionBeamEnnemiesBeamsPlayer(beams,beams1,player,player2);
       collisionBeamEnnemiesBeamsPlayer(beams,beams1,player,player2);
    }


}