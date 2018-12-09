package methods;

import java.math.BigInteger;

public class ExponentiationBySquaring {
    public static BigInteger exponentiationBySquaring(BigInteger g, BigInteger x, BigInteger p) {
        BigInteger r = BigInteger.ONE;
        while (x.compareTo(BigInteger.ZERO) == 1) {
            if (x.mod(new BigInteger("2")) == BigInteger.ONE) {
                r = r.multiply(g).mod(p);
            }

            g = g.multiply(g).mod(p);
        }
        return r;
    }
}
