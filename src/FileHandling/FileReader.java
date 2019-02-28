package FileHandling;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<String> readLines() {

        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(FilePath.inputPath));

            String line = reader.readLine();
            String fractals[] = line.split(" ");
            int numberOfRows = Integer.parseInt(fractals[0]);

            for (int i = 0; i < numberOfRows; i++) {
                line = reader.readLine();
                System.out.println(line);
                lines.add(line);
            }

            reader.close();

            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
