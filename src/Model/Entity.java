package Model;

import View.Renderer;
import javafx.scene.shape.Shape;

/**
 * Classe abstraite pour représenter une entité sur le jeu.
 *
 * Une entité possède minimalement un Renderer et une position (x, y) définie
 * par rapport au niveau (où y=0 est tout en bas).
 */
public abstract class Entity {

    protected double x, y;
    protected Renderer renderer;

    /**
     * Constructeur de Entity
     *
     * @param x position horizontale dans le niveau
     * @param y position verticale dans le niveau
     */
    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Donne la position en x du coin haut gauche.
     *
     * @return Composante horizontale
     */
    public double getX() {
        return x;
    }

    /**
     * Donne la position en y du coin haut gauche.
     *
     * @return Composante verticale
     */
    public double getY() {
        return y;
    }

    /**
     * @return la largeur totale de l'entité
     */
    public abstract double getWidth();

    /**
     * @return la hauteur totale de l'entité
     */
    public abstract double getHeight();

    public Renderer getRepresentation() {
        return renderer;
    }

    /**
     * Fonction appelée à chaque frame pour mettre à jour les attributs de
     * l'entité.
     *
     * @param dt Delta-Temps en secondes
     */
    public abstract void tick(double dt);

    /**
     * Retourne le collision box de l'entité
     *
     * @return Shape représentant le collision box de l'entité
     */
    public abstract Shape getShape();
}
