package Model;

/**
 * Troisième niveau du jeu, introduit les obstacles de type GrowingCircle ainsi que Rotating Circle
 * Introduit aussi le shield, qui est indispensable pour passer les obstacles 5-10.
 */
public class Level3 extends Level {

    public Level3(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Obstacle obstacle1 = new GrowingCircle(x, 0.75 * screenHeight, screenWidth / 6);
        Obstacle obstacle2 = new GrowingCircle(x, 1.50 * screenHeight, screenWidth / 4);
        Obstacle obstacle3 = new GrowingCircle(x, 2.25 * screenHeight, screenWidth / 6);
        Obstacle obstacle5 = new GrowingCircle(x, 3.00 * screenHeight, screenWidth / 6);
        Obstacle obstacle6 = new RotatingCircle(x, 3.00 * screenHeight, 20, screenWidth / 2);
        Obstacle obstacle7 = new GrowingCircle(x, 3.50 * screenHeight, screenWidth / 4);
        Obstacle obstacle8 = new RotatingCircle(x, 3.50 * screenHeight, 20, screenWidth / 1.75);
        Obstacle obstacle9 = new GrowingCircle(x, 4.00 * screenHeight, screenWidth / 2);
        Obstacle obstacle10 = new RotatingCircle(x, 4.00 * screenHeight, 20, screenWidth / 1.5);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);
        obstacles.add(obstacle8);
        obstacles.add(obstacle9);
        obstacles.add(obstacle10);

        // Création des items
        Potion potion1 = new Potion(x, 1.25 * screenHeight);
        Potion potion2 = new Potion(x, 2.00 * screenHeight);
        Shield shield1 = new Shield(x, 2.75 * screenHeight);

        items.add(potion1);
        items.add(potion2);
        items.add(shield1);

        victoryMushroom = new Mushroom(x, 4.50 * screenHeight);
    }

    @Override
    public int getLevelIndex() {
        return 3;
    }
}
