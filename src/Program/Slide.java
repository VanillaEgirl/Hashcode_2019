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

    public int howInteresting(Slide slide) {
        int onlySlide1 = 0;
        int onlySlide2 = 0;
        int both = 0;
        int minimum;

        List<String> tags1 = getTags();
        List<String> tags2 = slide.getTags();

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
