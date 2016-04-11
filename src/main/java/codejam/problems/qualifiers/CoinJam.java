package codejam.problems.qualifiers;

import codejam.framework.Problem;
import codejam.problems.qualifiers.model.CandidateGenerator;
import codejam.problems.qualifiers.model.JamCoin;
import codejam.problems.qualifiers.model.JamWallet;
import javaslang.Tuple2;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoinJam extends Problem<Tuple2<Integer, Integer>, JamWallet> {

    public CoinJam(String dataSetPath, String outputPath) {
        super(dataSetPath, outputPath);
    }

    @Override
    protected JamWallet solveTest(Tuple2<Integer, Integer> intTuple) {
        int n = intTuple._1;
        int j = intTuple._2;
        JamWallet wallet = new JamWallet(n, j);

        CandidateGenerator candidateGen = new CandidateGenerator(n);
        for (int ij = 0; ij < j; ++ij) {
            wallet.add(generateCoin(candidateGen));
            reportProgress(wallet);
        }

        if (wallet.verify()) {
            return wallet;
        }
        throw new RuntimeException("Invalid answer");
    }

    private JamCoin generateCoin(CandidateGenerator candidateGen) {
        while (true) {
            String coinId = getCoinId(candidateGen);

            Optional<List<BigInteger>> divisors = findAllDivisorsForCoin(coinId);

            if (divisors.isPresent()) {
                return new JamCoin(coinId, divisors.get());
            }
        }
    }

    private void reportProgress(JamWallet wallet) {
        if (wallet.size() % 10 == 0) {
            System.out.println(wallet.size());
        }
    }

    private Optional<List<BigInteger>> findAllDivisorsForCoin(String coinId) {
        List<BigInteger> divisors = new ArrayList<>();
        for (int base = 2; base <= 10; ++base) {
            BigInteger decInBase = new BigInteger(coinId, base);
            Optional<BigInteger> opt = findDivisor(decInBase);
            if (opt.isPresent()) {
                divisors.add(opt.get());
            } else {
                return Optional.empty();
            }
        }
        return Optional.of(divisors);
    }

    private String getCoinId(CandidateGenerator gen) {
        try {
            return gen.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Optional<BigInteger> findDivisor(BigInteger nb) {
        BigInteger two = BigInteger.valueOf(2);

        if (nb.mod(two).equals(BigInteger.ZERO) && nb.compareTo(two) < 0) {
            return Optional.of(two);
        }

        // big numbers optimization
        BigInteger sqrt = sqrt(nb);
        if (nb.toString().length() > 10) {
            sqrt = sqrt(sqrt);
        }

        BigInteger current = BigInteger.valueOf(3);

        while (true) {

            if (nb.mod(current).equals(BigInteger.ZERO)) {
                return Optional.of(current);
            } else {
                if (current.compareTo(sqrt) < 0) {
                    current = current.add(two);
                } else {
                    return Optional.empty();
                }
            }
        }

    }

    private static BigInteger newtonIteration(BigInteger n, BigInteger x0)
    {
        final BigInteger x1 = n.divide(x0).add(x0).shiftRight(1);
        return x0.equals(x1)||x0.equals(x1.subtract(BigInteger.ONE)) ? x0 : newtonIteration(n, x1);
    }

    public static BigInteger sqrt(final BigInteger number)
    {
        if(number.signum() == -1)
            throw new ArithmeticException("We can only calculate the square root of positive numbers.");
        return newtonIteration(number, BigInteger.ONE);
    }

    @Override
    protected Tuple2<Integer, Integer> parseTestInput(BufferedReader br) throws IOException {
        String input = br.readLine();
        String[] tokens = input.split(" ");

        return new Tuple2<>(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }

}
