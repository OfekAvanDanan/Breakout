import game.Game;

/**
 * The Game class represents the main class for running the game.
 * It initializes the game elements, such as the blocks, ball, and paddle, and
 * manages the game loop.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.1
 * @since 2024-02-02
 */
public class BreakOut {
    /**
     * Main method to start the game.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
