package Program;

import FileHandling.FileReader;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = FileReader.readMatrix();
        System.out.println();
        printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
