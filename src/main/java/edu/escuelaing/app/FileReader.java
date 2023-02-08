package edu.escuelaing.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private static String HOME = System.getProperty("user.home");
    String type;
    String file;

    public FileReader(String path) {
        String[] pathList = path.split("/");
        if (pathList.length < 3) {
            type = "html";
            Path filePath = Paths.get(HOME);
            String fileNames = "";
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(filePath)) {
                for (Path files : directoryStream) {
                    if (!Files.isHidden(files)){
                        fileNames += "\r\n" + files.toString();
                    }
                }
            } catch (IOException ex) {
            }
            file = fileNames;
        } else {
            path = path.replace("/file", HOME);
            Path filePath = Paths.get(path);
            if (!Files.exists(filePath)) {
                type = "html";
                file = "no existe el archivo";
            } else {
                if (Files.isDirectory(filePath)) {
                    type = "html";
                    String fileNames = "";
                    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(filePath)) {
                        for (Path files : directoryStream) {
                            if (!Files.isHidden(files)){
                                fileNames += "\r\n" + files.toString();
                            }
                        }
                    } catch (IOException ex) {
                    }
                    file = fileNames;
                } else if (Files.isRegularFile(filePath)) {
                    type = filePath.toString().split("\\.")[1];
                    try {
                        byte[] bs = Files.readAllBytes(filePath);
                        file = new String(bs);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getHeader() {
        switch (type) {
            case "html": {
                return "HTTP/1.1 200 OK\r\n"
                        + "Access-Control-Allow-Origin: *\r\n"
                        + "Content-Type:text/html \r\n"
                        + "\r\n";
            }
            case "js": {
                return "HTTP/1.1 200 OK\r\n"
                        + "Access-Control-Allow-Origin: *\r\n"
                        + "Content-Type:text/javascript \r\n"
                        + "\r\n";
            }
            case "jpeg": {
                return "HTTP/1.1 200 OK\r\n"
                        + "Access-Control-Allow-Origin: *\r\n"
                        + "Content-Type:image/jpeg \r\n"
                        + "\r\n";
            }
            case "png": {
                return "HTTP/1.1 200 OK\r\n"
                        + "Access-Control-Allow-Origin: *\r\n"
                        + "Content-Type:image/png \r\n"
                        + "\r\n";
            }
            case "css": {
                return "HTTP/1.1 200 OK\r\n"
                        + "Access-Control-Allow-Origin: *\r\n"
                        + "Content-Type:text/css \r\n"
                        + "\r\n";
            }
            default: {
                return "HTTP/1.1 200 OK\r\n"
                        + "Access-Control-Allow-Origin: *\r\n"
                        + "Content-Type:text/html \r\n"
                        + "\r\n";
            }
        }
    }

    public String getBody() {
        return file;
    }

}
