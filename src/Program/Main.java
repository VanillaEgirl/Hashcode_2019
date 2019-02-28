package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Photo> photos = FileReader.readLines();
    }
}
