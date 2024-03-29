package View;

import Model.Level;
import Model.RotatingCircle;
import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un RotatingCircle en dessinant un cercle coloré sur l'écran.
 */
public class RotatingCircleRenderer extends Renderer {

    private RotatingCircle circle;

    /**
     * Constructeur de RotationCircleRenderer
     *
     * @param c Entitée liée au renderer
     */
    public RotatingCircleRenderer(RotatingCircle c) {
        this.circle = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, circle.getY());

        context.setFill(Renderer.convertColor(circle.getColor()));

        context.fillOval(
                circle.getX() - circle.getCircleRadius(),
                canvasY - circle.getCircleRadius(),
                circle.getCircleRadius() * 2,
                circle.getCircleRadius() * 2);
    }
}
