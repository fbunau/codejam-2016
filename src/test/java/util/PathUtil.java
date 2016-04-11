package util;

public interface PathUtil {

    String ROUND_QUALIFIERS = "qualifiers";

    String INPUT_PATH = "./src/test/resources/%s/in/%s/";
    String OUTPUT_PATH = "./src/test/resources/%s/out/%s/";

    static String inPath(String round, String problem) {
        return String.format(INPUT_PATH, round, problem);
    }

    static String outPath(String round, String problem) {
        return String.format(OUTPUT_PATH, round, problem);
    }

}
