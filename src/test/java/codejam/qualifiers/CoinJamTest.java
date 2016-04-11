package codejam.qualifiers;

import codejam.framework.Problem;
import codejam.problems.qualifiers.CoinJam;
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
public class CoinJamTest {

    private static String INPUT_PATH = PathUtil.inPath(ROUND_QUALIFIERS, "C");
    private static String OUTPUT_PATH = PathUtil.outPath(ROUND_QUALIFIERS, "C");

    private Problem problem = new CoinJam(INPUT_PATH, OUTPUT_PATH);

    @Test
    @Parameters({
        "C-default",
    })
    public void testDataSet(String dataSetName) throws IOException {
        problem.solve(dataSetName);

        assertOutIsExpected(OUTPUT_PATH, dataSetName);
    }

    @Test
    @Ignore
    @Parameters({
        "C-default, 1",
    })
    public void testSpecificTest(String dataSetName, int testNb) throws IOException {
        problem.solve(dataSetName, OptionalInt.of(testNb));

        assertRawOutSameAsExpected(OUTPUT_PATH, dataSetName);
    }

    @Test
    @Ignore
    @Parameters({
        //"C-MyTest"
        //"C-small-attempt0"
        //"C-large"
    })
    public void solveDataSet(String dataSetName) throws IOException {
        problem.solve(dataSetName);
    }

}
