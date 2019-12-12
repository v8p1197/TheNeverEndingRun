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
     * The game score
     */
    private int score;

    /**
     * Initialises the score variable
     */
    public ScoreMetersListener() {
        score = 0;
    }

    static int getScoreFactor() {
        return SCORE_FACTOR;
    }

    /**
     * score getter
     *
     * @return the game score value
     */
    public int getScore() {
        return score;
    }

    /**
     * score setter
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
        setScore(SCORE_FACTOR * meters);
    }
}
