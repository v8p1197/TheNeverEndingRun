package it.unisa.theneverendingrun.services.speed;

import it.unisa.theneverendingrun.services.difficulty.DifficultyEventType;
import it.unisa.theneverendingrun.services.difficulty.DifficultyListener;
import it.unisa.theneverendingrun.services.difficulty.DifficultyMeterListener;

/**
 * A {@link DifficultyListener} that computes the store depending on the {@link DifficultyMeterListener} difficulty variable value
 */
public class SpeedDifficultyListener implements DifficultyListener {

    /**
     * Initial speed
     */
    public static final float INITIAL_SPEED = 1.2f;

    /**
     * Value of how the speed will increase
     */
    public static final float SPEED_FACTOR = 0.3f;

    /**
     * The actual value of the speed
     */
    private float speed;

    /**
     * Initialises the speed variable and the difficulty flag
     */
    public SpeedDifficultyListener() {
        setSpeed(INITIAL_SPEED);
    }

    /**
     * {@code speed) setter
     *
     * @param scoreFactor the new value to increase the speed
     */
    private void setSpeed(float speedFactor) {
        speed = speedFactor;
        speed = (float) (Math.round(speed * 100.0) / 100.0);
    }

    /**
     * {@code speed} getter
     *
     * @return the actual speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * The {@link SpeedDifficultyListener} listener reaction when the observed variable {@code difficulty} changes.
     * It increases the speed when the level will change.
     *
     * @param eventType
     * @param difficulty the new value for the observed variable
     */
    @Override
    public void update(DifficultyEventType eventType, int difficulty) {
        if (eventType == DifficultyEventType.LEVEL_CHANGED) {
            if (difficulty < Level.LEVEL_MAX.getValue())
                setSpeed(INITIAL_SPEED + SPEED_FACTOR * difficulty);
        }

    }

}
