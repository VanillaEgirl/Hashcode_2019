package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Photo> photos = FileReader.readPhotos();
        getSlides(photos);
        //FileWriter.writeSlideshow(slides);
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

        for(Photo photo : verticalPhotos) {
            if(!photo.alreadyInSlide) {
                int minValue = 99999;
                int minIndex = -1;
                for (Photo photo2 : verticalPhotos) {
                    int sameTags = 99999;
                    if (!photo2.alreadyInSlide) {
                        sameTags = photo.sameTags(photo2);
                        if(sameTags<minValue) {
                            minIndex = verticalPhotos.indexOf(photo2);
                        }
                    }
                    if(sameTags == 0) {
                        break;
                    }
                }

                Slide slide = new Slide();
                slide.photos.add(photo);
                photo.alreadyInSlide = true;
                if(minIndex != -1) {
                    Photo photo2 = verticalPhotos.get(minIndex);
                    slide.photos.add(photo2);
                    photo2.alreadyInSlide = true;
                }
            }
        }

        return slides;
    }
}
