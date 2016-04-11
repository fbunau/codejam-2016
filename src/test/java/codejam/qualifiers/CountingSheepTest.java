package codejam.qualifiers;

import codejam.framework.Problem;
import codejam.problems.qualifiers.CountingSheep;
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
public class CountingSheepTest {

    private static String INPUT_PATH = PathUtil.inPath(ROUND_QUALIFIERS, "A");
    private static String OUTPUT_PATH = PathUtil.outPath(ROUND_QUALIFIERS, "A");

    private Problem problem = new CountingSheep(INPUT_PATH, OUTPUT_PATH);

    @Test
    @Parameters({
        "A-default",
    })
    public void testDataSet(String dataSetName) throws IOException {
        problem.solve(dataSetName);

        assertOutIsExpected(OUTPUT_PATH, dataSetName);
    }

    @Test
    @Ignore
    @Parameters({
        "A-default, 2",
    })
    public void testSpecificTest(String dataSetName, int testNb) throws IOException {
        problem.solve(dataSetName, OptionalInt.of(testNb));

        assertRawOutSameAsExpected(OUTPUT_PATH, dataSetName);
    }


    @Test
    @Ignore
    @Parameters({
        //"A-small-attempt0"
        //"A-large"
    })
    public void solveDataSet(String dataSetName) throws IOException {
        problem.solve(dataSetName);
    }

}
