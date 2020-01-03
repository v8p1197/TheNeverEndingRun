package it.unisa.theneverendingrun.services.strategies;

/**
 * This Strategy defines how to visit a collection of items when a key (ex. UP or DOWN) is pressed
 */
public interface KeyStrategy {

    /**
     * Computes the first index to visit on a collection of dimension {@code size}
     *
     * @param size the dimension of the collection to visit
     * @return the first index to visit
     */
    int firstIndex(int size);

    /**
     * Computes the next index to visit on a collection of dimension {@code size}
     *
     * @param i    the last index visited
     * @param size the dimension of the collection to visit
     * @return the next index to visit
     */
    int nextIndex(int i, int size);
}
