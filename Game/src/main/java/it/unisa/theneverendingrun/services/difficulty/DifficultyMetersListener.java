package it.unisa.theneverendingrun.services.difficulty;

import it.unisa.theneverendingrun.services.meters.MeterEditor;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;

/**
 *
 * A concrete {@link MetersListener} that computes the difficulty level depending on the
 * {@link MeterEditor} meters variable value
 */
public class DifficultyMetersListener implements MetersListener {

    /**
     *
     * {@link DifficultyMetersListener#difficultyLevel} field increases by DIFFICULTY_FACTOR each
     * {@link DifficultyMetersListener#METERS_DELTA} meters
     */
    public final static int DIFFICULTY_FACTOR = 1;

    /**
     *
     * How many meters the {@link DifficultyMetersListener#difficultyLevel} variable changes
     */
    public final static float METERS_DELTA = 200.0f;

    /**
     *
     * The difficulty level when the game begins, i.e. when {@link MeterEditor#INITIAL_METERS} meters have been travelled
     */
    public final static int INITIAL_DIFFICULTY = 1;

    /**
     *
     * The handler for all the {@link DifficultyEventType} topics related to this class
     */
    private DifficultyEventManager eventManager;

    /**
     *
     * The observed variable that stores the current difficulty level
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
     * {@link DifficultyMetersListener#difficultyLevel} setter: updates the {@code difficulty} variable and notifies
     * all the {@link DifficultyListener} observers
     *
     * @param difficulty the new difficulty value
     */
    private void setDifficultyLevel(int difficulty) {
        difficultyLevel = difficulty;
        getEventManager().notify(DifficultyEventType.LEVEL_CHANGED, getDifficultyLevel());
    }

    /**
     *
     * The {@link DifficultyMetersListener} listener reaction when the observed variable {@code meters} changes.
     * It increases the difficulty level as a step function of the travelled meters
     *
     * @param eventType the updated topic related to {@link MeterEditor}
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED)
            setDifficultyLevel(DIFFICULTY_FACTOR * (int)(meters / METERS_DELTA) + INITIAL_DIFFICULTY);
    }
}
