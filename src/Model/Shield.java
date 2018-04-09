package Model;

import View.ImageRenderer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Model.Item : Model.Shield.
 *
 * Rend le joueur invulnerable aux collisions pendant quelques secondes
 */
public class Shield extends Item {

    private boolean used = false;

    /**
     * Constructeur du shield, initialise renderer
     *
     * @param x Position horizontale
     * @param y Position verticale
     */
    public Shield(double x, double y) {
        super(x, y);

        this.renderer = new ImageRenderer("shield", this);
    }

    @Override
    public void tick(double dt) {
        // Rien à faire
    }

    @Override
    public double getWidth() {
        return 32;
    }

    @Override
    public double getHeight() {
        return 32;
    }

    @Override
    public Shape getShape() {
        return new Circle(
                getX(),
                getY(),
                getWidth() / 2);
    }

    /**
     * Une collision avec le shield rend le joueur invulnérable pour 3 secondes
     * Le statut "used" et l'affichage du shield sont mis à jour.
     */
    @Override
    public void handleCollision(Player player, Game game) {
        used = true;
        this.renderer = null;
        player.setInvincibilityTimer(3);
    }

    @Override
    public boolean intersects(Player player) {
        return !used & super.intersects(player);
    }
}
