package FileHandling;

class FilePath {
    //private static String FILE_NAME = "test";
    //private static String FILE_NAME = "a_example";
    //private static String FILE_NAME = "b_lovely_landscapes";
    private static String FILE_NAME = "c_memorable_moments";
    //private static String FILE_NAME = "d_pet_pictures";
    //private static String FILE_NAME = "e_shiny_selfies";

    private static String RESOURCE_DIR = "resource\\";

    private static String RESOURCE_EXT = ".possible";
    private static String INPUT_EXT = ".txt";
    private static String OUTPUT_EXT = ".out";

    static String resourcePath = RESOURCE_DIR + FILE_NAME + RESOURCE_EXT;
    static String inputPath = LocalPath.DIR + FILE_NAME + INPUT_EXT;
    static String outputPath = LocalPath.DIR + FILE_NAME + OUTPUT_EXT;
}
