package it.unisa.theneverendingrun.services.difficulty;

import it.unisa.theneverendingrun.services.meters.MeterEditor;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;

/**
 * The class that is delegated to properly updating the difficulty level. Moreover is also a {@link MetersListener} that computes
 * the difficulty level depending on the {@link MeterEditor} meters variable value.
 * At the end this class is a subscriber to the meters and a publisher about the difficulty level
 */

public class DifficultyMetersListener implements MetersListener {

    /**
     *
     * The handler for all the {@link DifficultyEventType} topics related to this class
     */
    private DifficultyEventManager eventManager;

    /**
     *
     * Each {@link DifficultyMetersListener#METERS_DELTA} meters the {@link DifficultyMetersListener#difficultyLevel}
     * increases by DIFFICULTY_FACTOR
     */
    public final static int DIFFICULTY_FACTOR = 1;

    /**
     *
     * The initial difficulty of the game
     */
    public final static int INITIAL_DIFFICULTY = 1;

    /**
     *
     * The number of the meter that the level difficulty will change
     */
    public final static float METERS_DELTA = 200.0f;

    /**
     *
     * the current level of difficulty
     */
    private int difficultyLevel;

    /**
     *
     * @see DifficultyMetersListener#setDifficultyLevel(int)
     *
     * Initializes the {@link DifficultyMetersListener#difficultyLevel} field to
     * {@link DifficultyMetersListener#INITIAL_DIFFICULTY}
     */
    public DifficultyMetersListener() {
        setDifficultyLevel(INITIAL_DIFFICULTY);

        eventManager = new DifficultyEventManager(DifficultyEventType.values());
    }

    /**
     * @see DifficultyMetersListener#difficultyLevel
     *
     * @return the current difficulty level
     */
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     *
     * @see DifficultyMetersListener#eventManager
     *
     * @return the handler for all the {@link DifficultyEventType} topics related to this class
     */
    public DifficultyEventManager getEventManager() {
        return eventManager;
    }

    /**
     *
     * @see DifficultyMetersListener#difficultyLevel
     *
     * @param difficulty the new value for the difficulty variable
     */
    private void setDifficultyLevel(int difficulty) {
        difficultyLevel = difficulty;
        getEventManager().notify(DifficultyEventType.LEVEL_CHANGED, getDifficultyLevel());
    }

    /**
     *
     * The {@link DifficultyMetersListener} listener reaction when the observed variable {@code meters} changes.
     * It increases the score as a step function of the travelled meters
     *
     * @param eventType the updated topic related to {@link MeterEditor}
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED)
            setDifficultyLevel((int) (DIFFICULTY_FACTOR * meters / METERS_DELTA) + INITIAL_DIFFICULTY);
    }
}
