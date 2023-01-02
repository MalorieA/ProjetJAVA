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

public final class Game {

    final BooleanProperty end       = new SimpleBooleanProperty(false);
    final IntegerProperty score     = new SimpleIntegerProperty(0);

    final Player player;
    final Player player2;
    final List<Entity> invaders;
    final List<Entity>beams;

    final List<Entity>beams1;
    final Random rand      = new Random();

    int                   direction = 0;
    double                deltaX    = 1;


    public Game(Player player, Player player2, List<Entity> invaders) {
        this.player   = player;
        this.player2 = player2;
        this.invaders = invaders;
        this.beams    = new LinkedList<>();
        this.beams1    = new LinkedList<>();
    }

    void handlePlayer() {


            player2.move(player2.velX);
            player.move(player.velX);

    }

    void updateInvaders() {
        invadersMove();
        invadersShoot();

        invadersShoot1();
        //updateSpeedInvaders();
    }

    public List<Entity> GetListOfImage(Image img){
        List<Entity> smallerLists = new LinkedList<>();
        for (Entity invader : invaders) {
            if (invader.getImage() == img) {
                smallerLists.add(invader);
            }
        }
        return smallerLists;
    }

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

    private void updateBeamPlayer() {
        if (player.beam.getY() < 0) {
            player.beam.setY(-50);
            player.beam.setX(0);
            player.canShoot = true;
        } else if (player.beam.getY() > 0) {
            player.beam.moveY(-25);
        }
    }

    private void updateBeamPlayer2() {
        if (player2.beam.getY() >Height) {
            player2.beam.setY(Height+50);
            player2.beam.setX(0);
            player2.canShoot2 = true;
        } else if (player2.beam.getY() > 0) {
            player2.beam.moveY(25);
        }
    }
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
      // collisionBeamPlayerEnnemies2(player2, invaders, score,end);
       collisionBeamEnnemiesBeamsPlayer(beams,beams1,player,player2);
       collisionBeamEnnemiesBeamsPlayer(beams,beams1,player,player2);
    }


}