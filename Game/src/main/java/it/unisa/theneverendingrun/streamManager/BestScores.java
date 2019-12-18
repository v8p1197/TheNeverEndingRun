package it.unisa.theneverendingrun.streamManager;

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

    /**
     * Initializes fields {@code highScore} and {@code longestRun}
     *
     * @param highScore  the new highest score
     * @param longestRun the new longest run
     */
    public BestScores(int highScore, int longestRun) {
        this.highScore = highScore;
        this.longestRun = longestRun;
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
