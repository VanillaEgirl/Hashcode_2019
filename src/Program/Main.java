package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Photo> photos = FileReader.readPhotos();
        Slide slide = new Slide();
        slide.photos.add(photos.get(0));
        slide.photos.add(photos.get(1));
        Slide slide2 = new Slide();
        slide2.photos.add(photos.get(2));
        slide2.photos.add(photos.get(3));
        List<Slide> slides = new ArrayList<>();
        slides.add(slide);
        slides.add(slide2);
        FileWriter.writeSlideshow(slides);
    }
}
