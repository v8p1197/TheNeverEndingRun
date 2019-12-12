package it.unisa.theneverendingrun.metersManager;

public class MetersManagerFactory {

    private ScoreMetersListener scoreMetersListener;

    public MetersManagerFactory() {
        createSubscriptions();
        MeterEditor.initialise();
    }

    private void createSubscriptions() {
        MeterEditor.events = new MetersEventManager(MetersEventType.METERS_CHANGED);

        scoreMetersListener = new ScoreMetersListener();
        MeterEditor.events.subscribe(MetersEventType.METERS_CHANGED, scoreMetersListener);
    }

    public void updateMeters() {
        MeterEditor.update();
    }

    public int getMeters() {
        return MeterEditor.getMeters();
    }

    public int getScore() {
        return scoreMetersListener.getScore();
    }
}
