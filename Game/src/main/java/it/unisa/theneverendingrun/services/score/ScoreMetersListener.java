package it.unisa.theneverendingrun.services.score;

import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;
import it.unisa.theneverendingrun.services.meters.MeterEditor;

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
     * @see ScoreMetersListener#setScore(int)
     *
     * Initializes the {@code score} field to {@code INITIAL_SCORE}
     */
    public ScoreMetersListener() {
        setScore(INITIAL_SCORE);
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
     * @see ScoreMetersListener#score
     *
     * @param score the new value for the score variable
     */
    private void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * The {@link ScoreMetersListener} listener reaction when the observed variable {@code meters} changes.
     * It increases the score as a linear function of the travelled meters
     *
     * @param eventType the updated topic related to {@link MeterEditor}
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED)
            setScore(SCORE_FACTOR * meters + INITIAL_SCORE);
    }
}
