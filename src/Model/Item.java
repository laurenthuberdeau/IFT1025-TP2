package Model;

/**
 * Classe abstraite représentant un item (powerup/champignon/...)
 */
public abstract class Item extends LevelElement {

    /**
     * Constructeur de Item, qui réfère au constructeur du parent LevelElement
     *
     * @param x position horizontale dans le niveau
     * @param y  position verticale dans le niveau
     */
    public Item(double x, double y) {
        super(x, y);
    }
}
