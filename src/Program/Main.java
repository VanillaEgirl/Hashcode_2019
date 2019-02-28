package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int CHUNK_SIZE = 500;

    public static void main(String[] args) {
        List<Photo> photos = FileReader.readPhotos();
        List<Slide> slides = getSlides(photos);
        System.out.println("slides generated");
        List<Slide> orderedSlides = new ArrayList<>();
        int i;
        for (i = 0; i < slides.size() / CHUNK_SIZE; i++) {
            List<Slide> slideChunk = slides.subList(i * CHUNK_SIZE, (i + 1) * CHUNK_SIZE - 1);
            orderedSlides.addAll(orderSlides(slideChunk));
            System.out.println("Done with chunk " + i);
        }
        if (i * CHUNK_SIZE < slides.size() - 1) {
            List<Slide> slideChunk = slides.subList(i * CHUNK_SIZE, slides.size() - 1);
            orderedSlides.addAll(orderSlides(slideChunk));
        }
        FileWriter.writeSlideshow(orderedSlides);
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
                    if (sameTags == 0) {
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

    private static List<Slide> orderSlides(List<Slide> slides) {
        List<Slide> orderedSlides = new ArrayList<>();

        List<Slide> remainingSlides = new ArrayList<>();
        remainingSlides.addAll(slides);

        Slide currentSlide = slides.get(0);
        orderedSlides.add(currentSlide);
        remainingSlides.remove(currentSlide);

        while (remainingSlides.size() > 0) {
            int matchScore = 999999;
            int bestMatchIndex = -1;
            for (Slide slide2 : remainingSlides) {
                int waste = currentSlide.howWasting(slide2);
                if (waste < 5) {
                    bestMatchIndex = slides.indexOf(slide2);
                    break;
                }
                if (waste < matchScore) {
                    matchScore = waste;
                    bestMatchIndex = slides.indexOf(slide2);
                }
            }
            currentSlide = slides.get(bestMatchIndex);
            orderedSlides.add(currentSlide);
            remainingSlides.remove(currentSlide);
        }

        return orderedSlides;
    }
}
