package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Game - Classe principale du côté modèle, dont une instance
 * sera initialisé par le contrôleur à chaque niveau du jeu.
 */
public class Game {

    private Level level;
    private Player player;
    private List<BouncingBall> bouncingBalls;

    /**
     * Dimensions de l'écran
     */
    private double screenWidth, screenHeight;

    /**
     * Indique si la partie est terminée/gagnée
     */
    private boolean gameOver = false;
    private boolean hasWon = false;

    /**
     * Crée une partie dans le niveau levelNumber.
     *
     * @param screenWidth  largeur de l'écran
     * @param screenHeight hauteur de l'écran
     * @param levelNumber  numéro du niveau
     */
    public Game(double screenWidth, double screenHeight, int levelNumber) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        player = new Player(screenWidth / 2, 200, 15);
        bouncingBalls = new ArrayList<>();

        switch (levelNumber) {
            case 1:
                level = new Level1(screenWidth, screenHeight);
                break;
            case 2:
                level = new Level2(screenWidth, screenHeight);
                break;
            case 3:
                level = new Level3(screenWidth, screenHeight);
                break;
            case 4:
                level = new Level4(screenWidth, screenHeight);
                break;
            case 5:
                level = new Level5(screenWidth, screenHeight);
                break;
            case 6:
                level = new Level6(screenWidth, screenHeight);
                break;
            default:
                throw new IllegalArgumentException("Niveau inconnu");
        }
    }

    /**
     * Fonction appelée à chaque frame
     *
     * @param dt Delta-Temps (en secondes)
     */
    public void tick(double dt) {

        level.tick(dt);

        for (BouncingBall bouncingBall : bouncingBalls) {
            bouncingBall.tick(dt);
            if (bouncingBall.getX() + bouncingBall.getRadius() > screenWidth
                    || bouncingBall.getX() - bouncingBall.getRadius() < 0) bouncingBall.horizontalBounce();
        }

        // This condition prevents the player from moving after exploding into bouncingBalls,
        // which would cause the view to scroll
        if (this.gameOver == false){
            player.tick(dt);

            if (player.getY() - player.getRadius() < level.getScroll()) {
                // Empêche la balle de sortir de l'écran
                player.setY(level.getScroll() + player.getRadius());
            } else if (player.getY() - level.getScroll() > screenHeight / 2) {
                // Scroll le level verticalement si nécessaire
                level.incrementScroll(player.getY() - level.getScroll() - screenHeight / 2);
            }

            // Gestion des collisions avec les éléments (items/obstacles/...) du niveau
            for (LevelElement element : level.getEntities()) {
                if (element.intersects(player)) {
                    element.handleCollision(player, this);
                }
            }
        }
    }

    /**
     * @return les entités à afficher à l'écran
     */
    public List<Entity> getEntities() {

        List<Entity> entities = new ArrayList<>(level.getEntities());
        entities.add(player);
        entities.addAll(bouncingBalls);

        return entities;
    }

    /**
     * Getter de level
     *
     * @return this.level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Méthode qui fait "sauter" le joueur
     */
    public void jump() {
        player.jump();
    }

    /**
     * Méthode appelée lorsque le joueur a touché un obstacle d'une couleur différente,
     * ce qui fait disparaitre l'affichage normal du joueur
     * qui explose en 100 instances de bouncingBalls.
     */
    public void lose() {
        if (!this.gameOver) {
            this.gameOver = true;
            player.disappear();
            for (int i = 0; i < 100; i++) {
                bouncingBalls.add(new BouncingBall(player.getX(), player.getY(), 5));
            }
        }
    }

    /**
     * Méthode appelée lorsque le joueur a terminé le niveau avec succès
     */
    public void win() {
        this.hasWon = true;
        this.gameOver = true;
        player.disappear();
    }

    /**
     * Indique si la partie est gagnée
     *
     * @return
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * Indique si la partie est terminée
     *
     * @return
     */
    public boolean isGameOver() {
        return gameOver;
    }
}
