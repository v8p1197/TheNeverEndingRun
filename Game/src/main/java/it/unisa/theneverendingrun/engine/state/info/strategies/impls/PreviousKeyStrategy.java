package it.unisa.theneverendingrun.engine.state.info.strategies.impls;

import it.unisa.theneverendingrun.engine.state.info.strategies.KeyStrategy;

/**
 * This Strategy defines how to visit a collection of items when the (ex. UP (or W)) is pressed
 */
public class PreviousKeyStrategy implements KeyStrategy {

    /**
     * Computes the first index to visit on a collection of dimension {@code size}
     * when the (ex. UP (or W)) is pressed
     *
     * @param size the dimension of the collection to visit
     * @return the previous index in the collection
     */
    @Override
    public int firstIndex(int size) {
        return size - 1;
    }

    /**
     * Computes the next index to visit on a collection of dimension {@code size}
     * when the (ex. UP (or W)) is pressed
     *
     * @param i    the last index visited
     * @param size the dimension of the collection to visit
     * @return the previous index in the collection, or the last index if {@code i} is the first index in the collection
     */
    @Override
    public int nextIndex(int i, int size) {
        var next = i - 1;
        if (next < 0)
            next = size - 1;
        return next;
    }
}
