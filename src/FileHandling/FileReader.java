package FileHandling;

import Program.Photo;
import Program.Slide;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<Photo> readPhotos() {
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
                photo.id = i;
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

    public static List<Slide> readSlides(List<Photo> photos) {
        List<Slide> slides = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(FilePath.resourcePath));

            String line = reader.readLine();
            String fractals[] = line.split(" ");
            int numberOfRows = Integer.parseInt(fractals[0]);

            for (int i = 0; i < numberOfRows; i++) {
                line = reader.readLine();
                System.out.println(line);
                Slide slide = parseSlide(line, photos);
                slides.add(slide);
            }

            reader.close();

            return slides;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Slide parseSlide(String line, List<Photo> photos) {
        String fractals[] = line.split(" ");

        Slide slide;

        if(fractals.length > 1) {
            int id1 = Integer.parseInt(fractals[0]);
            int id2 = Integer.parseInt(fractals[1]);
            slide = new Slide(photos.get(id1), photos.get(id2));
        } else {
            int id = Integer.parseInt(fractals[0]);
            slide = new Slide(photos.get(id));
        }

        return slide;
    }
}
