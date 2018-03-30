package View;

import Model.GrowingCircle;
import Model.Level;
import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un GrowingSquare en dessinant un cercle coloré sur l'écran.
 */
public class GrowingCircleRenderer extends Renderer {

    private GrowingCircle circle;

    public GrowingCircleRenderer(GrowingCircle c) {
        this.circle = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, circle.getY());

        context.setFill(Renderer.convertColor(circle.getColor()));

        context.fillOval(
                circle.getX(),
                canvasY,
                circle.getCurrentRadius() * 2,
                circle.getCurrentRadius() * 2);
    }
}
