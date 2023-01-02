package fr.chalodss.spaceinvaders.classes;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static fr.chalodss.spaceinvaders.classes.Init.initInvaders;
import static fr.chalodss.spaceinvaders.utils.Images.*;

public final class Controller  {
    @FXML
    private Pane board;
    @FXML
    private Text score;



    BooleanProperty end;
    public static   Integer invWidth;
    public static   Integer Width;
    public static   Integer Height;

    private Player player;
    private  Player player2;

    private Game game;


    static AnimationTimer loop;
    private Renderer renderer;
    public static long startTime;

    public Player getPlayer() {
        return player;
    }

    public Controller() {

    }
    int i=1;

    @FXML
    private void init() throws InterruptedException {

        if (i!=1) {
            Collisions.stage.close();
            reset();


        }
        i++;
        Integer invHei=(int)board.getHeight()/3 -40;
        Width=(int)board.getWidth();
        Height=(int)board.getHeight();
        invWidth=(int)board.getWidth()/3;

        var invaders = initInvaders(invWidth, invHei);

        Entity BEM1   = new Entity(-20, 0, BEAM_PLAYER);
        Entity BEM2  = new Entity(0, Height-50, BEAM_PLAYER);
        end      = new SimpleBooleanProperty(false);
        player   = new Player(board.getWidth()/2, board.getHeight()-80, PLAYER,KeyCode.A,KeyCode.D,KeyCode.W,BEM1);
        player2  = new Player(board.getWidth()/2, 0, PLAYER2,KeyCode.J,KeyCode.L,KeyCode.I,BEM2);
        game     = new Game(player, player2, invaders);
        renderer = new Renderer();
       //set.setAlignment(Pos.CENTER);

        board.getChildren().add(game.player);
        board.getChildren().add(game.player2);

        board.getChildren().add(game.player.beam);
        board.getChildren().add(game.player2.beam);
        board.getChildren().addAll(game.invaders);
        board.addEventHandler(KeyEvent.KEY_PRESSED,player.onKeyPressed);
        board.addEventHandler(KeyEvent.KEY_PRESSED,player2.onKeyPressed);
        board.addEventHandler(KeyEvent.KEY_PRESSED,player.onKeyReleased);
        board.addEventHandler(KeyEvent.KEY_PRESSED,player2.onKeyReleased);
        //Double s=board.getWidth();
       // System.out.println("www"+s);
        score.textProperty().bind(Bindings.convert(game.score));


        end.bind(game.end);


        start();
    }

    @FXML
    private void start() {

        startTime = System.currentTimeMillis();;
        //System.out.println(startTime);
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!end.get()) {
                    game.handlePlayer();
                    game.updateInvaders();
                    game.updateBeams();
                    game.updateCollisions();
                    renderer.render(board, game);
                } else {
                    stop();
                }
            }
        };


        player2.requestFocus();
        player.requestFocus();
       // player2.requestFocus();
        loop.start();
    }

    @FXML
    private void reset() {
        loop.stop();
        board.getChildren().clear();
    }

}