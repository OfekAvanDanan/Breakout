package game.objects;

import java.awt.Color;
import biuoop.DrawSurface;
import game.informative.Counter;
import game.interfaces.Sprite;
import gui.Text;

/**
 * The ScoreIndicator class represents a sprite that displays the game's score on the screen.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.1
 * @since 2024-24-02
 */
public class ScoreIndicator implements Sprite {
    private int x;
    private int y;
    private Counter score;
    private int fontSize;
    private Color color;

    private final String title = "SCORE: ";

    /**
     * Constructs a ScoreIndicator with the specified position, score counter, font size, and color.
     *
     * @param x      the x-coordinate of the score indicator position
     * @param y      the y-coordinate of the score indicator position
     * @param score  the counter representing the game score
     * @param fontSize the font size of the text
     * @param color  the color of the text
     */
    public ScoreIndicator(int x, int y, Counter score, int fontSize, Color color) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.fontSize = fontSize;
        this.color = color;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the score indicator
     */
    @Override
    public void drawOn(DrawSurface d) {
        Text text = new Text(x, y, title + score.getValue(), fontSize, color);
        text.drawOn(d);
    }

    /**
     * Performs a time-based operation for the score indicator (no time-based operation for this class).
     */
    @Override
    public void timePassed() {
        // No time-based operation for the score indicator
    }
}
