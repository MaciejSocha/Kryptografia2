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

            //Postać znakowa?
            BigInteger bi = new BigInteger(1, hash);
            //Wartość hex
            String text = bi.toString(16);

            //Uzuepłnienie 0 do 32 bitów
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
