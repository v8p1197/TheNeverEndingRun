package it.unisa.theneverendingrun.services.speed;

import it.unisa.theneverendingrun.services.difficulty.DifficultyEventType;
import it.unisa.theneverendingrun.services.difficulty.DifficultyListener;
import it.unisa.theneverendingrun.services.difficulty.DifficultyMetersListener;

/**
 *
 * A {@link DifficultyListener} that computes the {@link SpeedDifficultyListener#speed} depending on the
 * {@link DifficultyMetersListener} difficulty variable value
 */
public class SpeedDifficultyListener implements DifficultyListener {

    /**
     *
     * The handler for all the {@link SpeedEventType} topics related to this class
     */
    private SpeedEventManager eventManager;

    /**
     * The initial speed of the game
     */
    public static final float INITIAL_SPEED = 1.2f;

    /**
     *
     * Each {@link SpeedDifficultyListener#DIFFICULTY_DELTA} levels of difficulty the {@link SpeedDifficultyListener#speed}
     * increases by SPEED_FACTOR
     */
    public static final float SPEED_FACTOR = 0.3f;

    /**
     *
     * How many difficulty levels the speed changes
     */
    public static final float DIFFICULTY_DELTA = 1;

    /**
     * The current value of the game speed
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
     * @return the current speed
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
     * {@link SpeedDifficultyListener#speed} setter: updates the {@code speed} variable and notifies
     * all the {@link SpeedListener} observers
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
     * It increases the speed as a step function of the travelled meters
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
