package Model;

public class Level5 extends Level {

    public Level5(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Obstacle obstacle1 = new RotatingCircle(x, 0.75 * screenHeight, 40, screenWidth / 5);
        Obstacle obstacle2 = new RotatingCircle(x, 1.25 * screenHeight, 30, screenWidth / 4);
        Obstacle obstacle3 = new RotatingCircle(x, 1.75 * screenHeight, 20, screenWidth / 3);
        Obstacle obstacle4 = new RotatingCircle(x, 2.25 * screenHeight, 10, screenWidth / 2);
        Obstacle obstacle5 = new GrowingCircle(x, 2.25 * screenHeight, screenWidth / 6);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);

        victoryMushroom = new Mushroom(x, 2.75 * screenHeight);
    }

    @Override
    public int getLevelIndex() {
        return 5;
    }
}
