package gui;

import java.util.ArrayList;
import java.util.List;

import gui.shapes.Rectangle;

/**
 * The Line class represents a line in a 2D space defined by two points.
 * It includes methods for calculating the length, middle point, intersection,
 * and more.
 *
 * @author Ofek Avan Danan ofek.avandanan@live.biu.ac.il
 * @version 1.22
 * @since 2024-01-09
 */
public class Line {
    private Point start;
    private Point end;

    private boolean isInfinite = false;
    private double a;
    private double b;

    /**
     * Constructs a Line with the specified start and end points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);

        // Get line function parameters
        if (this.start.getX() == this.end.getX()) {
            // in this case the slope of the line is infinite so we set it up differently
            this.isInfinite = true;
            this.a = 0;
            this.b = this.start.getX();
        } else {
            // Otherwise the line is normal, we set it parameters up like we did in 7 grade.
            if (this.start.getY() == 0 && this.end.getY() == 0) {
                // Make sure we don't get divide by 0 error.
                this.a = 0;
            } else {
                this.a = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            }
            this.b = this.start.getY() - this.a * this.start.getX();

        }
    }

    /**
     * Constructs a Line with specified coordinates for start and end points.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Returns the length of the line segment.
     *
     * @return the length of the line segment
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        // sums up the values and divide them by two.
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;

        return new Point(midX, midY);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point of the line
     */
    public Point start() {
        return new Point(this.start);
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the ending point of the line
     */
    public Point end() {
        return new Point(this.end);
    }

    /**
     * Checks if the line intersects with another line.
     *
     * @param other the other Line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // Check if there is one intersection point
        if (this.intersectionWith(other) != null) {
            return true;
        }
        // Check if there are more then 1 intersection points or none
        return this.infiniteIntersection(other);
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first other Line to check for intersection
     * @param other2 the second other Line to check for intersection
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Checks if the line intersects with another line.
     *
     * @param other the other Line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public Point intersectionWith(Line other) {
        return intersectionWith(other, true);
    }

    /**
     * Calculates the intersection point between this line and another line.
     *
     * <p>This method determines the intersection point between two lines in a
     * two-dimensional space.It considers various scenarios such as parallel lines,
     * infinite slopes, and
     * checks for intersection within line segments.
     *
     * <p>If both lines are infinite and have the same y-intercept, it is considered a
     * non-unique intersection, and null is returned.
     * Otherwise, the method checks for an intersection point with the infinite line
     * and ensures it lies within the line segment defined
     * by the non-infinite line. If one line is infinite and the other is not, the
     * method swaps the roles and invokes the other line's
     * {@code intersectionWith} method to reduce code duplication. If both lines are
     * non-infinite and have the same slope and y-intercept,
     * it is considered a non-unique intersection, and null is returned. Otherwise,
     * the method checks for an intersection point and ensures
     * it lies within the line segments defined by both lines. If the lines are
     * parallel and not equal, or if the slopes are equal but
     * y-intercepts are different, null is returned as the lines do not intersect.
     * If the lines are not parallel and not equal, the method
     * calculates the intersection point using the equations of the lines. It checks
     * if the intersection point lies within the line segments
     * defined by both lines, and if so, returns the intersection point. Otherwise,
     * null is returned.
     *
     * @param other  the other Line to find the intersection point with
     * @param inside flag indicating whether the intersection point should be inside
     *               the line segments
     * @return the intersection point if the lines intersect, otherwise null
     */
    public Point intersectionWith(Line other, boolean inside) {
        // Special treatment for isInfinite lines:
        if (this.isInfinite) {
            if (other.isInfinite) {
                // Check if the lines are parallel and have the same y-intercept
                if (this.b == other.b) {
                    // Return null if there is more than one point of intersection, otherwise check
                    // for corner points.
                    return this.infiniteIntersection(other) ? null : this.checkCornerPoints(other);
                } else {
                    return null; // Lines are parallel and do not intersect.
                }
            } else {
                // Check if the line has an intersection point with the infinite line.
                double x = b;
                double y = other.a * x + other.b;
                Point intersect = new Point(x, y);
                if (!inside || (inBetween(this.start, intersect, this.end, true)
                        && inBetween(other.start, intersect, other.end, true))) {
                    return intersect; // Return intersection point if within the line segment.
                } else {
                    return null; // No intersection within the line segment.
                }
            }
        } else if (other.isInfinite) {
            // Call the method with the infinite line to save code lines.
            return other.intersectionWith(this);
        } else if (this.a == other.a && this.b == other.b) {
            // if the functions are the same, Return intersection point if within the line
            // segment.
            return this.infiniteIntersection(other) ? null : this.checkCornerPoints(other);
        } else if (this.a == other.a && this.b != other.b) {
            // Lines are parallel and do not intersect.
            return null;
        } else {
            // Both lines are not vertical and not parallel.
            double x = (other.b - this.b) / (this.a - other.a);
            double y = this.a * x + this.b;

            Point intersect = new Point(x, y);

            boolean inThisLine = inBetween(this.start, intersect, this.end, true);
            boolean inOtherLine = inBetween(other.start, intersect, other.end, true);

            if (!inside || (inThisLine && inOtherLine)) {
                // Return intersection point if within both line segments.
                return intersect;
            }
        }
        // No intersection point found.
        return null;
    }

    /**
     * Checks for infinite intersection between this line and another line.
     *
     * @param other the other Line to check for infinite intersection
     * @return true if there is an infinite intersection, false otherwise
     */
    private boolean infiniteIntersection(Line other) {
        boolean between = inBetween(this.start, other.start, this.end, false)
                || inBetween(this.start, other.end, this.end, false);
        boolean inside = (inBetween(this.start, other.start, this.end, true))
                && (inBetween(this.start, other.end, this.end, true))
                || (inBetween(other.start, this.start, other.end, true)
                        && inBetween(other.start, this.end, other.end, true));
        return between || inside;
    }

    /**
     * Checks for corner points that are common to both this line and another line.
     *
     * @param other the other Line to check for corner points
     * @return the common corner point if found, otherwise null
     */
    private Point checkCornerPoints(Line other) {
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        } else if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        } else {
            return null;
        }
    }

    /**
     * Checks if a point is between two other points.
     *
     * @param a       the first point
     * @param b       the second point
     * @param c       the point to check
     * @param include flag indicating whether to include the boundaries
     * @return true if the point is between the two other points, false otherwise
     */
    private boolean inBetween(Point a, Point b, Point c, boolean include) {
        if (include) {
            if (!(b.getX() >= Math.min(a.getX(), c.getX()) && b.getX() <= Math.max(a.getX(), c.getX()))) {
                return false;
            }
            if (!(b.getY() >= Math.min(a.getY(), c.getY()) && b.getY() <= Math.max(a.getY(), c.getY()))) {
                return false;
            }
            return true;
        } else {
            if (!(b.getX() > Math.min(a.getX(), c.getX()) && b.getX() < Math.max(a.getX(), c.getX()))) {
                return false;
            }
            if (!(b.getY() > Math.min(a.getY(), c.getY()) && b.getY() < Math.max(a.getY(), c.getY()))) {
                return false;
            }
            return true;
        }
    }

    /**
     * Finds the closest intersection point to the start of the line within a
     * rectangle.
     *
     * @param rect the rectangle to check for intersection
     * @return the closest intersection point to the start of the line, or null if
     *         no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = new ArrayList<Point>();

        points.addAll(rect.intersectionPoints(this, false));

        if (points.size() == 0) {
            return null;
        } else {
            return this.start.closestPoint(points);
        }
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other Line to compare with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));

    }

    @Override
    public String toString() {
        return isInfinite ? "x=" + this.b : a == 0 ? "y=" + this.b : "y=" + this.a + "b";
    }
}