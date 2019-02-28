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
}
