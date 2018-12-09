package algorithm;

import java.io.File;
import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DSA implements Algorithm {
    private BigInteger q;
    private BigInteger p;
    private BigInteger k;
    private BigInteger g;
    private BigInteger x;
    private BigInteger h;
    private BigInteger y;

    @Override
    public boolean verifyFile(String publicKey, File file) {
        return false;
    }

    @Override
    public String generateKey(String privateKey, File file) {
        return null;
    }

    public void generateNumbers() {
        Random r = new Random();
        q = BigInteger.probablePrime(160, new Random());

        int l =-10;
        while (l % 64 != 0)
            l = ThreadLocalRandom.current().nextInt(512, 1024);
        p = BigInteger.probablePrime(l, new Random());

        k = new BigInteger(r.nextInt(159), new Random());

        x = new BigInteger(r.nextInt(159), new Random());

        h = new BigInteger(r.nextInt(l - 1), new Random());

        g = (h.modPow((p.subtract(BigInteger.ONE)).divide(q), p));

        y = g.modPow(x, p);
    }
}
