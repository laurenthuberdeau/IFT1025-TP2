package Model;

import View.AnimationRenderer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Model.Item : champignon.
 * 
 * Ramasser un champignon permet de gagner le niveau actuel
 */
public class Mushroom extends Item {

    /**
     * Constructeur du champignon, initialise renderer
     *
     * @param x Position horizontale
     * @param y Position verticale
     */
    public Mushroom(double x, double y) {
        super(x, y);

        this.renderer = new AnimationRenderer("mushroom_animation",
                26,
                30,
                this);
    }

    @Override
    public void tick(double dt) {
        // Rien à faire
    }

    @Override
    public double getWidth() {
        return 64;
    }

    @Override
    public double getHeight() {
        return 64;
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
        game.win();
    }
}
