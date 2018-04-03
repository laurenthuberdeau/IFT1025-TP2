package Model;

import View.ImageRenderer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Model.Item : Model.Shield.
 * <p>
 * Rend le joueur invulnerable aux collisions pendant quelques secondes
 */
public class Shield extends Item {

    private boolean used = false;

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
