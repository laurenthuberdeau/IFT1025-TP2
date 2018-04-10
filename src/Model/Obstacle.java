package Model;

/**
 * Obstacle dans un niveau.
 *
 * Lorsque le joueur entre en collision avec un obstacle, la partie est
 * terminée.
 */
public abstract class Obstacle extends LevelElement {

    protected int color;

    public Obstacle(double x, double y) {
        super(x, y);
    }

    /**
     * Gestion de la collision entre le joueur et l'obstacle
     *
     * @param player Le joueur
     * @param game La partie en cours
     */
    @Override
    public void handleCollision(Player player, Game game) {
        game.lose();
    }

    /**
     * Détermine s'il y a collision entre this et player.
     * Overridé pour ignorer les collisions si le joueur est invincible
     *
     * @param player Joueur
     * @return Si le joueur rencontre l'objet
     */
    @Override
    public boolean intersects(Player player){
        if (player.getInvincibility()){
            return false;
        }
        else{
            return super.intersects(player);
        }
    }

    /**
     * Getter de color
     *
     * @return this.color
     */
    public int getColor() {
        return color;
    }
}
