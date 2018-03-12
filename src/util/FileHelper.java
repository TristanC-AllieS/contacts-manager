package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class FileHelper {

    public static List<String> slurp(String filepath) {
        
        List<String> data = new ArrayList<>();

        try {
            data = Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return data;
    }

    public static void spit(String filename, List<String> contents) {
        try {
            Files.write(Paths.get(filename), contents, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void spit(String filename, List<String> contents, boolean append) {
        try {
            Files.write(Paths.get(filename), contents, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void makeExciting(String filename) {
        List<String> data = new ArrayList<>();
        for(String line : slurp(filename)) {
            data.add(line.toUpperCase() + "!");
        }
        spit(filename + ".exciting", data);
    }

}
