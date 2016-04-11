package util;

import codejam.framework.Problem;
import javaslang.Tuple2;
import javaslang.collection.List;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CompareFilesTestUtil {

    private static String EXPECTED_EXTENSION = "expected";

    public static void assertOutIsExpected(String outputPath, String dataSetName) throws IOException {

        String outFilePath = String.format("%s%s.%s", outputPath, dataSetName, Problem.OUT_EXTENSION);
        String expectedFilePath = String.format("%s%s.%s", outputPath, dataSetName, EXPECTED_EXTENSION);

        List<String> outLineList;
        List<String> expectedLineList;

        try (BufferedReader br = new BufferedReader(new FileReader(outFilePath))) {
            outLineList = br.lines().collect(List.collector());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(expectedFilePath))) {
            expectedLineList = br.lines().collect(List.collector());
        }

        assertEquals("Different number of output lines", expectedLineList.size(), outLineList.size());
        expectedLineList.zip(outLineList).zipWithIndex().forEach(
            tupleWithTupleIdx -> {
                Tuple2 lineTuple = tupleWithTupleIdx._1;
                long idx = tupleWithTupleIdx._2;
                assertEquals("Line: " + idx+1, lineTuple._1, lineTuple._2);
            }
        );
    }

    public static void assertRawOutSameAsExpected(String outputPath, String dataSetName) throws IOException {
        String outFilePath = String.format("%s%s.%s", outputPath, dataSetName, Problem.OUT_EXTENSION);
        String expectedFilePath = String.format("%s%s.%s", outputPath, dataSetName, EXPECTED_EXTENSION);

        assertEquals(FileUtils.readLines(new File(expectedFilePath)), FileUtils.readLines(new File(outFilePath)));
    }

    private static String OUTPUT_PATH = "./src/test/resources/util/";

    @Test
    public void testAssertOK() throws IOException {
        assertOutIsExpected(OUTPUT_PATH, "test_assert_ok");
    }

    @Test
    public void testAssertBadNbOfLines() throws IOException {
        try {
            assertOutIsExpected(OUTPUT_PATH, "/test_assert_bad_nb_lines");
        } catch (AssertionError ae) {
            assertEquals(ae.getMessage(), "expected:<14> but was:<4>");
            return;
        }
        fail("Expected assertion error");
    }

    @Test
    public void testAssertBadLines() throws IOException {
        try {
            assertOutIsExpected(OUTPUT_PATH, "/test_assert_bad_lines");
        } catch (AssertionError ae) {
            assertEquals(ae.getMessage(), "Line: 81 expected:<Nulla r[honc]us orci faucibus por...> but was:<Nulla r[obl]us orci faucibus por...>");
            return;
        }
        fail("Expected assertion error");

    }

}
