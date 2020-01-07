package it.unisa.theneverendingrun.services.spawn.observer;


/**
 *
 * A common interface for all the subscribers interested in observing the {@link SpawnProbabilityDifficultyListener}
 * spawnProbability variable
 */
public interface SpawnProbabilityListener {

    /**
     *
     * The {@link SpawnProbabilityListener} listener reaction when the observed variable {@code spawnProbability} changes
     *
     * @param eventType the updated topic related to {@link SpawnProbabilityDifficultyListener}
     * @param spawnProbability the new value for the observed variable
     */
    void update(SpawnProbabilityEventType eventType, int spawnProbability);
}
