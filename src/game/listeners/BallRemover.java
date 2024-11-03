package game.listeners;

import game.Game;
import game.informative.Counter;
import game.interfaces.HitListener;
import game.objects.Ball;
import game.objects.Block;

/**
 * The BallRemover class is responsible for removing balls from the game and updating
 * the count of remaining balls. It implements the HitListener interface.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.1
 * @since 2024-23-02
 */
public class BallRemover implements HitListener {
   private Game game;
   private Counter remainingBalls;

   /**
    * Constructs a BallRemover with a given Game and Counter for remaining balls.
    *
    * @param game the game instance
    * @param remainingBalls the Counter for remaining balls
    */
   public BallRemover(Game game, Counter remainingBalls) {
      this.game = game;
      this.remainingBalls = remainingBalls;
   }

   /**
    * Handles the event of a ball hitting a block. Removes the ball from the game and
    * decreases the remaining balls count.
    *
    * @param beingHit the block being hit (not used in this context)
    * @param hitter   the ball that hits the block
    */
   public void hitEvent(Block beingHit, Ball hitter) {
      hitter.removeFromGame(game);
      remainingBalls.decrease(1);
   }
}
