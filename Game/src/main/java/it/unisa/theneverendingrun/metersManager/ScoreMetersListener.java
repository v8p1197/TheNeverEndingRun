package it.unisa.theneverendingrun.metersManager;

/**
 * A {@link MetersListener} that computes the store depending on the {@link MeterEditor} meters variable value
 */
class ScoreMetersListener implements MetersListener {

    /**
     * The factor the meters are multiplied with in order to compute the score
     */
    private static final int SCORE_FACTOR = 10;

    /**
     * The score when the game begins, i.e. when 0 meters have been travelled
     */
    private static final int INITIAL_SCORE = 0;

    /**
     * The game score
     */
    private int score;

    /**
     * Initialises the score variable
     */
    public ScoreMetersListener() {
    }

    /**
     * {@code SCORE_FACTOR} getter
     *
     * @return the factor the meters are multiplied with in order to compute the score
     */
    static int getScoreFactor() {
        return SCORE_FACTOR;
    }

    /**
     * {@code INITIAL_SCORE} getter
     *
     * @return the score value when the game begins, i.e. when 0 meters have been travelled
     */
    static int getInitialScore() {
        return INITIAL_SCORE;
    }

    /**
     * {@code score} getter
     *
     * @return the game score value
     */
    int getScore() {
        return score;
    }

    /**
     * {@code score} setter
     *
     * @param score the new value for the score variable
     */
    private void setScore(int score) {
        this.score = score;
    }

    /**
     * The {@link ScoreMetersListener} listener reaction when the observed variable {@code meters} changes.
     * It increases the score as a linear function of the travelled meters
     *
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(int meters) {
        setScore(SCORE_FACTOR * meters + INITIAL_SCORE);
    }
}
