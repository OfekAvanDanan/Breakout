package game.objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

import game.Game;
import game.interfaces.Collidable;
import game.interfaces.Sprite;
import game.interfaces.HitNotifier;
import game.interfaces.HitListener;
import gui.Point;
import gui.Velocity;
import gui.shapes.Rectangle;

/**
 * Represents a block in a 2D space.
 * A block is a rectangle that can be collided with.
 * It implements the Collidable interface and provides methods
 * to get its collision rectangle, handle hits, and obtain collision
 * points.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.3
 * @since 2024-23-01
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private List<HitListener> hitListeners = new ArrayList<>();
    private static final Color BACKGROUND_COLOR = new Color(33, 33, 33);

    private static final double EPSILON = 0.001;

    /**
     * Constructs a block with a specified rectangle.
     *
     * @param rect the rectangle representing the block
     */
    public Block(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Constructs a block with specified upper-left point, width, height, and color.
     *
     * @param upperLeft the upper-left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, int width, int height, java.awt.Color color) {
        this(new Rectangle(upperLeft, (double) width, (double) height, color));
    }

    /**
     * Gets the collision rectangle of the block.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Handles a hit by calculating and returning the new velocity after the hit.
     *
     * @param hitter          the ball that hit the block
     * @param collisionPoint  the collision point with the block
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double newDx = dx;
        double newDy = dy;

        List<Point> points = rect.getPoints();
        for (int i = 0; i < points.size(); i++) {
            if (touchDirectionEdge(points.get(i).getX(), collisionPoint.getX(), EPSILON)) {
                newDx = -dx;
            }
            if (touchDirectionEdge(points.get(i).getY(), collisionPoint.getY(), EPSILON)) {
                newDy = -dy;
            }
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return new Velocity(newDx, newDy);
    }

    private boolean touchDirectionEdge(double edge, double location, double threshold) {
        return (Math.abs(edge - location) < threshold);
    }

    /**
     * Draws the block on a given DrawSurface.
     *
     * @param d the DrawSurface to draw the block on
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rect.drawOn(d);
    }

    /**
     * Gets the collision points of the block.
     * Returns the four corner points of the rectangle.
     *
     * @return a list of collision points
     */
    @Override
    public List<Point> getCollisionPoints() {
        return this.rect.getPoints();
    }

    /**
     * Checks if two collidables are equal based on their collision rectangles.
     *
     * @param collidable the collidable to compare
     * @return true if the collision rectangles are equal, false otherwise
     */
    public boolean equals(Collidable collidable) {
        return this.rect.equals(collidable.getCollisionRectangle());
    }

    /**
     * Checks if the color of the ball matches the color of the block. If not, sets
     * the color of the ball to the color
     * of the block.
     *
     * @param ball the ball to check its color
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        if (!this.rect.getColor().equals(BACKGROUND_COLOR)) {
            if (ball.getColor().equals(this.rect.getColor())) {
                return true;
            } else {
                ball.setColor(this.rect.getColor());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Adds a hit listener to the block.
     *
     * @param hl the hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param hl the hit listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Performs a time-based operation for the block (no time-based operation for this class).
     */
    public void timePassed() {

    }

    /**
     * Adds the block to the game by adding it to the collidables and sprites lists.
     *
     * @param g the game to add the block to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

     /**
     * Removes the block from the game by removing it from the collidables and
     * sprites lists.
     *
     * @param g the game to remove the block from
     */
    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSpite(this);
    }

    /**
     * Notifies all hit listeners about a hit event.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
