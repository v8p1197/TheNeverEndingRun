package it.unisa.theneverendingrun.gameStates;

/**
 * This Strategy defines how to visit a collection of items when the DOWN (or S) key is pressed
 */
public class NextKeyButtonsStrategy implements KeyButtonsStrategy {

    /**
     * Computes the first index to visit on a collection of dimension {@code} when the DOWN (or S) key is pressed
     *
     * @param size the dimension of the collection to visit
     * @return the first index in the collection
     */
    @Override
    public int firstIndex(int size) {
        return 0;
    }

    /**
     * Computes the next index to visit on a collection of dimension {@code} when the DOWN (or S) key is pressed
     *
     * @param i    the last index visited
     * @param size the dimension of the collection to visit
     * @return the next index in the collection, or the first index if {@code i} is the last index in the collection
     */
    @Override
    public int nextIndex(int i, int size) {
        return (i + 1) % size;
    }
}