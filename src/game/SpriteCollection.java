package game;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

import game.interfaces.Sprite;


/**
 * The SpriteCollection class represents a collection of sprites in the game.
 * It provides methods to add sprites, notify all sprites that time has passed,
 * and draw all sprites on a DrawSurface.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.01
 * @since 2024-01-21
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<Sprite>();

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * removes a sprite to the collection.
     *
     * @param s the sprite to be removed
     */
    public void removeSpite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(spriteList);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on a DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }
}
