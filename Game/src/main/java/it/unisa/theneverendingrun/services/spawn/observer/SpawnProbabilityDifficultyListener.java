package it.unisa.theneverendingrun.services.spawn.observer;

import it.unisa.theneverendingrun.services.difficulty.DifficultyEventType;
import it.unisa.theneverendingrun.services.difficulty.DifficultyListener;
import it.unisa.theneverendingrun.services.difficulty.DifficultyMetersListener;

/**
 *
 * A concrete {@link DifficultyListener} that computes the spawn probability depending on the
 * {@link DifficultyMetersListener} difficultyLevel variable value
 */
public class SpawnProbabilityDifficultyListener implements DifficultyListener {

    /**
     *
     * {@link SpawnProbabilityDifficultyListener#spawnProbability} field increases by SPAWN_PROBABILITY_FACTOR each
     * {@link SpawnProbabilityDifficultyListener#DIFFICULTY_DELTA} difficulty levels
     */
    public static final int SPAWN_PROBABILITY_FACTOR = -10;

    /**
     *
     * How many difficulty levels the {@link SpawnProbabilityDifficultyListener#spawnProbability} variable changes
     */
    public static final float DIFFICULTY_DELTA = 1;

    /**
     * The spawn probability when the game begins, i.e. with a difficulty level of
     * {@link DifficultyMetersListener#INITIAL_DIFFICULTY}
     */
    public static final int INITIAL_SPAWN_PROBABILITY = 90;

    /**
     *
     * The handler for all the {@link SpawnProbabilityEventType} topics related to this class
     */
    private SpawnProbabilityEventManager eventManager;

    /**
     *
     * The observed variable that stores the current spawn probability
     */
    private int spawnProbability;

    /**
     *
     * @see SpawnProbabilityDifficultyListener#setSpawnProbability(int)
     *
     * Initializes the {@link SpawnProbabilityDifficultyListener#spawnProbability} field to
     * {@link SpawnProbabilityDifficultyListener#INITIAL_SPAWN_PROBABILITY}
     */
    public SpawnProbabilityDifficultyListener() {
        eventManager = new SpawnProbabilityEventManager(SpawnProbabilityEventType.values());

        setSpawnProbability(INITIAL_SPAWN_PROBABILITY);
    }

    /**
     * @see SpawnProbabilityDifficultyListener#spawnProbability
     *
     * @return the current spawn probability
     */
    int getSpawnProbability() {
        return spawnProbability;
    }

    /**
     *
     * @see SpawnProbabilityDifficultyListener#eventManager
     *
     * @return the handler for all the {@link SpawnProbabilityEventType} topics related to this class
     */
    public SpawnProbabilityEventManager getEventManager() {
        return eventManager;
    }

    /**
     *
     * {@link SpawnProbabilityDifficultyListener#spawnProbability} setter: updates the
     * {@link SpawnProbabilityDifficultyListener#spawnProbability} variable and notifies
     * all the {@link SpawnProbabilityListener} observers
     *
     * @param probability the new speed value
     */
    private void setSpawnProbability(int probability) {
        spawnProbability = probability;

        getEventManager().notify(SpawnProbabilityEventType.SPAWN_PROBABILITY_CHANGED, getSpawnProbability());
    }

    /**
     *
     * The {@link SpawnProbabilityDifficultyListener} listener reaction when the observed variable
     * {@link SpawnProbabilityDifficultyListener#spawnProbability} changes.
     * It increases the spawn probability as a step function of the difficulty
     *
     * @param eventType the updated topic related to {@link DifficultyMetersListener}
     * @param difficulty the new value for the observed variable
     */
    @Override
    public void update(DifficultyEventType eventType, int difficulty) {
        if (eventType == DifficultyEventType.LEVEL_CHANGED) {
            setSpawnProbability(
                    SPAWN_PROBABILITY_FACTOR *
                            (int) ((difficulty - DifficultyMetersListener.INITIAL_DIFFICULTY) / DIFFICULTY_DELTA) +
                            INITIAL_SPAWN_PROBABILITY);
        }
    }
}
