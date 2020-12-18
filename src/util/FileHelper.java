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
            boolean fileExists = Files.exists(Paths.get(filepath));

            if(!fileExists) {
                Files.createFile(Paths.get(filepath));
            }

            data = Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return data;
    }

    public static void spit(String filename, List<String> contents) {
        try {
            boolean fileExists = Files.exists(Paths.get(filename));
            Files.write(Paths.get(filename), contents, fileExists ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE);
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
