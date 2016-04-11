package codejam.problems.qualifiers.model;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JamCoin {

    private final String jamString;
    private final List<BigInteger> divisors;

    public JamCoin(String jamString, List<BigInteger> divisors) {
        this.jamString = jamString;
        this.divisors = Collections.unmodifiableList(divisors);
    }

    public boolean verify() {
        if (jamString.startsWith("0")) {
            return false;
        }
        if (jamString.endsWith("0")) {
            return false;
        }

        for (int base = 2; base <= 10; ++base) {
            BigInteger nb = new BigInteger(jamString, base);
            BigInteger divisor = divisors.get(base-2);
            if (!divisorNonTrivial(nb, divisor)) {
                return false;
            }
        }
        return true;
    }

    public int length() {
        return jamString.length();
    }

    public String getJamString() {
        return jamString;
    }

    private boolean divisorNonTrivial(BigInteger nb, BigInteger divisor) {
        return (nb.mod(divisor).equals(BigInteger.ZERO)) && !divisor.equals(BigInteger.ONE) && !divisor.equals(nb);
    }

    @Override
    public String toString() {
        String divisorsStr = divisors.stream()
                .map(l -> "" + l)
                .collect(Collectors.joining(" "));

        return new StringBuilder().append(jamString).append(" ").append(divisorsStr).toString();
    }
}
