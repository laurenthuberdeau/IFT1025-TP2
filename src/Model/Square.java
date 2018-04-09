package Model;

import View.SquareRenderer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Obstacle simple : un carré qui change de couleur à toutes les 2 secondes.
 */
public class Square extends Obstacle {

    // Dimensions de l'objet
    private double width;

    // Temps depuis le dernier changement de couleur
    private double timeSinceColorChange = 0;

    /**
     * Constructeur du carré, initialise renderer
     *
     * @param x Position horizontale
     * @param y Position verticale
     * @param longueur Dimension du carré
     */
    public Square(double x, double y, double longueur) {
        super(x, y);

        this.width = longueur;
        this.renderer = new SquareRenderer(this);

        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return width;
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
    public void tick(double dt) {
        timeSinceColorChange += dt;

        if (timeSinceColorChange > 2) {
            color = (color + 1) % 4;
            timeSinceColorChange = 0;
        }
    }

    @Override
    public boolean intersects(Player player) {
        return this.color != player.getColor()
                && super.intersects(player);
    }
}
