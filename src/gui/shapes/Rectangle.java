package gui.shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import biuoop.DrawSurface;

import gui.Point;
import gui.Line;

/**
 * A class representing a rectangle in a 2D space.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 2.171
 * @since 2024-01-21
 */
public class Rectangle {

   private Point upperLeft;
   private Point upperRight;
   private Point bottomRight;
   private Point bottomLeft;
   private double width;
   private double height;
   private Color color;

   private static final Color LINE_COLOR = new Color(33, 33, 33);

   /**
    * Constructs a rectangle with two diagonal points and a specified color.
    *
    * @param upperLeft   the starting point of the diagonal
    * @param bottomRight the ending point of the diagonal
    * @param color       the color of the rectangle
    */
   public Rectangle(Point upperLeft, Point bottomRight, Color color) {
      set4Points(upperLeft, bottomRight);

      this.width = Math.abs(upperLeft.getX() - bottomRight.getX());
      this.height = Math.abs(upperLeft.getY() - bottomRight.getY());

      this.color = new Color(color.getRGB());
   }

   /**
    * Constructs a rectangle with a starting point, width, height, and a specified
    * color.
    *
    * @param upperLeft the starting point of the rectangle
    * @param width     the width of the rectangle
    * @param height    the height of the rectangle
    * @param color     the color of the rectangle
    */
   public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
      this(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY() + height), color);
   }

   private void set4Points(Point upperLeft, Point bottomRight) {
      this.upperLeft = new Point(upperLeft);
      this.bottomRight = new Point(bottomRight);
      this.upperRight = new Point(bottomRight.getX(), upperLeft.getY());
      this.bottomLeft = new Point(upperLeft.getX(), bottomRight.getY());
   }

   /**
    * Gets the width of the rectangle.
    *
    * @return the width of the rectangle
    */
   public double getWidth() {
      return this.width;
   }

   /**
    * Gets the height of the rectangle.
    *
    * @return the height of the rectangle
    */
   public double getHeight() {
      return this.height;
   }

   /**
    * Gets the upper-left corner point of the rectangle.
    *
    * @return the upper-left corner point
    */
   public Point getUpperLeft() {
      return new Point(this.upperLeft);
   }

   /**
    * Gets the upper-right corner point of the rectangle.
    *
    * @return the upper-right corner point
    */
   public Point getUpperRight() {
      return new Point(this.upperRight);
   }

   /**
    * Gets the bottom-left corner point of the rectangle.
    *
    * @return the bottom-left corner point
    */
   public Point getBottomLeft() {
      return new Point(this.bottomLeft);
   }

   /**
    * Gets the bottom-right corner point of the rectangle.
    *
    * @return the bottom-right corner point
    */
   public Point getBottomRight() {
      return new Point(this.bottomRight);
   }

   /**
    * Changes the position of the rectangle by setting its upper-left and
    * bottom-right points.
    *
    * @param p the new upper-left point of the rectangle
    */
   public void changePosition(Point p) {
      this.set4Points(p, new Point(p.getX() + this.width, p.getY() + this.height));
   }

   /**
    * Gets a list of the four corner points of the rectangle.
    *
    * @return a List of the four corner points
    */
   public java.util.List<Point> getPoints() {
      List<Point> points = new ArrayList<Point>();

      points.add(this.getUpperLeft());
      points.add(this.getUpperRight());
      points.add(this.getBottomRight());
      points.add(this.getBottomLeft());

      return points;
   }

   /**
    * Gets a list of the four lines composing the rectangle.
    *
    * @return a List of the four lines
    */
   public java.util.List<Line> getLines() {
      List<Line> lines = new ArrayList<Line>();
      lines.add(this.getUpperLine());
      lines.add(this.getRightLine());
      lines.add(this.getBottomLine());
      lines.add(this.getLeftLine());

      return lines;
   }

   /**
    * Gets the upper line of the rectangle.
    *
    * @return the upper line
    */
   public Line getUpperLine() {
      return new Line(this.getUpperLeft(), this.getUpperRight());
   }

   /**
    * Gets the bottom line of the rectangle.
    *
    * @return the bottom line
    */
   public Line getBottomLine() {
      return new Line(this.getBottomLeft(), this.getBottomRight());
   }

   /**
    * Gets the left line of the rectangle.
    *
    * @return the left line
    */
   public Line getLeftLine() {
      return new Line(this.getBottomLeft(), this.getUpperLeft());
   }

   /**
    * Gets the right line of the rectangle.
    *
    * @return the right line
    */
   public Line getRightLine() {
      return new Line(this.getBottomRight(), this.getUpperRight());
   }

   /**
    * Gets the total size (area) of the rectangle.
    *
    * @return the size of the rectangle
    */
   public double getSize() {
      return this.width * this.height;
   }

   /**
    * Gets the starting x-coordinate of the rectangle.
    *
    * @return the starting x-coordinate
    */
   public double getStartX() {
      return this.upperLeft.getX() < this.bottomRight.getX() ? this.upperLeft.getX() : this.bottomRight.getX();
   }

   /**
    * Gets the starting y-coordinate of the rectangle.
    *
    * @return the starting y-coordinate
    */
   public double getStartY() {
      return this.upperLeft.getY() < this.bottomRight.getY() ? this.upperLeft.getY() : this.bottomRight.getY();
   }

   /**
    * Gets the ending x-coordinate of the rectangle.
    *
    * @return the ending x-coordinate
    */
   public double getEndX() {
      return this.upperLeft.getX() > this.bottomRight.getX() ? this.upperLeft.getX() : this.bottomRight.getX();
   }

   /**
    * Gets the ending y-coordinate of the rectangle.
    *
    * @return the ending y-coordinate
    */
   public double getEndY() {
      return this.upperLeft.getY() > this.bottomRight.getY() ? this.upperLeft.getY() : this.bottomRight.getY();
   }

   /**
    * Gets the color of the rectangle.
    *
    * @return the color of the rectangle
    */
   public java.awt.Color getColor() {
      return this.color;
   }

   /**
    * Gets the starting point of the rectangle based on the minimum x and y
    * coordinates.
    *
    * @return the starting point of the rectangle
    */
   public Point getStarPoint() {
      Point start;
      if (this.upperLeft.getX() < this.upperRight.getX()) {
         start = this.upperLeft.getY() < this.bottomLeft.getY() ? this.upperLeft : this.bottomLeft;
      } else {
         start = this.upperRight.getY() < this.bottomRight.getY() ? this.upperRight : this.bottomRight;
      }
      return start;
   }

   /**
    * Draws the rectangle on a given DrawSurface.
    *
    * @param surface the DrawSurface to draw the rectangle on
    */
   public void drawOn(DrawSurface surface) {
      Point start = this.getStarPoint();
      surface.setColor(this.getColor());
      surface.fillRectangle((int) start.getX(), (int) start.getY(), (int) width, (int) height);
      surface.setColor(LINE_COLOR);
      surface.drawRectangle((int) start.getX(), (int) start.getY(), (int) width, (int) height);
   }

   /**
    * Checks if a given point is inside the rectangle in the x-axis with a
    * specified margin.
    *
    * @param point  the point to check
    * @param margin the margin allowed in the x-axis
    * @return true if the point is inside, false otherwise
    */
   public boolean insideX(Point point, double margin) {
      return Math.min(upperLeft.getX(), upperRight.getX()) + margin < point.getX()
            && point.getX() < Math.max(upperLeft.getX(), upperRight.getX()) - margin;
   }

   /**
    * Checks if a given point is inside the rectangle in the x-axis with no margin.
    *
    * @param point the point to check
    * @return true if the point is inside, false otherwise
    */
   public boolean insideX(Point point) {
      return insideX(point, 0);
   }

   /**
    * Checks if a given point is inside the rectangle in the y-axis with a
    * specified margin.
    *
    * @param point  the point to check
    * @param margin the margin allowed in the y-axis
    * @return true if the point is inside, false otherwise
    */
   public boolean insideY(Point point, double margin) {
      return Math.min(upperRight.getY(), bottomRight.getY()) + margin < point.getY()
            && point.getY() < Math.max(upperRight.getY(), bottomRight.getY()) - margin;
   }

   /**
    * Checks if a given point is inside the rectangle in the y-axis with no margin.
    *
    * @param point the point to check
    * @return true if the point is inside, false otherwise
    */
   public boolean insideY(Point point) {
      return insideY(point, 0);
   }

   /**
    * Checks if a given point is inside the rectangle with a specified margin.
    *
    * @param point  the point to check
    * @param margin the margin allowed in both x and y axes
    * @return true if the point is inside, false otherwise
    */
   public boolean inside(Point point, double margin) {
      return insideX(point, margin) && insideY(point, margin);
   }

   /**
    * Checks if a given point is inside the rectangle with no margin.
    *
    * @param point the point to check
    * @return true if the point is inside, false otherwise
    */
   public boolean inside(Point point) {
      return insideX(point, 0) && insideY(point, 0);
   }

   /**
    * Generates a random point within the bounds of the rectangle.
    *
    * @param margin the margin to prevent points from being too close to the edges
    * @return a random point within the rectangle
    */
   public Point getRandomPoint(double margin) {
      Random rand = new Random();

      double x = rand.nextDouble() * (this.getEndX() - this.getStartX()) + this.getStartX() - margin;
      double y = rand.nextDouble() * (this.getEndY() - this.getStartY()) + this.getStartY() - margin;

      return new Point(x, y);
   }

   /**
    * Generates a random point within the bounds of the rectangle with no margin.
    *
    * @return a random point within the rectangle
    */
   public Point getRandomPoint() {
      return getRandomPoint(0);
   }

   /**
    * Finds the intersection points between the rectangle and a given line.
    *
    * @param line the line to find intersection points with
    * @return a List of intersection points
    */
   public java.util.List<Point> intersectionPoints(Line line) {
      return intersectionPoints(line, true);
   }

   /**
    * Finds the intersection points between the rectangle and a given line,
    * optionally
    * including points on the rectangle edges.
    *
    * @param line   the line to find intersection points with
    * @param inside flag to include points on the rectangle edges
    * @return a List of intersection points
    */
   public java.util.List<Point> intersectionPoints(Line line, boolean inside) {
      List<Point> points = new ArrayList<Point>();
      List<Line> lines = getLines();

      for (int i = 0; i < lines.size(); i++) {
         Point point = lines.get(i).intersectionWith(line, inside);
         if (point != null) {
            points.add(point);
         }
      }

      return points;
   }

   /**
    * Provides a string representation of the rectangle.
    *
    * @return a string with information about the rectangle
    */
   @Override
   public String toString() {
      return "p1- " + this.upperLeft + " width- " + this.width + " height- " + this.height;
   }

}
