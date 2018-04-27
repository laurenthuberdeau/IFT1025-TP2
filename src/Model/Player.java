package Model;

import View.PlayerRenderer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Classe représentant l'entité de la personne qui joue (aka, la sorcière).
 *
 * La sorcière est représentée par un cercle.
 */
public class Player extends Entity {

    private double radius;
    private double vy;
    private double ay;
    private double invincibilityTimer;
    private int color = 1;
    private boolean debugMode;

    public Player(double x, double y, double r) {
        super(x, y);

        this.radius = r;

        this.vy = 0;
        this.ay = -400;

        this.invincibilityTimer = 0;
        this.debugMode = false;

        this.renderer = new PlayerRenderer(this);
    }

    /**
     * Getter de radius
     *
     * @return this.radius
     */
    public double getRadius() {
        return radius;
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
        //System.out.println("player vy "+vy);

        // Mise à jour de la position
        y += dt * vy;

        // Clip la vitesse pour rester entre -300 et 300
        vy = Math.min(vy, 300);
        vy = Math.max(vy, -300);

        if (invincibilityTimer > 0){
            if (dt > invincibilityTimer){
                invincibilityTimer = 0;
            }
            else{
                invincibilityTimer -= dt;
            }
        }
    }

    /**
     * Getter de color (entier 0-4)
     *
     * @return this.color
     */
    public int getColor() {
        return color;
    }

    /**
     * Remplace la couleur actuelle par une nouvelle couleur aléatoire
     */
    public void randomizeColor() {
        int newColor;

        do {
            newColor = (int) (Math.random() * 4);
        } while (newColor == this.color);

        this.color = newColor;
    }

    /**
     * Fait sauter la sorcière
     */
    public void jump() {
        vy = Math.max(vy, 0);
        vy += 200;
    }

    /**
     * Initialise une période au cours de laquelle les collisions
     * avec les obstacles ne seront pas prises en compte
     *
     * @param invincibilityTimer durée en secondes
     */
    public void setInvincibilityTimer(double invincibilityTimer){
        this.invincibilityTimer = invincibilityTimer;
    }

    /**
     * Vérifie si le joueur est présentement invincible
     *
     * @return un booléen, vrai si un "shield" est actif (timer non-nul) ou si le mode de déboguage est activé
     */
    public boolean getInvincibility(){
        return (this.invincibilityTimer != 0)||this.debugMode;
    }

    /**
     * Méthode qui active/desactive le mode de déboguage au niveau du joueur, ce qui le rend invincible
     * (indépendamment de la variable utilisée pour le shield)
     */
    public void debugModeToggle() {
        this.debugMode=!this.debugMode;
    }

    /**
     * Setter de la position verticale du joueur
     *
     * @param y position verticale dans le niveau
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Fait disparaitre le joueur de l'affichage en lui donnant
     * un renderer null qui sera ignoré lors de l'affichage
     */
    public void disappear() {
        this.renderer = null;
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
