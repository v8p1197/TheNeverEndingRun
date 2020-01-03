package it.unisa.theneverendingrun.services.persistence;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Abstract Factory that creates two products:
 * an input stream for loading data;
 * an output stream for saving data.
 */
public interface StreamFactory {

    /**
     * Creates a general input stream. Its concrete type depends on the concrete factory implementing this method
     *
     * @return an InputStream from which data can be loaded
     * @throws FileNotFoundException if the method is not able to open the stream
     */
    InputStream createInputStream() throws FileNotFoundException;

    /**
     * Creates a general output stream. Its concrete type depends on the concrete factory implementing this method
     *
     * @return an OutputStream on which data can be saved
     * @throws FileNotFoundException if the method is not able to open the stream
     */
    OutputStream createOutputStream() throws FileNotFoundException;
}
