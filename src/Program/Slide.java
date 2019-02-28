package Program;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Slide {
    public List<Photo> photos = new ArrayList<>();
    public List<String> tags = new ArrayList<>();

    public Slide(Photo photo) {
        this.photos.add(photo);
        tags.addAll(photo.tags);
    }

    public Slide(Photo photo1, Photo photo2) {
        this.photos.add(photo1);
        this.photos.add(photo2);

        List<String> tags = new ArrayList<>();

        tags.addAll(photo1.tags);
        tags.addAll(photo2.tags);

        this.tags = tags.stream().distinct().collect(Collectors.toList());
    }


    public int sameTags(Slide slide) {
        int number = 0;

        for (String tag : this.tags) {
            if (slide.tags.contains(tag)) {
                number++;
            }
        }

        return number;
    }

    public int differentTagsThan(Slide slide) {
        int number = 0;

        for (String tag : this.tags) {
            if (!slide.tags.contains(tag)) {
                number++;
            }
        }

        return number;
    }

    public int howInteresting(Slide slide) {
        int minimum;

        int onlySlide1 = this.differentTagsThan(slide);
        int onlySlide2 = slide.differentTagsThan(this);

        minimum = sameTags(slide);

        if (onlySlide1 < minimum) minimum = onlySlide1;
        if (onlySlide2 < minimum) minimum = onlySlide2;

        return minimum;
    }
}
