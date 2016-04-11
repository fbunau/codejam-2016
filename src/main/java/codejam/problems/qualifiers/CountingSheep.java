package codejam.problems.qualifiers;

import codejam.framework.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.BitSet;

public class CountingSheep extends Problem<Integer, String> {

    public CountingSheep(String dataSetPath, String outputPath) {
        super(dataSetPath, outputPath);
    }

    @Override
    protected String solveTest(Integer inputModel) {
        BigInteger orig_nb = BigInteger.valueOf(inputModel);
        BigInteger current_nb = orig_nb.add(BigInteger.ZERO);

        if (orig_nb.equals(BigInteger.ZERO)) {
            return " INSOMNIA";
        }

        BitSet digits_seen = new BitSet();
        addSeenDigits(digits_seen, current_nb);

        while (!checkSeenAllDigits(digits_seen)) {
            current_nb = current_nb.add(orig_nb);
            addSeenDigits(digits_seen, current_nb);
        }

        return " " + current_nb.toString();
    }

    private void addSeenDigits(BitSet digits_seen, BigInteger nb) {
        String nbString = nb.toString();
        for (int i = 0; i < nbString.length(); ++i) {
            char charDigit = nbString.charAt(i);
            String stringDigit = String.valueOf(charDigit);
            int digit = Integer.parseInt(stringDigit);

            digits_seen.set(digit);
        }
    }

    private boolean checkSeenAllDigits(BitSet digits_seen) {
        for (int i = 0; i <= 9; ++i) {
            if (!digits_seen.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected Integer parseTestInput(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

}
