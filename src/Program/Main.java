package Program;

import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random random = new Random();

    private static final int SAME_TAGS = 1;
    private static final int MIN_WASTE = 2;
    private static final int MAX_INTERESTING = 5;
    private static final double WEIGHT = 2;
    private static final double MAX_MATCH_SCORE = 4;
    private static final int CHUNK_SIZE = 800;

    public static void main(String[] args) {
        List<Photo> photos = FileReader.readPhotos();
        List<Slide> slides = FileReader.readSlides(photos);
        System.out.println("slides generated");
        slides = shuffleSlides(slides);
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
        System.out.println();
        System.out.println(calculateScore(orderedSlides));
    }

    private static List<Slide> shuffleSlides(List<Slide> slides) {
        List<Slide> shuffledSlides = new ArrayList<>();

        List<Slide> remainingSlides = new ArrayList<>();
        remainingSlides.addAll(slides);

        for (int i = 0; i < slides.size(); i++) {
            int randomNumber = random.nextInt(remainingSlides.size());
            Slide currentSlide = remainingSlides.get(randomNumber);
            shuffledSlides.add(currentSlide);
            remainingSlides.remove(currentSlide);
        }

        return shuffledSlides;
    }

    private static List<Slide> orderSlides(List<Slide> slides) {
        List<Slide> orderedSlides = new ArrayList<>();

        List<Slide> remainingSlides = new ArrayList<>();
        remainingSlides.addAll(slides);

        Slide currentSlide = slides.get(0);
        orderedSlides.add(currentSlide);
        remainingSlides.remove(currentSlide);

        int counter = 0;
        while (remainingSlides.size() > 0) {
            double bestMatchScore = -500;
            int bestMatchIndex = -1;
            for (Slide slide2 : remainingSlides) {
                int interesting = currentSlide.howInteresting(slide2);
                int waste = currentSlide.howWasting(slide2);
                double matchscore = interesting - waste * WEIGHT;
                if (matchscore > MAX_MATCH_SCORE) {
                    counter++;
                    bestMatchIndex = slides.indexOf(slide2);
                    break;
                }
                if (matchscore > bestMatchScore) {
                    bestMatchScore = matchscore;
                    bestMatchIndex = slides.indexOf(slide2);
                }
            }
            currentSlide = slides.get(bestMatchIndex);
            orderedSlides.add(currentSlide);
            remainingSlides.remove(currentSlide);
        }
        System.out.println(counter);

        return orderedSlides;
    }

    private static int calculateScore(List<Slide> slides) {
        int score = 0;

        Slide lastSilde = slides.get(0);

        for (int i = 1; i < slides.size(); i++) {
            Slide slide = slides.get(i);
            score += lastSilde.howInteresting(slide);
            lastSilde = slide;
        }

        return score;
    }
}
