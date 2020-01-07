package it.unisa.theneverendingrun.services.speed;


/**
 *
 * A common interface for all the subscribers interested in observing the {@link SpeedDifficultyListener} speed variable
 */
public interface SpeedListener {

    /**
     *
     * The {@link SpeedListener} listener reaction when the observed variable {@code speed} changes
     *
     * @param eventType the updated topic related to {@link SpeedDifficultyListener}
     * @param speed the new value for the observed variable
     */
    void update(SpeedEventType eventType, float speed);
}
