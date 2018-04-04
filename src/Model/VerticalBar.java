package Model;

import View.VerticalBarRenderer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Obstacle simple : une barre verticale allant de gauche à droite
 */
public class VerticalBar extends Obstacle {

    // Fréquence de l'oscillation
    static final private double frequency = 1/3f;

    // Dimensions de l'objet
    private double width;
    private double height;

    // Position offset calculée dans tick
    private double offset = 0;

    // Largeur de l'oscillation de la barre
    private double amplitude;

    // Temps depuis la création de l'objet
    private double timeSinceStart = 0;

    /**
     * Constructeur de VerticalBar
     *
     * @param x Position horizontale du centre
     * @param y Position verticale du centre
     * @param width Largeur de la barre
     * @param height Hauteur de la barre
     * @param amplitude Largeur de l'oscillation de la barre
     */
    public VerticalBar(double x, double y, double width, double height, double amplitude) {
        super(x, y);

        this.renderer = new VerticalBarRenderer(this);

        this.width = width;
        this.height = height;
        this.amplitude = amplitude - width / 2;

        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getX() {
        return super.getX() + offset;
    }

    @Override
    public Shape getShape() {
        return new Rectangle(
                getX()  - getWidth() / 2,
                getY()  - getHeight() / 2,
                getWidth(),
                getHeight());
    }

    @Override
    public void tick(double dt) {
        timeSinceStart += dt;

        double period = 2 * Math.PI * frequency;
        offset = amplitude * Math.sin(timeSinceStart * period);
    }

    @Override
    public boolean intersects(Player player) {
        return this.color != player.getColor()
                && super.intersects(player);
    }
}
