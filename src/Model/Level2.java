package Model;

public class Level2 extends Level {

    public Level2(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles

        Square obstacle1 = new Square(x, 0.75 * screenHeight, 40);
        Square obstacle2 = new Square(x, 1.50 * screenHeight, 60);
        Square obstacle3 = new Square(x, 2.25 * screenHeight, 150);
        Obstacle obstacle5 = new VerticalBar(x, 1.00 * screenHeight, 20, 70, screenWidth / 2);
        Obstacle obstacle6 = new VerticalBar(x, 1.75 * screenHeight, 40, 80, screenWidth / 2);
        Obstacle obstacle7 = new VerticalBar(x, 2.75 * screenHeight, 80, 90, screenWidth / 2);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);

        victoryMushroom = new Mushroom(x, 4.50 * screenHeight);
    }

    @Override
    public int getLevelIndex() {
        return 2;
    }
}
