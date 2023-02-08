package edu.escuelaing.app;

import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import edu.escuelaing.app.services.Service;
import edu.escuelaing.app.services.WebPage;

/**
 * Servidor ws que nos permite enviar y recibir elementos por ws
 * @author Luis Felipe Giraldo Rodriguez
 * @version 1.0
 */
public final class HttpServer {
    private static HttpServer instance;
    
    /**
     * Retorna la instancia del servidor, en caso de que no exista lo crea
     * @return instancia del servidor
     */
    public static HttpServer getInstance() {
        if (instance == null) {
            instance = new HttpServer();
        }
        return instance;
    }

    /**
     * Metodo principal que nos inicia un servidor socket http
     * @param args
     * @throws IOException
     */
    public void run(String[] args) throws IOException {
        System.out.println("Servidor funcionando ...");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);

        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            String path = "";
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("GET")) {
                    path = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            Service servicio = null;
            switch (path) {
                case "/web/": {
                    servicio = new WebPage();
                }
                case "/":{
                    servicio = new WebPage();
                }
            };
            if (servicio!= null) {
                outputLine = servicio.getHeader()
                    + servicio.getBody();
            }
            
            else if (path.contains("/file/")){
                FileReader fileReader = new FileReader(path);
                outputLine = fileReader.getHeader()+ fileReader.getBody();
            }
            else {
                outputLine = "HTTP/1.1 404 Not Found" + 
                "\r\n"
                + "Content-Type:text/html \r\n"
                + "\r\n"
                + "<html>"+
                "<body>"+
                ""+
                "<h1>Pagina no encontrada!</h1>"+
                ""+
                "<img src=\"https://media.giphy.com/media/3o7aCTPPm4OHfRLSH6/giphy.gif\" alt=\"Computer man\">"+
                ""+
                "</body>"+
                "</html>";                        
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
        System.out.println("Servidor apagado.");
    }
}