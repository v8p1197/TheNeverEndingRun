package it.unisa.theneverendingrun.services.persistence;

import java.io.*;

/**
 * A concrete {@link StreamFactory} that creates files as streams
 */
public class FileStreamFactory implements StreamFactory {

    /**
     * The file path to use to get and receive data
     */
    private String filename;

    /**
     * Initializes the {@code filename} field
     *
     * @param filename the file path
     */
    public FileStreamFactory(String filename) {
        this.filename = filename;
    }

    /**
     * Creates a file-based input stream
     *
     * @return a FileInputStream from which data can be loaded
     * @throws FileNotFoundException if the method is not able to open the file, or if the file doesn't exist
     */
    @Override
    public InputStream createInputStream() throws FileNotFoundException {
        return new FileInputStream(filename);
    }

    /**
     * Creates a file-based output stream
     *
     * @return a FileOutputStream on which data can be saved
     * @throws FileNotFoundException if the method is not able to open the file
     */
    @Override
    public OutputStream createOutputStream() throws FileNotFoundException {
        return new FileOutputStream(filename);
    }
}
