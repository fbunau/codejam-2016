package codejam.problems.qualifiers.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JamWallet {

    private List<JamCoin> jamCoins = new ArrayList<>();

    private final int requiredJamcoinLength;
    private final int requiredJamCoinNb;

    public JamWallet(int requiredJamcoinLength, int requiredJamCoinNb) {
        this.requiredJamcoinLength = requiredJamcoinLength;
        this.requiredJamCoinNb = requiredJamCoinNb;
    }

    public void add(JamCoin coin) {
        jamCoins.add(coin);
    }

    public boolean verify() {
        if (jamCoins.size() != requiredJamCoinNb) {
            return false;
        }

        Set<String> coins = new HashSet<>();
        for (JamCoin jamCoin: jamCoins) {
            if (coins.contains(jamCoin.getJamString())) {
                return false;
            }
            if (jamCoin.length() != requiredJamcoinLength) {
                return false;
            }
            if (!jamCoin.verify()) {
                return false;
            }
            coins.add(jamCoin.getJamString());
        }
        return true;
    }

    public int size() {
        return jamCoins.size();
    }

    @Override
    public String toString() {
        return "\n" + jamCoins.stream()
                       .map(JamCoin::toString)
                       .collect(Collectors.joining("\n"));
    }

}
