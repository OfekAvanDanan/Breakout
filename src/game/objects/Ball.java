package game.objects;

import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;

import game.Game;
import game.GameEnvironment;
import game.informative.CollisionInfo;
import game.interfaces.Sprite;
import gui.Point;
import gui.Velocity;
import gui.Line;

/**
 * Represents a ball in a 2D space.
 * A ball has a center point, radius, color, and velocity.
 * It also includes methods for drawing, setting velocity, and moving the ball.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 117.2025
 * @since 2024-21-01
 */
public class Ball implements Sprite {
   private Point point;
   private int r;
   private java.awt.Color color;
   private Velocity velocity;
   private GameEnvironment gameEnvironment;

   private static final int MIN_RADIUS = 5;
   private static final int MAX_SPEED_RADIUS = 60;
   private static final double RAMP = 0.1;
   private static final double MIN_SPEED = 10;
   private static final double EPSILON = 0.0001;
   private static final double ANGLE_RANGE = 360.0;

   /**
    * Constructs a ball with a specified center point, radius, and color.
    *
    * @param point           the center point of the ball
    * @param r               the radius of the ball
    * @param color           the color of the ball
    * @param gameEnvironment the environment of colliding object
    */
   public Ball(Point point, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
      this.point = point;
      this.r = Math.max(Math.abs(r), MIN_RADIUS);
      this.color = color;
      this.gameEnvironment = gameEnvironment;

      this.velocity = new Velocity(0, 0);
   }

   /**
    * Constructs a ball with specified coordinates, radius, and color.
    *
    * @param x               the x-coordinate of the center
    * @param y               the y-coordinate of the center
    * @param r               the radius of the ball
    * @param color           the color of the ball
    * @param gameEnvironment the environment of colliding object
    */
   public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
      this(new Point((double) x, (double) y), Math.abs(r), color, gameEnvironment);
   }

   // Accessor methods

   /**
    * Gets the center point of the ball.
    *
    * @return the center point of the ball
    */
   public Point getPoint() {
      return new Point(this.point);
   }

   /**
    * Gets the x-coordinate of the center point.
    *
    * @return the x-coordinate of the center point
    */
   public double getX() {
      return this.point.getX();
   }

   /**
    * Gets the y-coordinate of the center point.
    *
    * @return the y-coordinate of the center point
    */
   public double getY() {
      return this.point.getY();
   }

   /**
    * Sets the x-coordinate of the center point.
    *
    * @param x the new x-coordinate value
    */
   public void setX(double x) {
      this.point.setX(x);
   }

   /**
    * Sets the y-coordinate of the center point.
    *
    * @param y the new y-coordinate value
    */
   public void setY(double y) {
      this.point.setY(y);
   }

   /**
    * Sets the point of the object.
    *
    * @param p the new point to set
    */
   public void setPoint(Point p) {
      this.point.setX(p.getX());
      this.point.setY(p.getY());
   }

   /**
    * Gets the radius of the ball.
    *
    * @return the radius of the ball
    */
   public int getSize() {
      return r;
   }

   /**
    * Sets the radius of the ball.
    *
    * @param radius the new radius of the ball
    */
   public void setSize(int radius) {
      this.r = Math.max(Math.abs(radius), MIN_RADIUS);
   }

   /**
    * Gets the color of the ball.
    *
    * @return the color of the ball
    */
   public java.awt.Color getColor() {
      return this.color;
   }

   // Drawing method

   /**
    * Draws the ball on a given DrawSurface.
    *
    * @param surface the DrawSurface on which to draw the ball
    */
   @Override
   public void drawOn(DrawSurface surface) {
      surface.setColor(this.getColor());
      surface.fillCircle((int) this.getX(), (int) this.getY(), this.getSize());
   }

   // Velocity methods

   /**
    * Sets the velocity of the ball.
    *
    * @param v the new velocity of the ball
    */
   public void setVelocity(Velocity v) {
      this.velocity = v;
   }

   /**
    * Sets the velocity of the ball.
    *
    * @param dx the delta for x in every frame.
    * @param dy the delta for y in every frame.
    */
   public void setVelocity(double dx, double dy) {
      setVelocity(new Velocity(dx, dy));
   }

   /**
    * Gets the current velocity of the ball.
    *
    * @return the velocity of the ball
    */
   public Velocity getVelocity() {
      return this.velocity;
   }

   /**
    * Gets the x-component of the ball's velocity.
    *
    * @return the x-component of the velocity
    */
   public double getDx() {
      return this.getVelocity().getDx();
   }

   /**
    * Gets the y-component of the ball's velocity.
    *
    * @return the y-component of the velocity
    */
   public double getDy() {
      return this.getVelocity().getDy();
   }

   /**
    * Sets the color of the ball.
    * Creates a new color object with the same RGB values as the provided color.
    *
    * @param c the color to set for the ball
    */
   public void setColor(Color c) {
      this.color = new Color(c.getRGB());
   }

   /**
    * Moves the ball one step according to its current velocity.
    * Handles collisions with objects in the specified GameEnvironment.
    */
   public void moveOneStep() {
      Point nextPoint = new Point(this.getX() + this.getDx(), this.getY() + this.getDy());
      Line trajectory = new Line(this.point, nextPoint);
      CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);

      if (collision.collisionPoint() != null) {
         this.point = collision.collisionPoint();
         this.velocity = collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity);
         this.point = new Velocity(this.getDx() * EPSILON, this.getDy() * EPSILON).applyToPoint(this.point);
      } else {
         this.point = this.getVelocity().applyToPoint(this.point);
      }
   }

   @Override
   public void timePassed() {
      moveOneStep();
   }

   /**
    * Sets a random speed and direction for the ball within specified limits.
    * The speed is determined based on the ball's radius, ensuring it stays within
    * a defined range.
    */
   public void setSpeed() {
      // Calculate the maximum allowed speed based on the RAMP and minimum speed

      // Calculate the speed within the allowed range
      double speed = MIN_SPEED + (MAX_SPEED_RADIUS - this.r) * RAMP;

      // Generate a random angle between 0 and 360 degrees
      Random rand = new Random();
      double angle = rand.nextDouble() * ANGLE_RANGE;

      // Set the ball's velocity based on the calculated angle and speed
      this.fromAngleAndSpeed(angle, speed);
   }

   // Helpers methods
   /**
    * Sets the ball's velocity based on a given angle and speed.
    *
    * @param angle the angle in degrees
    * @param speed the speed of the ball
    */
   public void fromAngleAndSpeed(double angle, double speed) {
      this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
   }

   /**
    * Removes the ball from the game.
    * Sets the ball's location to an off-screen point and stops its movement.
    *
    * @param g the game from which to remove the ball
    */
   public void removeFromGame(Game g) {
      g.removeSpite(this);
      this.setPoint(new Point(-100, -100));
      this.setVelocity(0, 0);
   }

   /**
    * Returns a string representation of the object.
    *
    * @return a string representation in the format: "(x,y) r:radius"
    */
   @Override
   public String toString() {
      return "(" + this.getX() + "," + this.getY() + ") r:" + this.r;
   }
}