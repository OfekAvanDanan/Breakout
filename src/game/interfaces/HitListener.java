package game.interfaces;
import game.objects.Block;
import game.objects.Ball;

/**
 * The HitListener interface represents an object that can handle hit events.
 * Implementing classes should define the behavior when a specific object (beingHit) is hit by a Ball (hitter).
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.0
 * @since 2024-23-02
 */
public interface HitListener {
   /**
    * This method is called whenever the beingHit object is hit.
    *
    * @param beingHit the Block that is being hit
    * @param hitter   the Ball that is doing the hitting
    */
   void hitEvent(Block beingHit, Ball hitter);
}

