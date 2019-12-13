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
    }

    /**
     * Updates the meter counter
     */
    public void updateMeters() {
        MeterEditor.update();
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
}
