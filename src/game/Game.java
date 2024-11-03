package game;

import java.awt.Color;

import biuoop.Sleeper;
import biuoop.DrawSurface;
import biuoop.GUI;

import gui.Point;
import gui.shapes.Rectangle;
import game.informative.Counter;
import game.interfaces.Collidable;
import game.interfaces.Sprite;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.ScoreTrackingListener;
import game.objects.Ball;
import game.objects.Block;
import game.objects.Paddle;
import game.objects.ScoreIndicator;

/**
 * The Game class represents the main class for running the game.
 * It initializes the game elements, such as the blocks, ball, and paddle, and
 * manages the game loop.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.7
 * @since 2024-21-01
 */
public class Game {
    private static final String GAME_NAME = "ass5 - game";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MARGINS = 30;

    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int UPPER_LEVEL_BLOCKS = 12;
    private static final int BLOCK_SPACING = 0;

    private static final int PADDLE_WIDTH = 120;
    private static final int PADDLE_HEIGHT = 5;
    private static final int PADDLE_START = 30;

    private static final int START_SPEED = 7;
    private static final int BALL_RADIUS = 7;
    private static final int BALL_SPACING = 32;

    private static final int SCORE_SIZE = 17;
    private static final int SCORE_X = WIDTH / 2 - 40;
    private static final int SCORE_Y = SCORE_SIZE + 5;

    private static final Color BLACK_COLOR = new Color(33, 33, 33);
    private static final Color[] COLORS_ARRAY = new Color[] {new Color(0, 145, 234), new Color(0, 200, 83),
            new Color(197, 17, 98), new Color(255, 214, 0)};
    private static final Color WHITE_COLOR = new Color(238, 238, 238);

    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui = new GUI(GAME_NAME, WIDTH, HEIGHT);
    private Sleeper sleeper = new Sleeper();
    private Counter blockCounter = new Counter();
    private Counter ballsCounter = new Counter();
    private Counter scoreCounter = new Counter();

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite object to be added
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Creates a basic triangle shape of blocks and adds them to the game.
     *
     * @param size the number of blocks in the upper level
     */
    private void basicTriangleShapeBlocks(int size) {
        int level = 0;
        int numberOfBlocks = size;
        while (numberOfBlocks > 0) {
            int startY = 0 + MARGINS * 2;
            int startX = (WIDTH - numberOfBlocks * BLOCK_WIDTH + (level * 2) * BLOCK_SPACING) / 2;
            for (int i = 0; i < numberOfBlocks; i++) {
                double x = startX + (BLOCK_WIDTH + BLOCK_SPACING) * i;
                double y = startY + (BLOCK_HEIGHT + BLOCK_SPACING) * level;
                Block block = new Block(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT,
                        COLORS_ARRAY[level % COLORS_ARRAY.length]);

                block.addHitListener(new BlockRemover(this, this.blockCounter));
                block.addHitListener(new ScoreTrackingListener(scoreCounter));
                block.addToGame(this);
            }
            this.blockCounter.increase(numberOfBlocks);
            numberOfBlocks -= 2;
            level++;
        }

    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite object from the game.
     *
     * @param s the sprite object to be removed
     */
    public void removeSpite(Sprite s) {
        sprites.removeSpite(s);
    }

    /**
     * Initializes the game by creating and adding the blocks, ball, and paddle.
     */
    public void initialize() {
        // Frame Block
        Block frameBlock = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT, WHITE_COLOR));
        frameBlock.addToGame(this);

        // Game Block
        Block gameBlock = new Block(new Rectangle(new Point(MARGINS, MARGINS), WIDTH - MARGINS * 2,
                HEIGHT - MARGINS * 2, BLACK_COLOR));
        gameBlock.addToGame(this);

        // Death Block
        Block deathBlock = new Block(new Rectangle(new Point(MARGINS, HEIGHT - MARGINS - 1),
                WIDTH - MARGINS * 2, 1, BLACK_COLOR));
        deathBlock.addHitListener(new BallRemover(this, this.ballsCounter));
        deathBlock.addToGame(this);

        // Triangle shape
        basicTriangleShapeBlocks(UPPER_LEVEL_BLOCKS);

        // Balls
        Ball ball1 = new Ball(new Point(WIDTH / 2 + BALL_SPACING, HEIGHT / 2), BALL_RADIUS, WHITE_COLOR, environment);
        ball1.setVelocity(0, START_SPEED);
        sprites.addSprite(ball1);

        Ball ball2 = new Ball(new Point(WIDTH / 2 - BALL_SPACING, HEIGHT / 2), BALL_RADIUS, WHITE_COLOR, environment);
        ball2.setVelocity(0, START_SPEED);
        sprites.addSprite(ball2);

        Ball ball3 = new Ball(new Point(WIDTH / 2, HEIGHT / 2 + 1.5), BALL_RADIUS, WHITE_COLOR, environment);
        ball3.setVelocity(0, START_SPEED);
        sprites.addSprite(ball3);

        ballsCounter.increase(3);

        // Paddle
        Point paddleLocation = new Point((WIDTH - PADDLE_WIDTH) / 2, HEIGHT - PADDLE_START * 2);
        Paddle paddle = new Paddle(paddleLocation, PADDLE_WIDTH, PADDLE_HEIGHT,
                WHITE_COLOR, gui, environment);
        paddle.addToGame(this);

        // score
        ScoreIndicator score = new ScoreIndicator(SCORE_X, SCORE_Y, scoreCounter, SCORE_SIZE, BLACK_COLOR);
        sprites.addSprite(score);
    }

    /**
     * Runs the game loop, managing the animation and timing.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        Boolean win = false;
        Boolean lost = false;

        while (!win && !lost) {
            // check if the player won
            if (this.blockCounter.getValue() == 0) {
                // Update the score
                scoreCounter.increase(100);
                // Exit
                win = true;
            }

            // Check if the player lost the game
            if (this.ballsCounter.getValue() == 0) {
                lost = true;
            }

            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

        if (win || lost) {
            gui.close();
        }

    }
}
