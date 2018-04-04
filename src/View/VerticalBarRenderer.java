package View;

import Model.Level;
import Model.VerticalBar;
import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un VerticalBar en dessinant un rectangle coloré sur l'écran.
 */
public class VerticalBarRenderer extends Renderer {

    private VerticalBar bar;

    /**
     * Constructeur de VerticalBarRenderer
     *
     * @param c Entitée liée au renderer
     */
    public VerticalBarRenderer(VerticalBar c) {
        this.bar = c;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, bar.getY());

        context.setFill(Renderer.convertColor(bar.getColor()));

        context.fillRect(
                bar.getX() - bar.getWidth() / 2,
                canvasY - bar.getHeight() / 2,
                bar.getWidth(),
                bar.getHeight());
    }
}
