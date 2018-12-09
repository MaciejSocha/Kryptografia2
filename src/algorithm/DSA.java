package algorithm;

import java.io.File;
import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DSA implements Algorithm {
    private BigInteger q;
    private BigInteger p;
    private BigInteger g;
    private File file;

    @Override
    public boolean verifyFile(String publicKey, BigInteger[] signature, File file) {
        this.file = file;
        generateNumbers();
        BigInteger y = new BigInteger(publicKey);
        return signature[0].equals(calculateV(g, signature[0], y, p, signature[1], q));
    }

    private BigInteger calculateR(BigInteger g, BigInteger k, BigInteger p, BigInteger q) {
        BigInteger ret = g.modPow(k, p);
        return ret.mod(q);
    }

    private BigInteger calculateV(BigInteger g, BigInteger r, BigInteger y, BigInteger p, BigInteger s, BigInteger q) {
        BigInteger w = calculateW(s, q);
        BigInteger ret = (g.modPow(calculateU2(r, w, q), p).multiply(y.modPow(calculateU2(r, w, q), p))).mod(p);
        ret = ret.mod(q);
        return ret;
    }

    private BigInteger calculateW(BigInteger s, BigInteger q) {
        s = BigInteger.ONE.divide(s);
        return s.mod(q);
    }

    private BigInteger calculateS(BigInteger r, BigInteger k, BigInteger x, BigInteger q) {
        return (k.pow(-1).multiply(calculateH().add(x.multiply(r)))).mod(q);
    }

    private BigInteger calculateH() {
        return new BigInteger(Sha1.hasz(file));
    }

    private BigInteger calculateU2(BigInteger r, BigInteger w, BigInteger q) {
        return ((r.mod(q)).multiply(w.mod(q))).mod(q);
    }

    private BigInteger calculateY(BigInteger g, BigInteger x, BigInteger p) {
        return g.modPow(x, p);
    }

    @Override
    public String[] generateKey(String privateKey, File file) {
        Random random = new Random();
        this.file = file;
        generateNumbers();
        BigInteger k = new BigInteger(random.nextInt(159), new Random());
        BigInteger x = new BigInteger(random.nextInt(159), new Random());
        BigInteger r = calculateR(g, k, p, q);
        BigInteger y = calculateY(g, x, p);
        BigInteger s = calculateS(r, k, x, q);
        String[] ret = new String[3];
        ret[0] = r.toString();
        ret[1] = s.toString();
        ret[2] = y.toString();
        return ret;
    }

    private void generateNumbers() {
        Random r = new Random();
        q = BigInteger.probablePrime(160, new Random());

        int l = -10;
        while (l % 64 != 0)
            l = ThreadLocalRandom.current().nextInt(512, 1024);
        p = BigInteger.probablePrime(l, new Random());
        BigInteger h = new BigInteger(r.nextInt(l - 1), new Random());
        g = (h.modPow((p.subtract(BigInteger.ONE)).divide(q), p));
    }
}
