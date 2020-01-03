package it.unisa.theneverendingrun.services.persistence;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This class is delegated to load (save) data from (to) a specific {@link java.io.InputStream}
 */
public class StreamManager {

    /**
     * The stream factory which produces different I/O streams depending on its concrete subclass
     */
    private StreamFactory streamFactory;

    /**
     * Initializes the {@code streamFactory} field
     *
     * @param streamFactory the factory that creates input and output streams
     */
    public StreamManager(StreamFactory streamFactory) {
        this.streamFactory = streamFactory;
    }

    /**
     * Saves a {@link BestScores} object on the output stream
     *
     * @param bestScores the best scores to save
     */
    public void saveBestScores(BestScores bestScores) {
        try {
            var outputStream = streamFactory.createOutputStream();
            var dataOutputStream = new DataOutputStream(outputStream);

            var highScore = bestScores.getHighScore();
            var longestRun = bestScores.getLongestRun();

            dataOutputStream.writeInt(highScore);
            dataOutputStream.writeInt(longestRun);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a {@link BestScores} object from the input stream
     *
     * @return the best scores loaded
     */
    public BestScores loadBestScores() {
        try {
            var inputStream = streamFactory.createInputStream();
            var dataInputStream = new DataInputStream(inputStream);

            var highScore = dataInputStream.readInt();
            var longestRun = dataInputStream.readInt();
            dataInputStream.close();

            return new BestScores(highScore, longestRun);
        } catch (IOException e) {
            return new BestScores(0, 0);
        }
    }


}
