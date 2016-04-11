package codejam.framework;

import java.io.*;
import java.util.OptionalInt;

public abstract class Problem<IM, OM> {

    public static String IN_EXTENSION = "in";
    public static String OUT_EXTENSION = "out";

    private final String dataSetPath;
    private final String outputPath;

    public Problem(String dataSetPath, String outputPath) {
        this.dataSetPath = dataSetPath;
        this.outputPath = outputPath;
    }

    public void solve(String dataSetName) throws IOException {
        solve(dataSetName, OptionalInt.empty());
    }

    public void solve(String dataSetName, OptionalInt debugTestNb) throws IOException {

        PrintWriter writer = new PrintWriter(outputPath + dataSetName + "." + OUT_EXTENSION, "UTF-8");

        try (BufferedReader br = new BufferedReader(new FileReader(new File(dataSetPath + dataSetName + "." + IN_EXTENSION)))) {
            String line = br.readLine();
            int nbOfTests = Integer.parseInt(line);

            for (int t = 1; t <= nbOfTests; ++t) {
                IM input = parseTestInput(br);
                if (debugTestNb.isPresent() && debugTestNb.getAsInt() != t) {
                    writer.print(String.format("Case #%d: SKIPPED\n", t));
                    continue;
                }
                OM answer = solveTest(input);
                writer.print(String.format("Case #%d:%s\n", t, answer.toString()));
            }
        }

        writer.flush();
        writer.close();
    }

    protected abstract OM solveTest(IM inputModel);

    protected abstract IM parseTestInput(BufferedReader br) throws IOException;


}
