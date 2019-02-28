package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Photo> photos = FileReader.readPhotos();
        List<Slide> slides = getSlides(photos);
        System.out.println("slides generated");
        List<Slide> orderedSlides = orderSlides(slides);
        FileWriter.writeSlideshow(orderedSlides);
    }

    private static List<Slide> getSlides(List<Photo> photos) {
        List<Slide> slides = new ArrayList<>();
        List<Photo> verticalPhotos = new ArrayList<>();

        for (Photo photo : photos) {
            if (photo.horizontal) {
                Slide slide = new Slide();
                slide.photos.add(photo);
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
                    if (sameTags == 0) {
                        break;
                    }
                }

                Slide slide = new Slide();
                slide.photos.add(photo);
                photo.alreadyInSlide = true;
                if (minIndex != -1) {
                    Photo photo2 = verticalPhotos.get(minIndex);
                    slide.photos.add(photo2);
                    photo2.alreadyInSlide = true;
                }
                slides.add(slide);
            }
        }

        return slides;
    }

    private static List<Slide> orderSlides(List<Slide> slides) {
        List<Slide> orderedSlides = new ArrayList<>();

        List<Slide> remainingSlides = new ArrayList<>();
        remainingSlides.addAll(slides);

        Slide currentSlide = slides.get(0);
        orderedSlides.add(currentSlide);
        remainingSlides.remove(currentSlide);

        while (remainingSlides.size() > 0) {
            int maxValue = -1;
            int maxIndex = -1;
            for (Slide slide2 : remainingSlides) {
                int interesting = currentSlide.howInteresting(slide2);
                if (interesting > maxValue) {
                    maxValue = interesting;
                    maxIndex = slides.indexOf(slide2);
                }
            }
            currentSlide = slides.get(maxIndex);
            orderedSlides.add(currentSlide);
            remainingSlides.remove(currentSlide);
        }

        return orderedSlides;
    }
}
