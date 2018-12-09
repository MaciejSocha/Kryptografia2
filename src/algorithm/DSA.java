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
        return calculateR(g,k,p,q).equals(calculateV(g,y,p,q));
    }

    private BigInteger calculateR(BigInteger g, BigInteger k, BigInteger p, BigInteger q) {
        BigInteger ret = g.modPow(k,p);
        return ret.mod(q);
    }

    private BigInteger calculateV(BigInteger g, BigInteger y, BigInteger p, BigInteger q) {
        BigInteger r = calculateR(g,k,p,q);
        BigInteger w = calculateW(q);
        BigInteger ret = (g.modPow(calculateU2(r,w,q), p).multiply(y.modPow(calculateU2(r,w,q), p))).mod(p);
        ret = ret.mod(q);
        return ret;
    }

    private BigInteger calculateW(BigInteger q) {
        BigInteger s = calculateS(k,x,q);
        BigInteger minusOne = new BigInteger("-1");
        return s.modPow(minusOne, q);
    }

    private BigInteger calculateS(BigInteger k, BigInteger x, BigInteger q) {
        BigInteger r = calculateR(g,k,p,q);
        return k.pow(-1).multiply(calclulateH().add(x.multiply(r)));
    }

    private BigInteger calclulateH() {
        return new BigInteger("0");//TODO
    }

    private BigInteger calculateU2(BigInteger r, BigInteger w, BigInteger q) {
        return ((r.mod(q)).multiply(w.mod(q))).mod(q);
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
