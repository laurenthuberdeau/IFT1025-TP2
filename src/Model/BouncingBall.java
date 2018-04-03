package Model;

import View.BouncingBallRenderer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Classe représentant une balle de rayon, couleur, vitesse et direction aléatoires
 * qui accélère vers le bas. Une méthode horizontalBounce permet de simuler
 * le rebond de la balle sur des "parois" verticales.
 */
public class BouncingBall extends Entity {

    private double radius;
    private double vx, vy, ay;
    private int color;

    public BouncingBall(double x, double y, double r) {
        super(x, y);

        this.radius = r * (Math.random() + 1) / 2;
        this.color = (int) Math.floor(4 * Math.random());

        this.vx = (Math.random() - 0.5) * 1200;
        this.vy = (Math.random() - 0.5) * 1200;
        this.ay = -400;

        this.renderer = new BouncingBallRenderer(this);
    }


    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité
     *
     * @param dt Delta-Temps en secondes
     */
    @Override
    public void tick(double dt) {

        // Mise à jour de la vitesse
        vy += dt * ay;

        // Mise à jour de la position
        x += dt * vx;
        y += dt * vy;
    }

    public void horizontalBounce() {
        this.vx = -this.vx;
    }

    public int getColor() {
        return color;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getWidth() {
        return this.getRadius() * 2;
    }

    @Override
    public double getHeight() {
        return this.getRadius() * 2;
    }

    @Override
    public Shape getShape() {
        return new Circle(getX(), getY(), getRadius());
    }
}
