package data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class SaveToFile {

    public static void save(BigInteger[] message, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        for(BigInteger bigInteger : message) {
            stringBuilder.append(bigInteger.toString(2));
            stringBuilder.append(System.getProperty("line.separator"));
        }

        try {
            PrintWriter out = new PrintWriter("" + fileName + ".txt");
            out.write(stringBuilder.toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
