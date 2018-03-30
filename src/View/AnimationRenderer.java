package View;

import Model.Entity;
import Model.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class AnimationRenderer extends Renderer {

    private int frameRate;
    private Entity entity;

    private List<Image> images;

    private long lastTime;

    public AnimationRenderer(String basePath, int frameCount, int frameRate, Entity entity) {
        this.frameRate = frameRate;
        this.entity = entity;

        this.lastTime = System.nanoTime();

        this.images = new ArrayList<>(frameCount);

        for (int i = 1; i <= frameCount; i++) {
            this.images.add(new Image("/Images/" + basePath + i + ".png"));
        }
    }

    @Override
    public void draw(Level level, GraphicsContext context) {
        double dt = (System.nanoTime() - lastTime) / Math.pow(10, 9); // En secondes

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
