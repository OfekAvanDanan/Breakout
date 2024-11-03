package game.interfaces;
import java.util.List;

import gui.Point;
import gui.Velocity;
import gui.shapes.Rectangle;
import game.objects.Ball;

/**
 * The Collidable interface represents objects that can be collided with in a 2D
 * space.
 * It defines methods for obtaining the collision rectangle, handling hits, and
 * retrieving collision points.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.2024
 * @since 2024-21-01
 */
public interface Collidable {
    /**
     * Returns the collision rectangle of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred with it at a specific collision
     * point
     * and with a given velocity.
     * Returns the new velocity expected after the hit, based on the force the
     * object inflicted.
     *
     * @param hitter the ball the block got hit with
     * @param collisionPoint  the point of collision
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Gets the collision points of the object.
     * Returns a list of points representing the collision points.
     *
     * @return a list of collision points
     */
    List<Point> getCollisionPoints();

}
