import java.util.List;

import Model.Entity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * TP2: Copie du jeu Color(Sw|sW)itch
 * Par Simon Chabot et Laurent Huberdeau
 *
 * Classe principale. Définit la vue.
 */
public class ColorsWitch extends Application {

    public static final double WIDTH = 320, HEIGHT = 480;
    private static final double TEXT_TIMEOUT = 3 * Math.pow(10, 9); // in nanoseconds
    private static final String WON_LEVEL_TEXT = "Level ";
    private static final String LOST_LEVEL_TEXT = "Better luck next time!";

    private Controller controller;
    private GraphicsContext context;

    private double lastGameOver = 0;
    private boolean hasWon = false;
    private Text messageTextView;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Point d'entrée de l'application JavaFX
     *
     * @param primaryStage Stage de la fenêtre principale
     */
    @Override
    public void start(Stage primaryStage) {
        controller = new Controller();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        messageTextView = new Text();
        messageTextView.setFont(new Font(18));
        messageTextView.setFill(Color.WHITE);

        Pane root = new StackPane(canvas, messageTextView);

        context = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                if (controller.gameIsOver()) {
                    lastGameOver = now;
                    hasWon = controller.gameHasWon();
                    String message = hasWon
                        ? WON_LEVEL_TEXT + (controller.getCurrentLevel().getLevelIndex() + 1)
                        : LOST_LEVEL_TEXT;

                    messageTextView.setText(message);
                }

                controller.tick((now - lastTime) * 1e-9);

                context.setFill(Color.BLACK);
                context.fillRect(0, 0, WIDTH, HEIGHT);

                List<Entity> entities = controller.getEntities();

                for (Entity e : entities) {
                    // Permet aux objets de disparaitre de l'affichage lorsque leur représentation est "null"
                    if (e.getRepresentation()!=null){
                        e.getRepresentation().draw(controller.getCurrentLevel(), context);
                    }
                }

                messageTextView.setVisible(now < lastGameOver + TEXT_TIMEOUT);

                lastTime = now;
            }
        };
        timer.start();

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.SPACE) {
                lastGameOver = 0; // Hide text
                controller.spacePressed();
            }
            if (event.getCode() == KeyCode.TAB) {
                controller.tabPressed();
            }
        });

        primaryStage.setTitle("Colors Witch");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
