package Model;

import View.ImageRenderer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Item : Potion magique.
 * 
 * Fait changer la sorcière de couleur (aléatoire)
 */
public class Potion extends Item {

    private boolean used = false;

    /**
     * Constructeur de la potion, initialise renderer
     *
     * @param x Position horizontale
     * @param y Position verticale
     */
    public Potion(double x, double y) {
        super(x, y);

        this.renderer = new ImageRenderer("potion", this);
    }

    @Override
    public void tick(double dt) {
        // Rien à faire
    }

    @Override
    public double getWidth() {
        return 48;
    }

    @Override
    public double getHeight() {
        return 48;
    }

    @Override
    public Shape getShape() {
        return new Rectangle(
                getX()  - getWidth() / 2,
                getY()  - getWidth() / 2,
                getWidth(),
                getHeight());
    }

    /**
     * Une collision avec la potion fait changer la couleur du joueur.
     * Le statut "used" et l'affichage de la potion sont mis à jour.
     */
    @Override
    public void handleCollision(Player player, Game game) {
        used = true;
        this.renderer = new ImageRenderer("empty-potion", this);
        player.randomizeColor();
    }

    @Override
    public boolean intersects(Player player) {
        return !used & super.intersects(player);
    }
}
