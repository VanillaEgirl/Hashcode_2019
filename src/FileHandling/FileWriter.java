package FileHandling;

import java.io.PrintWriter;
import java.util.List;

public class FileWriter {
    private static final String ENCODING = "UTF-8";

    public static void writeLines(List<String> lines) {
        String filePath = FilePath.outputPath;

        try {
            PrintWriter writer = new PrintWriter(filePath, ENCODING);

            System.out.println(lines.size());
            writer.println(lines.size());

            for (String line : lines) {
                System.out.println(line);
                writer.println(line);
            }

            writer.close();
        } catch (Exception e) {
            System.err.println("Exception occurred trying to write " + filePath);
            e.printStackTrace();
        }
    }

    public static void writeMatrix(int[][] matrix) {
        String filePath = FilePath.outputPath;

        try {
            PrintWriter writer = new PrintWriter(filePath, ENCODING);

            System.out.println(matrix.length + " " + matrix[0].length);
            writer.println(matrix.length + " " + matrix[0].length);

            for (int[] row : matrix) {
                for(int unit : row) {
                    System.out.print(unit + " ");
                    writer.print(unit + " ");
                }
                System.out.println();
            }

            writer.close();
        } catch (Exception e) {
            System.err.println("Exception occurred trying to write " + filePath);
            e.printStackTrace();
        }
    }
}
