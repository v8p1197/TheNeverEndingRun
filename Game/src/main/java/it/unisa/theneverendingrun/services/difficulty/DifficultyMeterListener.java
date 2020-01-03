package it.unisa.theneverendingrun.services.difficulty;

import it.unisa.theneverendingrun.services.difficulty.DifficultyEventManager;
import it.unisa.theneverendingrun.services.difficulty.DifficultyEventType;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;
import it.unisa.theneverendingrun.services.meters.MeterEditor;

/**
 * The class that is delegated to properly updating the difficulty level. Moreover is also a {@link MetersListener} that computes
 * the difficulty level depending on the {@link MeterEditor} meters variable value.
 * At the end this class is a subscriber to the meters and a publisher about the difficulty level
 */

public class DifficultyMeterListener implements MetersListener {

    /**
     *
     * The handler for all the {@link DifficultyEventType} topics related to this class
     */
    private DifficultyEventManager events;

    /**
     *
     * The difficult each Meters_Delta meter increases of a Difficulty factor
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
     * the actual level difficulty
     */
    private int difficultyLevel;


    public DifficultyMeterListener() {
        setDifficultyLevel(INITIAL_DIFFICULTY);
    }

    /**
     * Getter of the actual difficulty level
     *
     * @return the actual difficulty level
     */
    public int getDifficultyLevel() {
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
     * @param eventType
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED)
            setDifficultyLevel((int) (DIFFICULTY_FACTOR * meters / METERS_DELTA) + INITIAL_DIFFICULTY);
    }
}
