package it.unisa.theneverendingrun.services.difficulty;

/**
 * A common interface for all the subscribers interested in observing the {@link DifficultyMeterListener} difficulty variable
 */
public interface DifficultyListener {

    /**
     * The {@link DifficultyListener} listener reaction when the observed variable {@code difficulty} changes
     *
     * @param eventType the updated topic related to {@link DifficultyMeterListener}
     * @param difficulty the new value for the observed variable
     */
    void update(DifficultyEventType eventType, int difficulty);
}
