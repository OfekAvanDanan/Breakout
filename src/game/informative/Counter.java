package game.informative;

/**
 * The Counter class represents a simple counter that can be incremented,
 * decremented, and queried for its current value.
 *
 * @author Ofek Avan Danan | ofek.avandanan@live.biu.ac.il | 211824727
 * @version 1.0
 * @since 2024-23-02
 */
public class Counter {
    private int count;

    /**
     * Increases the counter by a specified number.
     *
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the counter by a specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return this.count;
    }
}
