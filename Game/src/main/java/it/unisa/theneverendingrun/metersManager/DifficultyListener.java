package it.unisa.theneverendingrun.metersManager;

/**
 * A common interface for all the subscribers interested in observing the {@link DifficultyMeterListener} difficulty variable
 */
interface DifficultyListener {

    /**
     * The {@link DifficultyListener} listener reaction when the observed variable {@code difficulty} changes
     *
     * @param difficulty the new value for the observed variable
     */
    void update(int difficulty);
}
