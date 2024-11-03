package gui;
/**
 * A class representing a 2D velocity with components dx and dy.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.5
 * @since 2024-01-17
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a velocity with the given dx and dy components.
     *
     * @param dx the x-component of the velocity
     * @param dy the y-component of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a velocity from an angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed of the velocity
     * @return the new velocity created from the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double rad = Math.toRadians(angle);
        double dx = Math.cos(rad) * speed;
        double dy = Math.sin(rad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Applies the velocity to a given point, returning a new point.
     *
     * @param p the point to apply the velocity to
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;

        return new Point(x, y);
    }

    /**
     * Gets the x-component of the velocity.
     *
     * @return the x-component of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the y-component of the velocity.
     *
     * @return the y-component of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Gets the speed of the velocity.
     *
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * Checks if this velocity is equal to another velocity.
     *
     * @param other the other velocity to compare
     * @return true if the velocities are equal, false otherwise
     */
    public boolean equals(Velocity other) {
        return this.dx == other.dx && this.dy == other.dy;
    }
}
