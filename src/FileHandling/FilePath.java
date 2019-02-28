package FileHandling;

class FilePath {
    private static String FILE_NAME = "testMatrix";
    //private static String FILE_NAME = "a_example";
    //private static String FILE_NAME = "b_small";
    //private static String FILE_NAME = "c_medium";
    //private static String FILE_NAME = "d_big";

    private static String RESOURCE_DIR = "..\\..\\resource\\";

    private static String RESOURCE_EXT = ".possible";
    private static String INPUT_EXT = ".in";
    private static String OUTPUT_EXT = ".out";

    static String resourcePath = RESOURCE_DIR + FILE_NAME + RESOURCE_EXT;
    static String inputPath = LocalPath.DIR + FILE_NAME + INPUT_EXT;
    static String outputPath = LocalPath.DIR + FILE_NAME + OUTPUT_EXT;
}
