package it.unisa.theneverendingrun.gameStates;

/**
 * This Strategy defines how to visit a collection of items when a key (UP or DOWN) is pressed
 */
public interface KeyButtonsStrategy {

    /**
     * Computes the first index to visit on a collection of dimension {@code}
     *
     * @param size the dimension of the collection to visit
     * @return the first index to visit
     */
    int firstIndex(int size);

    /**
     * Computes the next index to visit on a collection of dimension {@code}
     *
     * @param i    the last index visited
     * @param size the dimension of the collection to visit
     * @return the next index to visit
     */
    int nextIndex(int i, int size);
}
