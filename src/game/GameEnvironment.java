package game;
import java.util.ArrayList;
import java.util.List;

import game.informative.CollisionInfo;
import game.interfaces.Collidable;
import gui.Point;
import gui.Line;
import gui.shapes.Rectangle;

/**
 * The GameEnvironment class represents the environment in which objects can
 * collide. It manages a collection of Collidable objects and provides methods
 * to add collidables, retrieve the list of collidables, and find the closest
 * collision point along a trajectory.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.2
 * @since 2024-21-01
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * Adds the given collidable to the environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable from the environment.
     *
     * @param c The collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets the list of collidables in the environment.
     *
     * @return List of collidables.
     */
    public List<Collidable> getCollidablesList() {
        return this.collidables;
    }



    /**
     * Assumes an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return
     * null. Else, return the information about the closest collision that is
     * going to occur.
     *
     * @param trajectory The line representing the movement trajectory.
     * @return CollisionInfo containing information about the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;
        Collidable closestObjectCollidable = null;

        // Iterate through collidables to find the closest collision point.
        for (Collidable collidable : this.collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            List<Point> points = rect.intersectionPoints(trajectory);

            if (!points.isEmpty()) {
                Point p = trajectory.start().closestPoint(points);
                double distance = trajectory.start().distance(p);

                if (distance < minDistance) {
                    closestObjectCollidable = collidable;
                    closestPoint = p;
                    minDistance = distance;
                }
            }
        }

        // Return CollisionInfo with information about the closest collision.
        return new CollisionInfo(closestPoint, closestObjectCollidable, minDistance);
    }
}
