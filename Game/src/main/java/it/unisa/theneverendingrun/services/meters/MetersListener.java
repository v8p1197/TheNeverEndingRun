package it.unisa.theneverendingrun.services.meters;

/**
 *
 * A common interface for all the subscribers interested in observing the {@link MeterEditor} meters variable
 */
public interface MetersListener {

    /**
     *
     * The {@link MetersListener} listener reaction when the observed variable {@code meters} changes
     *
     * @param eventType the updated topic related to {@link MeterEditor}
     * @param meters the new value for the observed variable
     */
    void update(MetersEventType eventType, int meters);
}
