package Model;

public class Level4 extends Level {

    public Level4(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Obstacle obstacle1 = new VerticalBar(x, 1.00 * screenHeight, 20, 256, screenWidth / 2);
        Obstacle obstacle2 = new VerticalBar(x, 2.50 * screenHeight, 20, 512, screenWidth / 2);
        Obstacle obstacle3 = new VerticalBar(x, 5.50 * screenHeight, 20, 1024, screenWidth / 2);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);

        // Création des items
        Shield shield1 = new Shield(x, 1.00 * screenHeight);
        Shield shield2 = new Shield(x, 2.50 * screenHeight);
        Shield shield3 = new Shield(x, 5.00 * screenHeight);

        items.add(shield1);
        items.add(shield2);
        items.add(shield3);

        victoryMushroom = new Mushroom(x, 7.00 * screenHeight);
    }

    @Override
    public int getLevelIndex() {
        return 4;
    }
}
