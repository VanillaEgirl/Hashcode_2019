package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> lines = FileReader.readLines();
        System.out.println();
        FileWriter.writeLines(lines);
    }
}
