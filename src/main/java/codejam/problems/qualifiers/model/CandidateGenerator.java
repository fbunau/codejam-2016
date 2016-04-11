package codejam.problems.qualifiers.model;

public class CandidateGenerator {

    private final int n;
    private long current = 0;

    public CandidateGenerator(int n) {
        this.n = n;
    }

    public String next() throws Exception {
        String midStr = Long.toString(current++, 2);
        while (midStr.length() <= n - 3) midStr = "0" + midStr;
        String candidate = "1" + midStr + "1";

        if (candidate.length() >= n+2) {
            throw new Exception("Exeeded limit for coin generation");
        }
        return candidate;
    }

}
