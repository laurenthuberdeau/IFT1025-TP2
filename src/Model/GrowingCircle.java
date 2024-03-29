package Model;

import View.GrowingCircleRenderer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Obstacle simple : un cercle qui change de couleur et qui grossit.
 */
public class GrowingCircle extends Obstacle {

    // Fréquence de l'oscillation
    static final private double FREQUENCY = 1/3f;
    // Rayon minimal de l'oscillation. Le ratio oscille entre MIN_RATIO * maxRadius et maxRadius
    static final private double MIN_RATIO = 0.2;

    private double currentRadius = 0;

    private double maxRadius;

    // Temps depuis la création de l'objet
    private double timeSinceStart = 0;

    // Temps depuis le dernier changement de couleur
    private double timeSinceColorChange = 0;

    /**
     * Constructeur de GrowingCircle
     *
     * @param x Position horizontale du centre de l'objet
     * @param y Position verticale du centre de l'objet
     * @param maxRadius Rayon maximal
     */
    public GrowingCircle(double x, double y, double maxRadius) {
        super(x, y);

        this.maxRadius = maxRadius;
        this.renderer = new GrowingCircleRenderer(this);

        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return maxRadius;
    }

    @Override
    public double getHeight() {
        return maxRadius;
    }

    @Override
    public Shape getShape() {
        return new Circle(getX(), getY(), currentRadius);
    }

    /**
     * Getter de currentRadius
     *
     * @return this.currentRadius
     */
    public double getCurrentRadius() {
        return currentRadius;
    }

    @Override
    public void tick(double dt) {
        timeSinceStart += dt;

        double period = 2 * Math.PI * FREQUENCY;
        double amplitude = maxRadius * (1 - MIN_RATIO);
        double min = maxRadius * MIN_RATIO;
        currentRadius = amplitude * Math.abs(Math.sin(timeSinceStart * period)) + min;

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
