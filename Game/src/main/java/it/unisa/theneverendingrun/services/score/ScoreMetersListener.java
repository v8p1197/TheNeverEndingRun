package it.unisa.theneverendingrun.services.score;

import it.unisa.theneverendingrun.services.meters.MeterEditor;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;

/**
 *
 * A concrete {@link MetersListener} that computes the score depending on the {@link MeterEditor} meters variable value
 */
public class ScoreMetersListener implements MetersListener {

    /**
     *
     * The factor the meters are multiplied with in order to compute the score
     */
    public static final int SCORE_FACTOR = 10;

    public static final float METERS_DELTA = 1.0F;

    /**
     *
     * The score when the game begins, i.e. when 0 meters have been travelled
     */
    public static final int INITIAL_SCORE = 0;

    /**
     *
     * The game score
     */
    private int score;

    /**
     *
     * The {@link ScoreMetersListener#score} multiplier
     */
    private float multiplier;

    /**
     *
     * The handler for all the {@link ScoreEventType} topics related to this class
     */
    private ScoreEventManager eventManager;

    /**
     *
     * @see ScoreMetersListener#setScore(int)
     *
     * Initializes the {@link ScoreMetersListener#score} field to {@link ScoreMetersListener#INITIAL_SCORE}
     */
    public ScoreMetersListener() {
        setScore(INITIAL_SCORE);
        multiplier = 1.0F;

        eventManager = new ScoreEventManager(ScoreEventType.values());
    }

    /**
     *
     * @see ScoreMetersListener#score
     *
     * @return the game score value
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @see ScoreMetersListener#eventManager
     *
     * @return the handler for all the {@link ScoreEventType} topics related to this class
     */
    public ScoreEventManager getEventManager() {
        return eventManager;
    }

    /**
     *
     * @see ScoreMetersListener#score
     *
     * @param score the new value for the score variable
     */
    private void setScore(int score) {
        this.score = score;
        getEventManager().notify(ScoreEventType.SCORE_CHANGED, getScore());
    }

    /**
     *
     * The {@link ScoreMetersListener} listener reaction when the observed variable {@code meters} changes.
     * It increases the score as a step function of the travelled meters
     *
     * @param eventType the updated topic related to {@link MeterEditor}
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED) {
            var currentScore = getScore();
            var newScore = (int) (SCORE_FACTOR * meters / METERS_DELTA) + INITIAL_SCORE;
            var delta = newScore - currentScore;
            delta *= multiplier;
            setScore(currentScore + delta);
        }
    }
}
