package Program;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Slide {
    public List<Photo> photos = new ArrayList<>();

    List<String> getTags() {
        List<String> tags = new ArrayList<>();

        for (Photo photo : photos) {
            tags.addAll(photo.tags);
        }

        return tags.stream().distinct().collect(Collectors.toList());
    }


    public int sameTags(Slide slide) {
        int number = 0;

        for (String tag : this.getTags()) {
            if (slide.getTags().contains(tag)) {
                number++;
            }
        }

        return number;
    }

    public int differentTagsThan(Slide slide) {
        int number = 0;

        for (String tag : this.getTags()) {
            if (!slide.getTags().contains(tag)) {
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
