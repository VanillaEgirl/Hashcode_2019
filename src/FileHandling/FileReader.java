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

    public static int[][] readMatrix() {
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(FilePath.inputPath));

            String line = reader.readLine();
            String fractals[] = line.split(" ");
            int numberOfRows = Integer.parseInt(fractals[0]);
            int numberOfColumns = Integer.parseInt(fractals[1]);
            int[][] matrix = new int[numberOfRows][numberOfColumns];

            for (int i = 0; i < numberOfRows; i++) {
                line = reader.readLine();
                System.out.println(line);
                matrix[i] = parseLine(line, numberOfColumns);
            }

            reader.close();

            return matrix;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int[] parseLine(String line, int numberOfColumns) {
        int[] lineMatrix = new int[numberOfColumns];
        String fractals[] = line.split(" ");

        for (int i = 0; i < numberOfColumns; i++) {
            lineMatrix[i] = Integer.parseInt(fractals[i]);
        }

        return lineMatrix;
    }

    public static void readResources() {
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(FilePath.resourcePath));

            String line = reader.readLine();

            while (line != null && !line.isEmpty()) {
                /*String fractals[] = line.split(" ");
                shape.y = Integer.parseInt(fractals[0]);
                shape.x = Integer.parseInt(fractals[1]);*/

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
