package game.objects;

import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import game.Game;
import game.GameEnvironment;
import game.interfaces.Collidable;
import game.interfaces.Sprite;
import gui.Point;
import gui.Velocity;
import gui.shapes.Rectangle;

/**
 * The Paddle class represents a paddle in the game.
 * It implements both the Sprite and Collidable interfaces.
 *
 * @author Ofek Avan Danan ofek.avandanan@live.biu.ac.il
 * @version 1.3
 * @since 2024-29-01
 */
public class Paddle implements Sprite, Collidable {
    private static final double PRESS_MOVE = 7;
    private static final int HIT_PARTS = 5;
    private static final double HIT_START_DEGREE = 30.01;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Collidable collidable;

    /**
     * Constructs a paddle with the specified parameters.
     *
     * @param location    the upper-left corner of the paddle
     * @param width       the width of the paddle
     * @param height      the height of the paddle
     * @param color       the color of the paddle
     * @param gui         the GUI object associated with the game
     * @param environment the game environment
     */
    public Paddle(Point location, double width, double height, java.awt.Color color, biuoop.GUI gui,
            GameEnvironment environment) {
        this.rectangle = new Rectangle(location, width, height, color);
        this.keyboard = gui.getKeyboardSensor();
        this.collidable = environment.getCollidablesList().get(1);
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        Rectangle frame = collidable.getCollisionRectangle();
        Point currentPosition = rectangle.getUpperLeft();
        if (frame.getStartX() < currentPosition.getX()) {
            this.rectangle.changePosition(new Point(currentPosition.getX() - PRESS_MOVE, currentPosition.getY()));
        } else {
            this.rectangle.changePosition(new Point(frame.getEndX() - rectangle.getWidth(), currentPosition.getY()));
        }
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        Rectangle frame = collidable.getCollisionRectangle();
        Point currentPosition = rectangle.getUpperLeft();
        if (collidable.getCollisionRectangle().getEndX() - rectangle.getWidth() > currentPosition.getX()) {
            this.rectangle.changePosition(new Point(currentPosition.getX() + PRESS_MOVE, currentPosition.getY()));
        } else {
            this.rectangle.changePosition(new Point(frame.getStartX(), currentPosition.getY()));
        }
    }

    // Sprite

    /**
     * Performs a time-based operation for the paddle.
     * Moves the paddle left if the left key is pressed and right if the right key
     * is pressed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on a given DrawSurface.
     *
     * @param d the DrawSurface to draw the paddle on
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    // Collidable

    /**
     * Gets the collision rectangle of the paddle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles a hit by calculating and returning the new velocity after the hit.
     * The paddle is divided into several parts, and the new velocity is determined
     * based on the hit location.
     *
     * @param hitter          the ball that hit the paddle
     * @param collisionPoint  the collision point with the paddle
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double posX = collisionPoint.getX() - this.rectangle.getStartX() - 1;
        double posY = collisionPoint.getY() - this.rectangle.getEndY() + 1;

        double part = rectangle.getWidth() / (HIT_PARTS);
        double degreeRange = 180 - HIT_START_DEGREE * 2;

        Velocity velocity = currentVelocity;
        double speed = currentVelocity.getSpeed();

        for (int i = 0; i < HIT_PARTS; i++) {
            if ((i + 0) * part <= posX && posX <= (i + 1) * part) {
                double degree = HIT_START_DEGREE + degreeRange / (HIT_PARTS - 1) * i;
                double angle = posY < 0 ? degree - 180 : 180 - degree;
                return Velocity.fromAngleAndSpeed(angle, speed);
            }
        }
        return velocity;
    }

    /**
     * Gets the collision points of the paddle.
     * Returns the four corner points of the rectangle.
     *
     * @return a list of collision points
     */
    @Override
    public List<Point> getCollisionPoints() {
        return this.rectangle.getPoints();
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game to which the paddle is added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
