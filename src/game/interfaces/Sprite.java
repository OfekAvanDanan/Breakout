package game.interfaces;
import biuoop.DrawSurface;

/**
 * The Sprite interface represents objects that can be drawn on a DrawSurface
 * and need to be notified when time has passed.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.0
 * @since 2024-01-21
 */
public interface Sprite {
    /**
     * Draws the sprite on a DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}
