package codejam.qualifiers.model;

import codejam.problems.qualifiers.model.JamCoin;
import codejam.problems.qualifiers.model.JamWallet;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JamWalletTest {

    @Test
    public void validJamWallet() {
        JamWallet wallet = new JamWallet(6, 3);

        wallet.add(new JamCoin("100011", Arrays.asList($(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L))));
        wallet.add(new JamCoin("111111", Arrays.asList($(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(10101L))));
        wallet.add(new JamCoin("111001", Arrays.asList($(3L), $(88L), $(5L), $(1938L), $(7L), $(208L), $(3L), $(20L), $(11L))));

        assertTrue(wallet.verify());
    }

    @Test
    public void invalidJamWalletTooManyCoins() {
        JamWallet wallet = new JamWallet(6, 3);

        wallet.add(new JamCoin("100011", Arrays.asList($(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L))));
        wallet.add(new JamCoin("111111", Arrays.asList($(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(10101L))));
        wallet.add(new JamCoin("111001", Arrays.asList($(3L), $(88L), $(5L), $(1938L), $(7L), $(208L), $(3L), $(20L), $(11L))));
        wallet.add(new JamCoin("111001", Arrays.asList($(3L), $(88L), $(5L), $(1938L), $(7L), $(208L), $(3L), $(20L), $(11L))));

        assertFalse(wallet.verify());
    }

    @Test
    public void invalidJamWalletDuplicateCoins() {
        JamWallet wallet = new JamWallet(6, 3);

        wallet.add(new JamCoin("100011", Arrays.asList($(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L))));
        wallet.add(new JamCoin("111111", Arrays.asList($(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(10101L))));
        wallet.add(new JamCoin("111111", Arrays.asList($(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(10101L))));

        assertFalse(wallet.verify());
    }

    @Test
    public void invalidJamWalletContainsInvalidCoin() {
        JamWallet wallet = new JamWallet(6, 3);

        wallet.add(new JamCoin("100011", Arrays.asList($(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L))));
        wallet.add(new JamCoin("110111", Arrays.asList($(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(10101L))));
        wallet.add(new JamCoin("111001", Arrays.asList($(3L), $(88L), $(5L), $(1938L), $(7L), $(208L), $(3L), $(20L), $(11L))));

        assertFalse(wallet.verify());
    }

    @Test
    public void walletIsOutputtedCorectly() {
        JamWallet wallet = new JamWallet(6, 3);

        wallet.add(new JamCoin("100011", Arrays.asList($(5L), $(13L), $(147L), $(31L), $(43L), $(1121L), $(73L), $(77L), $(629L))));
        wallet.add(new JamCoin("111111", Arrays.asList($(21L), $(26L), $(105L), $(1302L), $(217L), $(1032L), $(513L), $(13286L), $(10101L))));
        wallet.add(new JamCoin("111001", Arrays.asList($(3L), $(88L), $(5L), $(1938L), $(7L), $(208L), $(3L), $(20L), $(11L))));

        String expected =
                "100011 5 13 147 31 43 1121 73 77 629\n" +
                "111111 21 26 105 1302 217 1032 513 13286 10101\n" +
                "111001 3 88 5 1938 7 208 3 20 11";

        assertEquals(expected, wallet.toString());
    }

    private BigInteger $(long nb) {
        return BigInteger.valueOf(nb);
    }
}
