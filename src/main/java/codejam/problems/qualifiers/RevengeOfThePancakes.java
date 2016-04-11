package codejam.problems.qualifiers;

import codejam.framework.Problem;

import java.io.BufferedReader;
import java.io.IOException;

public class RevengeOfThePancakes extends Problem<String, String> {

    public RevengeOfThePancakes(String dataSetPath, String outputPath) {
        super(dataSetPath, outputPath);
    }

    @Override
    protected String solveTest(String pancakesStr) {
        char currentGroup = pancakesStr.charAt(0);
        int groups = 1;
        for (int i = 1; i < pancakesStr.length(); ++i) {
            char pancake = pancakesStr.charAt(i);
            if (pancake != currentGroup) {
                groups++;
                currentGroup = pancake;
            }
        }

        if (currentGroup == '+') {
            groups--;
        }

        return " " + groups;
    }

    @Override
    protected String parseTestInput(BufferedReader br) throws IOException {
        return br.readLine().trim();
    }

}
