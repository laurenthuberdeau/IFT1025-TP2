package Model;

/**
 * Sixième niveau. Il est intentionnellement insurmontable,
 * car les niveaux supérieures à 6 n'existent pas dans cette version du jeu.
 */
public class Level6 extends Level {

    public Level6(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);

        double x = screenWidth / 2;

        // Création des obstacles
        Square obstacle1 = new Square(screenWidth / 2, screenHeight * 2, screenHeight * 1.9);

        obstacles.add(obstacle1);

        // Création des items
        Potion potion1 = new Potion(x, 0.75 * screenHeight);

        items.add(potion1);

        victoryMushroom = new Mushroom(screenWidth / 2, 100 * screenHeight);
    }

    @Override
    public int getLevelIndex() {
        return 6;
    }
}
