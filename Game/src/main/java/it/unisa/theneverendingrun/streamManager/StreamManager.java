package it.unisa.theneverendingrun.streamManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StreamManager {

    private StreamFactory streamFactory;

    public StreamManager(StreamFactory streamFactory) {
        this.streamFactory = streamFactory;
    }

    public void saveBestScore(BestScores bestScores) {
        try {
            var outputStream = streamFactory.createOutputStream();
            var dataOutputStream = new DataOutputStream(outputStream);

            var bestScore = bestScores.getBestScore();
            var longestRun = bestScores.getLongestRun();

            dataOutputStream.writeInt(bestScore);
            dataOutputStream.writeInt(longestRun);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BestScores loadBestScore() {
        try {
            var inputStream = streamFactory.createInputStream();
            var dataInputStream = new DataInputStream(inputStream);

            var bestScore = dataInputStream.readInt();
            var longestRun = dataInputStream.readInt();
            dataInputStream.close();

            return new BestScores(bestScore, longestRun);
        } catch (IOException e) {
            return new BestScores(0, 0);
        }
    }


}
