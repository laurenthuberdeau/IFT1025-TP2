package Model;

import View.GrowingCircleRenderer;

/**
 * Obstacle simple : un cercle qui change de couleur et qui grossit.
 */
public class GrowingCircle extends Obstacle {

    static final private double FREQUENCY = 1/3f;
    static final private double MIN_RATIO = 0.2;

    private double currentRadius = 0;

    private double maxRadius;
    private double timeSinceStart = 0;
    private double timeSinceColorChange = 0;

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
    public double getX() {
        return super.getX() - currentRadius;
    }

    @Override
    public double getY() {
        return super.getY() + currentRadius;
    }

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

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {
        if (player.getColor() == this.getColor())
            return false;

        double distance = Math.pow(player.getX() - super.getX(), 2)
                        + Math.pow(player.getY() - super.getY(), 2);

        double minDistance = Math.pow(player.getRadius() + this.getCurrentRadius(), 2);

        return distance <= minDistance;
    }
}
