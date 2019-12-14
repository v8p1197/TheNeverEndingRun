package it.unisa.theneverendingrun.streamManager;

public class BestScores {

    private int bestScore;
    private int longestRun;

    public BestScores(int bestScore, int longestRun) {
        this.bestScore = bestScore;
        this.longestRun = longestRun;
    }

    public int getBestScore() {
        return bestScore;
    }

    public int getLongestRun() {
        return longestRun;
    }
}
