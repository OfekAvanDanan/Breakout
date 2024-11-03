package game.informative;

import game.interfaces.Collidable;
import gui.Point;

/**
 * The CollisionInfo class represents information about a collision between a
 * point and a collidable object.
 * It includes the collision point and the collidable object involved in the
 * collision.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.1
 * @since 2024-21-01
 */
public class CollisionInfo {
    private Point point;
    private Collidable object;
    private double distance;

    /**
     * Constructs a CollisionInfo with the specified collision point, collidable
     * object, and distance.
     *
     * @param point    the point at which the collision occurs
     * @param object   the collidable object involved in the collision
     * @param distance the distance from some reference point
     */
    public CollisionInfo(Point point, Collidable object, double distance) {
        this.point = point;
        this.object = object;
        this.distance = distance;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.object;
    }

    /**
     * Returns the distance associated with this collision.
     *
     * @return the distance from some reference point
     */
    public double distance() {
        return this.distance;
    }
}
