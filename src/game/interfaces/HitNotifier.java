package game.interfaces;

/**
 * The HitNotifier interface represents an object that can notify HitListener objects
 * about hit events.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.0
 * @since 2024-23-02
 */
public interface HitNotifier {
   /**
    * Adds a HitListener to the list of listeners to be notified about hit events.
    *
    * @param hl the HitListener to be added
    */
   void addHitListener(HitListener hl);

   /**
    * Removes a HitListener from the list of listeners, preventing it from being notified
    * about hit events.
    *
    * @param hl the HitListener to be removed
    */
   void removeHitListener(HitListener hl);
}
