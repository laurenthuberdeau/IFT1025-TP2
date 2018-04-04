package View;

import Model.Entity;
import Model.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Renderer affichant une animation.
 * Une animation est une suite d'images affichées de façon successives.
 *
 * N'utilise pas l'animation Timer puisqu'il n'est pas nécessaire.
 * Aussi, "linéarise" le dessinage dans le context.
 */
public class AnimationRenderer extends Renderer {

    // Nombre d'images de l'animation par seconde
    private int frameRate;

    // Entitée représentée par le renderer
    private Entity entity;

    // Images de l'animation, en ordre
    private List<Image> images;

    // Temps lors de la création de l'objet
    private long initialTime;

    /**
     * Contructeur d'Animation Renderer
     *
     * @param basePath Chemin d'accès des fichiers sans index et extension
     * @param frameCount Nombre d'image dans l'animation
     * @param frameRate Nombre d'images de l'animation par seconde
     * @param entity Entitée liée au renderer
     */
    public AnimationRenderer(String basePath, int frameCount, int frameRate, Entity entity) {
        this.frameRate = frameRate;
        this.entity = entity;

        this.initialTime = System.nanoTime();

        this.images = new ArrayList<>(frameCount);

        for (int i = 1; i <= frameCount; i++) {
            this.images.add(new Image("/Images/" + basePath + i + ".png"));
        }
    }

    @Override
    public void draw(Level level, GraphicsContext context) {
        double dt = (System.nanoTime() - initialTime) / Math.pow(10, 9); // En secondes

        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        int frame = (int) (dt * frameRate);
        Image image = images.get(frame % images.size());

        context.drawImage(image,
                x - entity.getWidth() / 2,
                y - entity.getHeight() / 2,
                entity.getWidth(),
                entity.getHeight());
    }
}
