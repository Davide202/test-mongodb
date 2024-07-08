package com.example.mongodbtests.util;

import lombok.Synchronized;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

@UtilityClass
public class FileUtil {

    private final String dir = "src/main/resources/";
    private final String errorFile = "ERRORS.txt";
    private final String newLine = "\r\n";

    @Synchronized
    @SuppressWarnings({"java:S2095"})
    public void append(String content)  {
        try {
            Files.createDirectories(Path.of(dir));
            Path file = Paths.get(dir+errorFile);
//          file = Path.of(dir+errorFile);

            if (!file.toFile().exists() && file.toFile().createNewFile()){
                System.out.println("Created file "+errorFile);
            }
            FileWriter writer = new FileWriter(file.toFile(),true);
            writer.append(newLine).append(content);
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
