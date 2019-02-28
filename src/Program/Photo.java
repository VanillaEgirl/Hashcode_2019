package Program;

import java.util.ArrayList;
import java.util.List;

public class Photo {
    public int id;
    public boolean horizontal;
    public boolean alreadyInSlide = false;
    public List<String> tags = new ArrayList<>();

    public int sameTags(Photo photo) {
        int number = 0;

        for (String tag : this.tags) {
            if (photo.tags.contains(tag)) {
                number++;
            }
        }

        return number;
    }

    public int differentTagsThan(Photo photo) {
        int number = 0;

        for (String tag : this.tags) {
            if (!photo.tags.contains(tag)) {
                number++;
            }
        }

        return number;
    }
}
