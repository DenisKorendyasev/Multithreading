package org.example.crawlingTheSite;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static String url = "";
    private static String fileName = "";
    private static final String DST_FOLDER = "";
    private static final String FILE_TYPE = "txt";
    private static final int numberOfCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        LinkExecutor linkExecutor = new LinkExecutor(url);
        String siteMap;
        siteMap = new ForkJoinPool(numberOfCores).invoke(linkExecutor);
        writeToFile(siteMap);
    }

    protected static void writeToFile(String string) {
        if (!Files.exists(Paths.get(DST_FOLDER))) {
            new File(DST_FOLDER).mkdir();
        }
        String filePath = DST_FOLDER.concat(fileName).concat(".").concat(FILE_TYPE);
        File file = new File(filePath);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.write(string);
        writer.flush();
    }
}