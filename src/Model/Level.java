package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite pour représenter un niveau.
 *
 */
public abstract class Level {

    /**
     * À quel point on est avancés dans le level
     */
    protected double scroll;

    /**
     * Dimensions du niveau visible à l'écran
     */
    protected double screenWidth, screenHeight;

    /**
     * Obstacles du niveau
     */
    protected List<Obstacle> obstacles;
    /**
     * Items et champignon final
     */
    protected List<Item> items;
    protected Mushroom victoryMushroom;

    /**
     * Constructeur du niveau.
     *
     * @param screenWidth Largeur de l'affichage
     * @param screenHeight Hauteur de l'affichage
     */
    public Level(double screenWidth, double screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        obstacles = new ArrayList<>();
        items = new ArrayList<>();
    }

    /**
     * Getter de scroll
     *
     * @return this.scroll
     */
    public double getScroll() { return scroll; }

    /**
     * Getter de screenWidth
     *
     * @return this.screenWidth
     */
    public double getScreenWidth() { return screenWidth; }

    /**
     * Getter de screenHeight
     *
     * @return this.screenHeight
     */
    public double getScreenHeight() { return screenHeight; }

    /**
     * Fonction appelée à chaque frame pour mettre à jour les entités.
     *
     * @param dt Delta-Temps en secondes
     */
    public void tick(double dt) {
        for (Obstacle o : obstacles) {
            o.tick(dt);
        }
        for (Item p : items) {
            p.tick(dt);
        }
    }

    /**
     * Méthode qui permet de faire scroller la portion visible du niveau
     *
     * @param scroll L'incrément de position
     */
    public void incrementScroll(double scroll) {
        this.scroll += scroll;
    }

    /**
     * Retourne les entités dans le niveau (obstacles, items et champignon)
     *
     * @return List des entités
     */
    public List<LevelElement> getEntities() {
        List<LevelElement> entities = new ArrayList<>();

        entities.addAll(this.obstacles);
        entities.addAll(this.items);

        entities.add(victoryMushroom);

        return entities;
    }

    /**
     * Retourne l'entier qui représente le numéro du niveau
     */
    abstract public int getLevelIndex();
}
