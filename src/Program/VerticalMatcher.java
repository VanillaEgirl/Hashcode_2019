package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VerticalMatcher {
    private static final int SAME_TAGS = 0;

    public static void main(String[] args) {
        List<Photo> photos = FileReader.readPhotos();
        List<Slide> slides = getSlides(photos);
        FileWriter.writeSlideshow(slides);
    }

    private static List<Slide> getSlides(List<Photo> photos) {
        List<Slide> slides = new ArrayList<>();
        List<Photo> verticalPhotos = new ArrayList<>();

        for (Photo photo : photos) {
            if (photo.horizontal) {
                Slide slide = new Slide(photo);
                slides.add(slide);
            } else {
                verticalPhotos.add(photo);
            }
        }

        List<Slide> verticalSlides = pairVerticalSlides(verticalPhotos);
        slides.addAll(verticalSlides);

        return slides;
    }

    private static List<Slide> pairVerticalSlides(List<Photo> verticalPhotos) {
        List<Slide> slides = new ArrayList<>();

        for (Photo photo : verticalPhotos) {
            if (!photo.alreadyInSlide) {
                int minValue = 99999;
                int minIndex = -1;
                for (Photo photo2 : verticalPhotos) {
                    int sameTags = 99999;
                    if (!photo2.alreadyInSlide) {
                        sameTags = photo.sameTags(photo2);
                        if (sameTags < minValue) {
                            minIndex = verticalPhotos.indexOf(photo2);
                        }
                    }
                    if (sameTags <= SAME_TAGS) {
                        break;
                    }
                }

                if (minIndex != -1) {
                    Photo photo2 = verticalPhotos.get(minIndex);
                    Slide slide = new Slide(photo, photo2);
                    photo.alreadyInSlide = true;
                    photo2.alreadyInSlide = true;

                    slides.add(slide);
                }
            }
        }

        return slides;
    }
}
