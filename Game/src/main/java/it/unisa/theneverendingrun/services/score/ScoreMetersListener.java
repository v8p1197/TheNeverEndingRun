package it.unisa.theneverendingrun.services.score;

import com.google.common.util.concurrent.AtomicDouble;
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
     * {@link ScoreMetersListener#score} field increases by SCORE_FACTOR each {@link ScoreMetersListener#METERS_DELTA}
     * meters
     */
    public static final int SCORE_FACTOR = 10;

    /**
     *
     * How many meters the {@link ScoreMetersListener#score} variable changes
     */
    public static final int METERS_DELTA = 20;

    /**
     *
     * The score when the game begins, i.e. when{@link MeterEditor#INITIAL_METERS} meters have been travelled
     */
    public static final int INITIAL_SCORE = 0;

    /**
     *
     * The handler for all the {@link ScoreEventType} topics related to this class
     */
    private ScoreEventManager eventManager;

    /**
     *
     * The observed variable that stores the game score
     */
    private int score;

    /**
     *
     * The {@link ScoreMetersListener#score} multiplier
     */
    private AtomicDouble multiplier;

    /**
     *
     * @see ScoreMetersListener#setScore(int)
     *
     * Initializes the {@link ScoreMetersListener#score} field to {@link ScoreMetersListener#INITIAL_SCORE}
     */
    public ScoreMetersListener() {
        eventManager = new ScoreEventManager(ScoreEventType.values());

        setScore(INITIAL_SCORE);
        multiplier = new AtomicDouble(1);
    }

    /**
     *
     * @see ScoreMetersListener#score
     *
     * @return the game score
     */
    public int getScore() {
        return score;
    }

    /**
     * @see ScoreMetersListener#multiplier
     * <p>
     * The {@link ScoreMetersListener#score} multiplier
     */
    public float getMultiplier() {
        return (float)multiplier.get();
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
     * {@link ScoreMetersListener#score} setter: updates the {@code score} field and notifies all the
     * {@link ScoreListener} observers
     *
     * @param score the new score value
     */
    private void setScore(int score) {
        this.score = score;
        getEventManager().notify(ScoreEventType.SCORE_CHANGED, getScore());
    }

    /**
     * @param multiplier the new {@link ScoreMetersListener#score} multiplier value
     * @see ScoreMetersListener#multiplier
     */
    public void setMultiplier(float multiplier) {
        this.multiplier.set(multiplier);
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
            if (meters % METERS_DELTA == 0) {
                var newScore = (int)(SCORE_FACTOR * multiplier.get());
                setScore(currentScore + newScore);
            }
          /*  var newScore = SCORE_FACTOR * (int)(meters / METERS_DELTA) + INITIAL_SCORE;
            var delta = newScore - currentScore;
            delta *= multiplier;
            setScore(currentScore + delta);*/
        }
    }
}
