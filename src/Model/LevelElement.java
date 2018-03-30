package Model;

import javafx.scene.shape.Shape;

/**
 * Éléments qui composent un niveau.
 *
 * Le joueur peut interagir avec toutes les objets de ce type en entrant en
 * collision avec.
 */
public abstract class LevelElement extends Entity {

    public LevelElement(double x, double y) {
        super(x, y);
    }

    public boolean intersects(Player player) {
        // todo : À optimiser. Utilise 100% du UI thread.
        return !Shape.intersect(player.getShape(), getShape())
                .getBoundsInLocal()
                .isEmpty();
    }

    public abstract void handleCollision(Player player, Game game);
}
