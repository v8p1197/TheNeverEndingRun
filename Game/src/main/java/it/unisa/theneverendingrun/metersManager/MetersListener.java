package it.unisa.theneverendingrun.metersManager;

/**
 * A common interface for all the subscribers interested in observing the {@link MeterEditor} meters variable
 */
interface MetersListener {

    /**
     * The {@link MetersListener} listener reaction when the observed variable {@code meters} changes
     *
     * @param meters the new value for the observed variable
     */
    void update(int meters);
}
