package Model;

/**
 * Premier niveau du jeu, introduit les obstacles carrés et les potions
 */
public class Level1 extends Level {

    public Level1(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle1 = new Square(x, 0.75 * screenHeight, 40);
        Square obstacle2 = new Square(x, 1.5 * screenHeight, 60);
        Square obstacle3 = new Square(x, 2.0 * screenHeight, 150);
        Square obstacle4 = new Square(x, 3 * screenHeight, 200);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);

        // Création des items
        Potion potion1 = new Potion(x, 1.15 * screenHeight);
        Potion potion2 = new Potion(x, 2.6 * screenHeight);

        items.add(potion1);
        items.add(potion2);

        victoryMushroom = new Mushroom(x, 3.5 * screenHeight);
    }

    @Override
    public int getLevelIndex() {
        return 1;
    }
}
