package it.unisa.theneverendingrun.metersManager;

/**
 * The class that is delegated to properly updating the difficulty level. Moreover is also a {@link MetersListener} that computes
 * the difficulty level depending on the {@link MeterEditor} meters variable value.
 * At the end this class is a subscriber to the meters and a publisher about the difficulty level
 */

class DifficultyMeterListener implements MetersListener {

    /**
     * The handler for all the {@link DifficultyEventType} topics related to this class
     */
    DifficultyEventManager events;

    /**
     * the difficult each Meters_Delta meter increases of a Difficulty factor
     */
    private final static int DIFFICULTY_FACTOR = 1;

    /**
     * the initial difficulty of the game
     */
    private final static int INITIAL_DIFFICULTY = 1;

    /**
     * the number of the meter that the level difficulty will change
     */
    private final static float METERS_DELTA = 200.0f;

    /**
     * the actual level difficulty
     */
    private int difficultyLevel;

    DifficultyMeterListener() {
        this.difficultyLevel = INITIAL_DIFFICULTY;
    }

    /**
     * Getter of the difficulty factor
     *
     * @return the difficulty factor
     */
    int getDifficultyFactor() {
        return DIFFICULTY_FACTOR;
    }

    /**
     * Getter of the initial difficulty
     *
     * @return initial difficulty
     */
    int getInitialDifficulty() {
        return INITIAL_DIFFICULTY;
    }

    /**
     * Getter the number of meters that the level difficulty will change
     *
     * @return meters delta
     */
    float getMetersDelta() {
        return METERS_DELTA;
    }

    /**
     * Getter of the actual difficulty level
     *
     * @return the actual difficulty level
     */
    int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Set the difficulty level and notify to all the subscriber that the difficulty has changed
     *
     * @param difficulty the new value for the difficulty
     */
    private void setDifficultyLevel(int difficulty) {
        difficultyLevel = difficulty;
        events.notify(DifficultyEventType.LEVEL_CHANGED, getDifficultyLevel());
    }

    /**
     * Update the difficulty level as a step function of the meters
     *
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(int meters) {
        setDifficultyLevel((int) (DIFFICULTY_FACTOR * meters / METERS_DELTA) + INITIAL_DIFFICULTY);
    }
}
