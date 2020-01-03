package it.unisa.theneverendingrun.metersManager;

/**
 * A {@link DifficultyListener} that computes the store depending on the {@link DifficultyMeterListener} difficulty variable value
 */
class ObstacleSpawnProbabilityListener implements DifficultyListener {

    /**
     * Initial probability of spawn
     */
    private static final int INITIAL_SPAWN_PROBABILITY = 90;

    /**
     * Spawn factor's probability used to compute the new spawn probability
     */
    private static final int SPAWN_FACTOR_PROBABILITY = 10;

    /**
     * The actual spawn probability
     */
    private int spawnProbability;

    /**
     * Constructor of the class. It initialize the difficultyFlag to 1 and the actual spawn probability to the initial spawn probability
     */
    ObstacleSpawnProbabilityListener() {
        spawnProbability = INITIAL_SPAWN_PROBABILITY;
    }

    /**
     * Getter of the initial spawn probability
     *
     * @return the initial spawn probability
     */
    int getInitialSpawnProbability() {
        return INITIAL_SPAWN_PROBABILITY;
    }

    /**
     * Getter of the spawn factor
     *
     * @return the spawn factor
     */
    int getSpawnFactorProbability() {
        return SPAWN_FACTOR_PROBABILITY;
    }

    /**
     * Getter of the actual spawn probability
     *
     * @return the actual spawn probability
     */
    int getSpawnProbability() {
        return spawnProbability;
    }

    /**
     * Setter of the actual spawn probability
     *
     * @param probabilityFactor the factor to subtract to the spawn probability
     */
    private void setSpawnProbability(int probabilityFactor) {
        spawnProbability = probabilityFactor;
    }

    /**
     * The {@link ObstacleSpawnProbabilityListener} listener reaction when the observed variable {@code difficulty} changes.
     * It decreases the spawn probability when the level will change.
     *
     * @param difficulty the new value for the observed variable
     */
    @Override
    public void update(int difficulty) {
        if (difficulty < Level.LEVEL_MAX.getValue())
            setSpawnProbability(INITIAL_SPAWN_PROBABILITY - SPAWN_FACTOR_PROBABILITY * difficulty);
    }
}
