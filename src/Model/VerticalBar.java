package Model;

import View.VerticalBarRenderer;

/**
 * Obstacle simple : une barre verticale allant de gauche à droite
 */
public class VerticalBar extends Obstacle {

    static final private double frequency = 1/3f;

    private double width;
    private double height;

    private double offset = 0;

    private double amplitude;
    private double timeSinceStart = 0;


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

    public double getOffset() {
        return offset;
    }

    @Override
    public void tick(double dt) {
        timeSinceStart += dt;

        double period = 2 * Math.PI * frequency;
        offset = amplitude * Math.sin(timeSinceStart * period);
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean intersects(Player player) {
        return this.color != player.getColor()
                && player.getX() < this.getX() + this.getWidth() / 2
                && player.getX() > this.getX() - this.getWidth() / 2
                && player.getY() < this.getY() + this.getHeight() / 2
                && player.getY() > this.getY() - this.getHeight() / 2;
    }
}
