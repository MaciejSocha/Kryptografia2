package algorithm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {
    public String hasz(String message) {

        byte hash[];
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            hash = sha1.digest(message.getBytes(StandardCharsets.UTF_8));
            BigInteger bi = new BigInteger(1, hash);
            String text = bi.toString(16);

            while (text.length() < 32) {
                text += "0";
            }

            return text;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
