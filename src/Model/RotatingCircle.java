package Model;

import View.RotatingCircleRenderer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Obstacle simple : un cercle tournant autour d'un point
 */
public class RotatingCircle extends Obstacle {

    // Fréquence de l'oscillation
    static final private double frequency = 1/3f;

    private double circleRadius;
    private double pathRadius;

    private double radian = 0;

    // Temps depuis la création de l'objet
    private double timeSinceStart = 0;

    // Temps depuis le dernier changement de couleur
    private double timeSinceColorChange = 0;

    public RotatingCircle(double x, double y, double circleRadius, double pathRadius) {
        super(x, y);

        this.renderer = new RotatingCircleRenderer(this);

        this.circleRadius = circleRadius;
        this.pathRadius = pathRadius  - circleRadius / 2;

        this.color = (int) (Math.random() * 4);
    }

    @Override
    public double getWidth() {
        return circleRadius;
    }

    @Override
    public double getHeight() {
        return circleRadius;
    }

    @Override
    public double getX() {
        // Could optimise by not calculating Math.cos every call to getX...
        return super.getX() + Math.cos(radian) * pathRadius;
    }

    @Override
    public double getY() {
        // Could optimise by not calculating Math.cos every call to getY...
        return super.getY() + Math.sin(radian) * pathRadius;
    }

    @Override
    public Shape getShape() {
        return new Circle(getX(), getY(), getCircleRadius());
    }

    public double getCircleRadius() {
        return this.circleRadius;
    }

    @Override
    public void tick(double dt) {
        timeSinceStart += dt;

        double period = 2 * Math.PI * frequency;
        radian = timeSinceStart * period;

        timeSinceColorChange += dt;
        if (timeSinceColorChange > period) {
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
