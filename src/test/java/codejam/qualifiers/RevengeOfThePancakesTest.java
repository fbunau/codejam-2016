package codejam.qualifiers;

import codejam.framework.Problem;
import codejam.problems.qualifiers.RevengeOfThePancakes;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.PathUtil;

import java.io.IOException;
import java.util.OptionalInt;

import static util.CompareFilesTestUtil.assertOutIsExpected;
import static util.CompareFilesTestUtil.assertRawOutSameAsExpected;
import static util.PathUtil.ROUND_QUALIFIERS;

@RunWith(JUnitParamsRunner.class)
public class RevengeOfThePancakesTest {

    private static String INPUT_PATH = PathUtil.inPath(ROUND_QUALIFIERS, "B");
    private static String OUTPUT_PATH = PathUtil.outPath(ROUND_QUALIFIERS, "B");

    private Problem problem = new RevengeOfThePancakes(INPUT_PATH, OUTPUT_PATH);

    @Test
    @Parameters({
        "B-default",
    })
    public void testDataSet(String dataSetName) throws IOException {
        problem.solve(dataSetName);

        assertOutIsExpected(OUTPUT_PATH, dataSetName);
    }

    @Test
    @Ignore
    @Parameters({
        "B-default, 2",
    })
    public void testSpecificTest(String dataSetName, int testNb) throws IOException {
        problem.solve(dataSetName, OptionalInt.of(testNb));

        assertRawOutSameAsExpected(OUTPUT_PATH, dataSetName);
    }

    @Test
    @Ignore
    @Parameters({
        //"B-small-attempt0"
        //"B-large"
    })
    public void solveDataSet(String dataSetName) throws IOException {
        problem.solve(dataSetName);
    }

}
