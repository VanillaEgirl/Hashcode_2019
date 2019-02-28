package FileHandling;

class FilePath {
    private static String INPUT_FILE = "test";
    //private static String INPUT_FILE = "a_example";
    //private static String INPUT_FILE = "b_small";
    //private static String INPUT_FILE = "c_medium";
    //private static String INPUT_FILE = "d_big";
    private static String INPUT_EXT = ".in";
    private static String OUTPUT_EXT = ".out";
    static String inputPath = LocalPath.DIR + INPUT_FILE + INPUT_EXT;
    static String outputPath = LocalPath.DIR + INPUT_FILE + OUTPUT_EXT;
}
