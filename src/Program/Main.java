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

    public static int howInteresting(Slide slide1, Slide slide2) {
        int onlySlide1 = 0;
        int onlySlide2 = 0;
        int both = 0;
        int minimum;

        List<String> tags1 = slide1.getTags();
        List<String> tags2 = slide2.getTags();

        for (String tag : tags1) {
            if (tags2.contains(tag)) {
                both++;
            } else {
                onlySlide1++;
            }
        }

        for (String tag : tags2) {
            if (!tags1.contains(tag)) {
                onlySlide1++;
            }
        }

        minimum = both;
        if (onlySlide1 < minimum) minimum = onlySlide1;
        if (onlySlide2 < minimum) minimum = onlySlide2;

        return minimum;
    }
}
