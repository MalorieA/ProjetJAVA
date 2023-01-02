package fr.chalodss.spaceinvaders.classes;

import javafx.scene.layout.Pane;

import static fr.chalodss.spaceinvaders.classes.EState.*;

public final class Renderer {

    public Renderer() {

    }

    private void addBeams(Pane board, Game game) {
        for (Entity e : game.beams) {
            if (!board.getChildren().contains(e)) {
                board.getChildren().add(e);
            }
        }
    }
    private void addBeams1(Pane board, Game game) {
        for (Entity e : game.beams1) {
            if (!board.getChildren().contains(e)) {
                board.getChildren().add(e);
            }
        }
    }

    private void removeEntities(Pane board) {
        board.getChildren().removeIf(e -> {
            if (e.getClass() != Player.class) {
                var entity = (Entity) e;
                return entity.state == DEAD;
            }
            return false;
        });
    }

    void render(Pane board, Game game) {
        addBeams(board, game);
        addBeams1(board, game);
        removeEntities(board);
    }

}