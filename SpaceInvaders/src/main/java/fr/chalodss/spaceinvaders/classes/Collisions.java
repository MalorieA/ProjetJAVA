package fr.chalodss.spaceinvaders.classes;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static fr.chalodss.spaceinvaders.classes.Controller.Height;
import static fr.chalodss.spaceinvaders.classes.Controller.startTime;
import static fr.chalodss.spaceinvaders.classes.EState.DEAD;

/**
 * Class qui permet de gérer les collisions qui peuvent exister lors de lancements de l'application
 * */
public final class Collisions  {

    private Collisions() {

    }
    @FXML
    private static TextField textField;
    static Collisions mycol=new Collisions();
    public static Stage stage;
    static Scene scene;
    public static long endTimee;

    static  Parent root;
   // Entity explo = new Entity(-70,-70,Explotion);


static Game game;

/**
 * function qui permet de s'avoir s'il existe une collisions entre des tirs
 * @param e1
 *          de type Entity represente les tir peut etre des alias ou des canons
 *
 * @param e2
 *          de type Entity represente les tir peut etre des alias ou des canons
 * @return true si il existe une collision false sinon
 * */
    static boolean collide(Entity e1, Entity e2) {
        return e1.getBoundsInParent().intersects(e2.getBoundsInParent());
    }

    /**
     * function qui permet de s'avoir s'il existe une collisions entre un tir et un player
     *
     * @param e1
     *          de type Entity represente les tir peut etre des alias ou des canons
     *
     * @param player
     *         un des players (canons)
     * @return true si il existe une collision false sinon
     * */
    static boolean collide(Entity e1, Player player) {
        return e1.getBoundsInParent().intersects(player.getBoundsInParent());
    }



    /**
     * function qui gere la collision entre les tirs des alias et les players(2 canons)
     *
     * @param beams
     *          les tirs de player
     * @param beams2
     *          les tirs de player2
     * @param player
     *           le player en bas de la scenne
     * @param player2
     *           le player en haut
     * @param end
     *           fin de jeu
     *
     *
     *
     * */

    static void collisionBeamEnnemiesPlayer(List<Entity> beams,List<Entity> beams2, Player player,Player player2, BooleanProperty end) {

        beams.stream().forEach(beam -> {
            if(collide(beam,player)){

                beam.state = DEAD;}
        });

        beams2.stream().forEach(beam -> {
            if(collide(beam,player2)){

                beam.state = DEAD;}
        });



        if (beams.stream().filter(b -> collide(b, player)).count() > 0) {
            player.state = DEAD;

            end.set(true);
            endTimee = System.nanoTime();
            player.setY(-80);
           // System.out.println("ww  "+endTime+ " adw");
            System.out.println("sss");

            try {
                mycol.gotoplay("/InvalidAlert.fxml");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        if (beams2.stream().filter(b -> collide(b, player2)).count() > 0) {
            player2.state = DEAD;
            player2.setY(-80);
            end.set(true);
            endTimee = System.nanoTime();
          //  System.out.println("ww"+endTime);

            try {
                mycol.gotoplay("/InvalidAlert.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        beams2.removeIf(Entity::isDead);
        beams.removeIf(Entity::isDead);
    }

    /**
     * fonction qui nous offre un alert selon la collisions ou lorsque les player gagne
     *
     * @param filname
     *          le nom de fichier
     * */

    public void gotoplay(String filname) throws IOException {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(filname)));
            //System.out.println(root);
            scene = new Scene(root);
            System.out.println(scene);
            stage = new Stage();
            stage.setScene(scene);
            //stage.sizeToScene();
            stage.show();

    }
/**
 * fonction qui gere la collision entre les tirs des alias et des players
 * elle détruit les tirs lors d'une collision
 *
 * */
    static void collisionBeamEnnemiesBeamsPlayer(List<Entity> beams1,List<Entity> beams,Player player,Player player2) {

        beams1.stream().forEach(beam -> {
            if(collide(beam,player.beam)){
                player.beam.setY(-80);
                //System.out.println("detect");
                beam.state = DEAD;}
            });
        beams1.removeIf(Entity::isDead);

        beams.stream().forEach(beam -> {
            if(collide(beam,player2.beam)){
                player2.beam.setY(Height+80);
               // System.out.println("detect2");

                beam.state = DEAD;}
        });
        beams.removeIf(Entity::isDead);
    }


/**
 * function qui gere la collisoin entre les tirs des players et les alias
 * detruit les alias
 * detruit les tirs des players
 * augmente le score
 *
 * fonction qui gère la collision entre les tirs des players et les players
 *   détruit les tirs des players
 *   détruit le player affecté
 *
 *  dans le cas où les players détruisent tous les alias gagne le jeu et affiche une alert
 *
 * */

    static void collisionBeamPlayerEnnemies(Player player, Player player2, List<Entity> invaders, IntegerProperty score,BooleanProperty end) {
        if (player.beam.getY() > 0) {
            var op = invaders.stream().filter(i -> collide(i, player.beam)).findFirst();

            if (op.isPresent()) {
                Entity e = op.get();
                e.state = DEAD;
                player.beam.setY(-Height);
                score.set(score.get() + 20);
                invaders.remove(e);
            }
        }
        if (player2.beam.getY() > 0) {
            var op = invaders.stream().filter(i -> collide(i, player2.beam)).findFirst();

            if (op.isPresent()) {
                Entity e = op.get();
                e.state = DEAD;
                player2.beam.setY(Height+100);
                score.set(score.get() + 20);
                invaders.remove(e);
            }
        }
        if (player2.beam.getY() > 0) {
            var op2 = collide(player2.beam, player);

            if (op2==true) {
                endTimee = System.currentTimeMillis();
                player2.beam.setY(-80);
                player.setY(Height+100);
                System.out.println("end ca  "+(endTimee-startTime)/1000);
                end.set(true);

                try {
                    mycol.gotoplay("/InvalidAlert.fxml");
                    textField=new TextField();
                    textField.setText("text");


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            //    board.getChildren().remove(game.player);

            }
        }

        if (player.beam.getY() > 0) {
            var op2 = collide(player.beam, player2);

            if (op2==true) {
                //endTimee = System.nanoTime();
               // System.out.println("ww  "+endTimee);
                player.beam.setY(-80);
                player2.setY(-80);

                end.set(true);
                try {
                    mycol.gotoplay("/InvalidAlert.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }
        if (invaders.isEmpty()){

            System.out.println("Victory!");
            endTimee = System.currentTimeMillis();

            System.out.println("The time to win the game is = "+(endTimee-startTime)/1000 +"seconds");
            end.set(true);
            try {
                mycol.gotoplay("/ValidAlert.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }
    }





}