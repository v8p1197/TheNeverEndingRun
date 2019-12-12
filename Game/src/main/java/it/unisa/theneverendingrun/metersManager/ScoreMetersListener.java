package it.unisa.theneverendingrun.metersManager;

class ScoreMetersListener implements MetersListener {

    private int score;

    public ScoreMetersListener() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    /**
     * The {@link ScoreMetersListener} listener reaction when the observed variable {@code meters} changes.
     * In increases the score as a linear function of the travelled meters
     *
     * @param meters the new value for the observed variable
     */
    @Override
    public void update(int meters) {
        setScore(10 * meters);
    }
}
