package View;

import Model.Entity;
import Model.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Fait le rendu d'une Entity sur l'écran en affichant une image
 */
public class ImageRenderer extends Renderer {

    private Image img;
    private Entity entity;

    /**
     * Constructeur de BouncingBallRenderer
     *
     * @param name Chemin d'accès de l'image
     * @param e Entitée liée au renderer
     */
    public ImageRenderer(String name, Entity e) {
        img = new Image("/Images/" + name + ".png");
        this.entity = e;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        context.drawImage(img,
                x - entity.getWidth() / 2,
                y - entity.getHeight() / 2,
                entity.getWidth(),
                entity.getHeight());
    }
}
