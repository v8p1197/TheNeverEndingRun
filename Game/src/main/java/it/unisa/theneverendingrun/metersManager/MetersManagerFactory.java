package it.unisa.theneverendingrun.metersManager;

/**
 * A class that is delegated to initialise the {@link MeterEditor} and all its {@link MetersListener}
 * observers subscriptions
 */
public class MetersManagerFactory {

    /**
     * The {@link ScoreMetersListener} observer for the {@link MeterEditor}
     */
    private ScoreMetersListener scoreMetersListener;

    private DifficultyMeterListener difficultyMeterListener;

    private SpeedDifficultyListener speedDifficultyListener;
    private ObstacleSpawnProbabilityListener spawnProbabilityListener;

    /**
     * Initialises the {@link MeterEditor} and all its {@link MetersListener} observers subscriptions
     */
    public MetersManagerFactory() {
        createSubscriptions();
        MeterEditor.initialise();
    }

    /**
     * Creates all the {@link MetersListener} observers subscriptions for the {@link MeterEditor}
     */
    private void createSubscriptions() {
        MeterEditor.events = new MetersEventManager(MetersEventType.METERS_CHANGED);

        scoreMetersListener = new ScoreMetersListener();
        MeterEditor.events.subscribe(MetersEventType.METERS_CHANGED, scoreMetersListener);

        difficultyMeterListener = new DifficultyMeterListener();
        MeterEditor.events.subscribe(MetersEventType.METERS_CHANGED, difficultyMeterListener);

        difficultyMeterListener.events = new DifficultyEventManager(DifficultyEventType.LEVEL_CHANGED);

        spawnProbabilityListener = new ObstacleSpawnProbabilityListener();
        speedDifficultyListener = new SpeedDifficultyListener();
        difficultyMeterListener.events.subscribe(DifficultyEventType.LEVEL_CHANGED, spawnProbabilityListener);
        difficultyMeterListener.events.subscribe(DifficultyEventType.LEVEL_CHANGED, speedDifficultyListener);
    }

    /**
     * Updates the meter counter
     */
    public void computeMeters() {
        MeterEditor.compute();
    }

    /**
     * @return the meter counter
     */
    public int getMeters() {
        return MeterEditor.getMeters();
    }

    /**
     * @return the score
     */
    public int getScore() {
        return scoreMetersListener.getScore();
    }

    public int getDifficulty() {
        return difficultyMeterListener.getDifficultyLevel();
    }

    public int getSpawnProbability() {
        return spawnProbabilityListener.getSpawnProbability();
    }

    public float getSpeed() {
        return speedDifficultyListener.getSpeed();
    }

    public float getMetersDelta() {
        return difficultyMeterListener.getMetersDelta();
    }

    public int getDifficultyFactor() {
        return difficultyMeterListener.getDifficultyFactor();
    }

    public float getInitialSpeed() {
        return speedDifficultyListener.getInitialSpeed();
    }

    public float getSpeedFactor() {
        return speedDifficultyListener.getSpeedFactor();
    }

    public int getInitialDifficulty() {
        return difficultyMeterListener.getInitialDifficulty();
    }

    public int getInitialSpawnProbability() {
        return spawnProbabilityListener.getInitialSpawnProbability();
    }

    public int getSpawnFactorProbability() {
        return spawnProbabilityListener.getSpawnFactorProbability();
    }

}
