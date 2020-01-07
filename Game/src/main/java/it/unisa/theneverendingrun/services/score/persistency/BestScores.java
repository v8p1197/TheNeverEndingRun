package it.unisa.theneverendingrun.services.score.persistency;

/**
 * This class is delegated for saving the game best scores, i.e. the highest score in points and the longest run in meters
 */
public class BestScores {

    /**
     * The game highest score in points
     */
    private int highScore;

    /**
     * The game longest run in meters
     */
    private int longestRun;

    private BestScores() { }

    private static class BestScoresHolder {
        static final BestScores instance = new BestScores();
    }

    public static BestScores getInstance() {
        return BestScoresHolder.instance;
    }

    /**
     * {@code highScore} getter
     *
     * @return the game highest score in points
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * {@code longestRun} getter
     *
     * @return the game longest run in meters
     */
    public int getLongestRun() {
        return longestRun;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setLongestRun(int longestRun) {
        this.longestRun = longestRun;
    }
}
