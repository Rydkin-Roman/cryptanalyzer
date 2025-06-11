package org.cryptanalyzer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void writeFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, content);
    }

    public static String getOutputPath(String inputFilePath, String endName) {
        Path inputPath = Paths.get(inputFilePath);
        Path directory = inputPath.getParent() != null ? inputPath.getParent() : Paths.get(".");
        String originalFileName = inputPath.getFileName().toString();

        int dotIndex = originalFileName.lastIndexOf('.');
        String baseName = (dotIndex != -1) ? originalFileName.substring(0, dotIndex) : originalFileName;

        String newFileName = baseName + endName;

        Path outputPath = directory.resolve(newFileName);
        return outputPath.toString();
    }


}
