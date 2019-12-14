package it.unisa.theneverendingrun.streamManager;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamFactory {

    InputStream createInputStream() throws FileNotFoundException;

    OutputStream createOutputStream() throws FileNotFoundException;
}
