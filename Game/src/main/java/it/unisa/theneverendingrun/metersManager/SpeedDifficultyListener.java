package it.unisa.theneverendingrun.metersManager;

/**
 * A {@link DifficultyListener} that computes the store depending on the {@link DifficultyMeterListener} difficulty variable value
 */
class SpeedDifficultyListener implements DifficultyListener {

    /**
     * Initial speed
     */
    private static final float INITIAL_SPEED = 1.2f;

    /**
     * Value of how the speed will increase
     */
    private static final float SPEED_FACTOR = 0.3f;

    /**
     * A support var to compute in a right way the spawn probability
     */
    private int difficultyFlag;

    /**
     * The actual value of the speed
     */
    private float speed;

    /**
     * Initialises the speed variable and the difficulty flag
     */
    SpeedDifficultyListener() {
        speed = INITIAL_SPEED;
        difficultyFlag = 1;
    }

    /**
     * {@code speed) setter
     *
     * @param scoreFactor the new value to increase the speed
     */
    private void setSpeed(float speedFactor) {
        speed += speedFactor;
        speed = (float) (Math.round(speed * 100.0) / 100.0);
    }

    /**
     * {@code speed} getter
     *
     * @return the initial speed
     */
    float getInitialSpeed() {
        return INITIAL_SPEED;
    }

    /**
     * {@code speed} getter
     *
     * @return the speed factor
     */
    float getSpeedFactor() {
        return SPEED_FACTOR;
    }

    /**
     * {@code speed} getter
     *
     * @return the actual speed
     */
    float getSpeed() {
        return speed;
    }

    /**
     * The {@link SpeedDifficultyListener} listener reaction when the observed variable {@code difficulty} changes.
     * It increases the speed when the level will change.
     *
     * @param difficulty the new value for the observed variable
     */
    @Override
    public void update(int difficulty) {
        if (difficulty == difficultyFlag + 1 && difficulty <= Level.LEVEL_MAX.getValue()) {
            difficultyFlag = difficulty;
            setSpeed(SPEED_FACTOR);
        } else if (difficulty == Level.LEVEL_PRO.getValue())
            setSpeed(SPEED_FACTOR);
    }

}
