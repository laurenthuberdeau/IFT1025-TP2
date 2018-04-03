import Model.Entity;
import Model.Game;
import Model.Level;

import java.util.List;

/**
 * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
 */
public class Controller {

    private Game game;
    private int level = 1;

    // gameOverTimer allows player to see the witch explode into bouncingBalls before the new Game
    private double gameOverTimer;
    private static double gameOverDelay = 2;

    public Controller() {

        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
        this.gameOverTimer = 0;
    }

    public List<Entity> getEntities() {
        return this.game.getEntities();
    }

    /**
     * Fonction appelée à chaque frame du jeu.
     *
     * @param dt Delta-temps exprimé en secondes
     */
    public void tick(double dt) {
        if (this.game.isGameOver()) {

            if (gameOverTimer == 0 && this.game.hasWon()) level++;

            gameOverTimer += dt;

            if (gameOverTimer > gameOverDelay) {
                gameOverTimer = 0;
                this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
            }
        }
        this.game.tick(dt);
    }

    public Level getCurrentLevel() {
        return this.game.getLevel();
    }

    /**
     * Fonction appelée lorsque la barre espace est enfoncée.
     */
    public void spaceTyped() {
        this.game.jump();
    }

    public boolean gameIsOver() {
        return this.game.isGameOver();
    }

    public boolean gameHasWon() {
        return this.game.hasWon();
    }
}
