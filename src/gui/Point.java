package gui;

import java.util.List;

/**
 * This class represent a point in a 2D space.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.2
 * @since 2024-01-09
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a Point with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new Point with the a different Point.
     *
     * @param p the point that have the coordinate of the point
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Calculates and returns the Euclidean distance between this point and another
     * point.
     *
     * @param other the other Point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double deltaX = this.x - other.getX();
        double deltaY = this.y - other.getY();

        double squareX = deltaX * deltaX;
        double squareY = deltaY * deltaY;

        double root = Math.sqrt(squareX + squareY);

        return root;
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other Point to compare with
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other != null && this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;

    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x the new x-coordinate value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y the new y-coordinate value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Finds and returns the closest point to the current point from a list of
     * points.
     * The method calculates the distance between the current point and each point
     * in the list and returns the point with the minimum distance.
     *
     * @param points a list of points to compare with the current point
     * @return the closest point to the current point from the list, or null if the
     *         list is empty
     */
    public Point closestPoint(List<Point> points) {
        Point minPoint = null;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < points.size(); i++) {
            double distance = points.get(i).distance(this);
            if (distance < minDistance) {
                minPoint = points.get(i);
                minDistance = distance;
            }
        }
        return minPoint;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

}