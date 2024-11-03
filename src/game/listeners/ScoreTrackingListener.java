package game.listeners;

import game.objects.Block;
import game.objects.Ball;
import game.informative.Counter;
import game.interfaces.HitListener;

/**
 * The ScoreTrackingListener class is responsible for keeping track of the player's score
 * by updating a Counter every time a block is hit by a ball.
 * It implements the HitListener interface.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.1
 * @since 2024-24-02
 */
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;

   /**
    * Constructs a ScoreTrackingListener with a given Counter to track the score.
    *
    * @param scoreCounter the Counter to track the score
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }

   /**
    * Updates the score by increasing it when a block is hit by a ball.
    *
    * @param beingHit the block being hit
    * @param hitter   the ball that hits the block
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(1);
   }
}
