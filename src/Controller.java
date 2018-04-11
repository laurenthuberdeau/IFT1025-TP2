import Model.Entity;
import Model.Game;
import Model.Level;

import java.util.List;

/**
 * Contrôleur pour le jeu : fait le pont entre la vue et les modèles.
 */
public class Controller {

    // Permet au joueur de voir l'explosion de son cercle avant la nouvelle partie
    private static double GAME_OVER_DELAY = 2;

    private Game game;
    private int level = 1;

    private double gameOverTimer;

    /**
     * Contructeur de base
     */
    public Controller() {
        this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
        this.gameOverTimer = 0;
    }

    /**
     * Getter des entitées de jeu
     *
     * @return Liste d'entitées dans le jeu
     */
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

            if (gameOverTimer > GAME_OVER_DELAY) {
                gameOverTimer = 0;
                this.game = new Game(ColorsWitch.WIDTH, ColorsWitch.HEIGHT, level);
            }
        }
        this.game.tick(dt);
    }

    /**
     * Retourne le numéro du niveau.
     * Simple getter game::getLevel
     *
     * @return Le numéro du niveau
     */
    public Level getCurrentLevel() {
        return this.game.getLevel();
    }

    /**
     * Fonction appelée lorsque la barre espace est enfoncée; mouvement du joueur
     */
    public void spacePressed() {
        this.game.jump();
    }

    /**
     * Fonction qui active le mode de déboguage lorsque le joueur appuie sur tab
     */
    public void tabPressed() {
        this.game.debugModeOn();
    }

    /**
     * Fonction qui désactive le mode de déboguage lorsque le joueur relâche la touche tab
     */
    public void tabReleased() {
        this.game.debugModeOff();
    }

    /**
     * Retourne si la partie est terminée.
     * Simple getter game::isGameOver
     *
     * @return Si la partie est terminée
     */
    public boolean gameIsOver() {
        return this.game.isGameOver();
    }

    /**
     * Retourne si la partie est gagnée.
     * Simple getter game::hasWon
     *
     * @return Si la partie est gagnée
     */
    public boolean gameHasWon() {
        return this.game.hasWon();
    }
}
