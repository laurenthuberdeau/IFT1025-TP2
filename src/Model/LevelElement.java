package Model;

/**
 * Éléments qui composent un niveau.
 *
 * Le joueur peut interagir avec toutes les objets de ce type en entrant en
 * collision avec.
 */
public abstract class LevelElement extends Entity {

    /**
     * Constructeur
     * @param x Position horizontale de l'objet
     * @param y Position verticale de l'objet
     */
    public LevelElement(double x, double y) {
        super(x, y);
    }

    /**
     * Détermine s'il y a collision entre this et player.
     * Utilise les fonctions de JavaFX
     *
     * @param player Joueur
     * @return Si le joueur rencontre l'objet
     */
    public boolean intersects(Player player) {
        return player.getShape().intersects(this.getShape().getBoundsInParent());
    }

    /**
     * Méthode abstraite permettant d'agir en cas de collision
     *
     * @param player Joueur
     * @param game Jeu
     */
    public abstract void handleCollision(Player player, Game game);
}
