package gui;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Text class represents a text element to be displayed on a DrawSurface.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.1
 * @since 2024-24-02
 */
public class Text {
    private int x;
    private int y;
    private int fontSize;
    private String text;
    private Color color;

    /**
     * Constructs a Text object with the specified position, text, font size, and color.
     *
     * @param x      the x-coordinate of the text position
     * @param y      the y-coordinate of the text position
     * @param text   the text content
     * @param fontSize the font size of the text
     * @param color  the color of the text
     */
    public Text(int x, int y, String text, int fontSize, Color color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.fontSize = fontSize;
        this.color = color;
    }

    /**
     * Sets the text content.
     *
     * @param text the new text content
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the current text content.
     *
     * @return the text content
     */
    public String getText() {
        return this.text;
    }

    /**
     * Draws the text on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the text
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText(this.x, this.y, this.text, this.fontSize);
    }
}
