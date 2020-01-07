package it.unisa.theneverendingrun.services.speed;

import it.unisa.theneverendingrun.services.difficulty.DifficultyEventType;
import it.unisa.theneverendingrun.services.difficulty.DifficultyListener;
import it.unisa.theneverendingrun.services.difficulty.DifficultyMetersListener;

/**
 *
 * A concrete {@link DifficultyListener} that computes the game speed depending on the
 * {@link DifficultyMetersListener} difficultyLevel variable value
 */
public class SpeedDifficultyListener implements DifficultyListener {

    /**
     *
     * {@link SpeedDifficultyListener#speed} field increases by DIFFICULTY_FACTOR each
     * {@link SpeedDifficultyListener#DIFFICULTY_DELTA} difficulty levels
     */
    public static final float SPEED_FACTOR = 0.3f;

    /**
     *
     * How many difficulty levels the {@link SpeedDifficultyListener#speed} variable changes
     */
    public static final float DIFFICULTY_DELTA = 1;

    /**
     * The speed when the game begins, i.e. with a difficulty level of
     * {@link DifficultyMetersListener#INITIAL_DIFFICULTY}
     */
    public static final float INITIAL_SPEED = 1.2f;

    /**
     *
     * The handler for all the {@link SpeedEventType} topics related to this class
     */
    private SpeedEventManager eventManager;

    /**
     *
     * The observed variable that stores the current game speed
     */
    private float speed;

    /**
     *
     * @see SpeedDifficultyListener#setSpeed(float)
     *
     * Initializes the {@link SpeedDifficultyListener#speed} field to
     * {@link SpeedDifficultyListener#INITIAL_SPEED}
     */
    public SpeedDifficultyListener() {
        setSpeed(INITIAL_SPEED);

        eventManager = new SpeedEventManager(SpeedEventType.values());
    }

    /**
     * @see SpeedDifficultyListener#speed
     *
     * @return the current game speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     *
     * @see SpeedDifficultyListener#eventManager
     *
     * @return the handler for all the {@link SpeedEventType} topics related to this class
     */
    public SpeedEventManager getEventManager() {
        return eventManager;
    }

    /**
     *
     * {@link SpeedDifficultyListener#speed} setter: updates the {@link SpeedDifficultyListener#speed} variable and
     * notifies all the {@link SpeedListener} observers
     *
     * @param speed the new speed value
     */
    private void setSpeed(float speed) {
        this.speed = speed;
        this.speed = (float) (Math.round(this.speed * 100.0) / 100.0);

        getEventManager().notify(SpeedEventType.SPEED_CHANGED, getSpeed());
    }

    /**
     *
     * The {@link SpeedDifficultyListener} listener reaction when the observed variable {@code difficulty} changes.
     * It increases the speed as a step function of the difficulty level
     *
     * @param eventType the updated topic related to {@link DifficultyMetersListener}
     * @param difficulty the new value for the observed variable
     */
    @Override
    public void update(DifficultyEventType eventType, int difficulty) {
        if (eventType == DifficultyEventType.LEVEL_CHANGED) {
            if (difficulty < Level.LEVEL_MAX.getValue())
                setSpeed(SPEED_FACTOR * (int)(difficulty / DIFFICULTY_DELTA) + INITIAL_SPEED);
        }
    }

}
