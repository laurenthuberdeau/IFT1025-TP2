package View;

import Model.Level;
import Model.BouncingBall;
import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un BouncingBall en dessinant un cercle coloré sur l'écran.
 */
public class BouncingBallRenderer extends Renderer {

    // Entitée à afficher
    private BouncingBall circle;

    /**
     * Constructeur de BouncingBallRenderer
     *
     * @param c Entitée liée au renderer
     */
    public BouncingBallRenderer(BouncingBall c) {
        this.circle = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, circle.getY());

        context.setFill(Renderer.convertColor(circle.getColor()));

        context.fillOval(
                circle.getX() - circle.getRadius(),
                canvasY - circle.getRadius(),
                circle.getRadius() * 2,
                circle.getRadius() * 2);
    }
}
