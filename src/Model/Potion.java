package Model;

import View.ImageRenderer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Item : Potion magique.
 * 
 * Fait changer la sorcière de couleur
 */
public class Potion extends Item {

    private boolean used = false;

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
