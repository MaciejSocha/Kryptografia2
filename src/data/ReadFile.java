package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class ReadFile {
    public static String[] readSignature(File file) {
        String[] ret = new String[3];
        try {
            Scanner scanner = new Scanner(file);
            for(int i = 0; i < 3; i++) {
                BigInteger pom = new BigInteger(scanner.next(), 2);
                ret[i] = pom.toString(10);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
