package edu.escuelaing.app;

import java.io.IOException;

import edu.escuelaing.app.services.Service;
import edu.escuelaing.app.services.WebPage;

public class App {
    static HttpServer httpServer = HttpServer.getInstance();
    public static void main(String[] args) throws IOException {
        httpServer.run(args);
    }
}
