package it.unisa.theneverendingrun.services.score;

/**
 *
 * A common interface for all the subscribers interested in observing the {@link ScoreMetersListener} score variable
 */
public interface ScoreListener {

    /**
     *
     * The {@link ScoreListener} listener reaction when the observed variable {@code score} changes
     *
     * @param eventType the updated topic related to {@link ScoreMetersListener}
     * @param score the new value for the observed variable
     */
    void update(ScoreEventType eventType, int score);
}
