package FileHandling;

import Program.Photo;
import Program.Slide;

import java.io.PrintWriter;
import java.util.List;

public class FileWriter {
    private static final String ENCODING = "UTF-8";

    public static void writeSlideshow(List<Slide> slides) {
        String filePath = FilePath.outputPath;

        try {
            PrintWriter writer = new PrintWriter(filePath, ENCODING);

            System.out.println(slides.size());
            writer.println(slides.size());

            for (Slide slide : slides) {
                for (Photo photo : slide.photos) {
                    System.out.print(photo.id + " ");
                    writer.print(photo.id + " ");
                }
                System.out.println();
                writer.println();
            }

            writer.close();
        } catch (Exception e) {
            System.err.println("Exception occurred trying to write " + filePath);
            e.printStackTrace();
        }
    }
}
