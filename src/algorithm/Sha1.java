package algorithm;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {
    public static String hasz(File file) {

        byte hash[];
        String text="0";
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            hash = sha1.digest(Files.readAllBytes(file.toPath()));

            //Postać znakowa?
            BigInteger bi = new BigInteger(1, hash);
            //Wartość hex
            text = bi.toString(16);

            //Uzuepłnienie 0 do 32 bitów
            while (text.length() < 32) {
                text += "0";
            }

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
