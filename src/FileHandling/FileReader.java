package FileHandling;

import Program.Photo;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<Photo> readLines() {
        List<Photo> photos = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(FilePath.inputPath));

            String line = reader.readLine();
            String fractals[] = line.split(" ");
            int numberOfRows = Integer.parseInt(fractals[0]);

            for (int i = 0; i < numberOfRows; i++) {
                line = reader.readLine();
                System.out.println(line);
                Photo photo = parseLine(line);
                photos.add(photo);
            }

            reader.close();

            return photos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Photo parseLine(String line) {
        String fractals[] = line.split(" ");
        Photo photo = new Photo();
        photo.horizontal = fractals[0].equals("H");

        int numberOfTags = Integer.parseInt(fractals[1]);

        for (int i = 0; i < numberOfTags; i++) {
            photo.tags.add(fractals[i + 2]);
        }

        return photo;
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
