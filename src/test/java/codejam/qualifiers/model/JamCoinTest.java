package codejam.qualifiers.model;

import codejam.problems.qualifiers.model.JamCoin;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class JamCoinTest {

    private Object[] validJamCoinData() {
        return new Object[]{
            new Object[]{"100011", new BigInteger[] { $(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L) }},
            new Object[]{"111111", new BigInteger[] { $(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(10101L) }},
            new Object[]{"111001", new BigInteger[] { $(3L), $(88L), $(5L), $(1938L), $(7L), $(208L), $(3L), $(20L), $(11L) }},
        };
    }

    private Object[] invalidJamCoinData() {
        return new Object[]{
                new Object[]{"110111", new BigInteger[] { $(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L) }},
                new Object[]{"110111", new BigInteger[] { $(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(337L) }},
                new Object[]{"110111", new BigInteger[] { $(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(1L) }},
                new Object[]{"010101", new BigInteger[] { $(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(7L) }},
                new Object[]{"101010", new BigInteger[] { $(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(7L) }},
                new Object[]{"110111", new BigInteger[] { $(1L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L) }},
                new Object[]{"110111", new BigInteger[] { $(35L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L) }},
        };
    }

    @Test
    @Parameters(method = "validJamCoinData")
    public void validJamCoins(String jamString, BigInteger[] divisors) {
        JamCoin jamCoin = new JamCoin(jamString, Arrays.asList(divisors));
        assertTrue(jamCoin.verify());
    }

    @Test
    @Parameters(method = "invalidJamCoinData")
    public void invalidJamCoins(String jamString, BigInteger[] divisors) {
        JamCoin jamCoin = new JamCoin(jamString, Arrays.asList(divisors));
        assertFalse(jamCoin.verify());
    }

    private BigInteger $(long nb) {
        return BigInteger.valueOf(nb);
    }
}
