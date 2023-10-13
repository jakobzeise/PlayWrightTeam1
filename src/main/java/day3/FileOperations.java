package day3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

    public static void main(String[] args) {

        try {
            File file = new File("log.txt");
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Hello");

            fileWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void writeToFile(String text) {

        // try with resources -> it will close automatically
        try (FileWriter fileWriter = new FileWriter("log.txt")) {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TestNG JUnit
}
