package it.unisa.theneverendingrun.streamManager;

import java.io.*;

public class FileStreamFactory implements StreamFactory {

    private String filename;

    public FileStreamFactory(String filename) {
        this.filename = filename;
    }

    @Override
    public InputStream createInputStream() throws FileNotFoundException {
        return new FileInputStream(filename);
    }

    @Override
    public OutputStream createOutputStream() throws FileNotFoundException {
        return new FileOutputStream(filename);
    }
}
