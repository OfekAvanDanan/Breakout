package game.listeners;

import game.Game;
import game.informative.Counter;
import game.interfaces.HitListener;
import game.objects.Ball;
import game.objects.Block;


/**
 * The BlockRemover class is responsible for removing blocks from the game and keeping track
 * of the remaining number of blocks. It implements the HitListener interface.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.1
 * @since 2024-23-02
 */
public class BlockRemover implements HitListener {
   private Game game;
   private Counter remainingBlocks = new Counter();

   /**
    * Constructs a BlockRemover with a given Game and Counter for remaining blocks.
    *
    * @param game            the game instance
    * @param remainingBlocks the Counter for remaining blocks
    */
   public BlockRemover(Game game, Counter remainingBlocks) {
      this.game = game;
      this.remainingBlocks = remainingBlocks;
   }

   /**
    * Handles the event of a block being hit by a ball. Removes the block from the game and
    * decreases the remaining blocks count. Also, removes this listener from the block.
    *
    * @param beingHit the block being hit
    * @param hitter   the ball that hits the block
    */
   public void hitEvent(Block beingHit, Ball hitter) {
      beingHit.removeFromGame(game);
      beingHit.removeHitListener(this);
      this.remainingBlocks.decrease(1);
   }
}
