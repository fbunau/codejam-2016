package codejam.qualifiers.model;

import codejam.problems.qualifiers.model.CandidateGenerator;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CandidateGeneratorTest {

    @Test
    public void coinsGenerated() throws Exception {
        CandidateGenerator gen = new CandidateGenerator(3);
        String[] as = new String[4];
        String[] expected = new String[] { "1001", "1011", "1101", "1111" };
        for (int i = 0; i < 4; ++i) {
            as[i]= gen.next();
        }

        assertArrayEquals(expected, as);
    }

}
