package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

public class Main {

    public static void main(String[] args) {
        FileReader.readResources();
        int[][] matrix = FileReader.readMatrix();
        System.out.println();
        FileWriter.writeMatrix(matrix);
    }
}
