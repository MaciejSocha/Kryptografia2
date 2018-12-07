package methods;

import java.util.ArrayList;
import java.util.List;

public class Conversions {
    public static int cropValue;
    public static boolean cropIsSet = false;

    //Funkcja zamienia string w listę elementów o jednakowej długości złożonych z 64 "bitów"
    public static List<List<Byte>> stringTo64Byte(String s) {
        byte[] bytes = s.getBytes();
        List<Integer> pom3 = new ArrayList<>();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                pom3.add((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }

        List<List<Byte>> readyBytes = new ArrayList<>();
        int pom1 = 0;
        int pom2 = 0;
        readyBytes.add(new ArrayList<>());

        for (int i : pom3) {
            if (pom1 == 64) {
                pom2++;
                readyBytes.add(new ArrayList<>());
                pom1 = 0;
            }
            readyBytes.get(pom2).add((byte) i);
            pom1++;
        }
        if (readyBytes.get(readyBytes.size() - 1).size() < 64) {
            int sizef = 64 - readyBytes.get(readyBytes.size() - 1).size();
            for (int i = 0; i < sizef; i++) {
                readyBytes.get(readyBytes.size() - 1).add((byte) 0);
            }
        }
        return readyBytes;
    }

    //Przekształca liczbę mniejszą niż 15 na jej bitową postać
    public static List<Byte> numberTo4Byte(byte number) {
        List<Byte> ret = new ArrayList<>();
        int val = number;
        for (int i = 0; i < 4; i++) {
            ret.add((byte)((val & 8) == 0 ? 0 : 1));
            val <<= 1;
        }
        return ret;
    }

    public static List<Byte> numberTo8Byte(byte number) {
        List<Byte> ret = new ArrayList<>();
        int val = number;
        for (int i = 0; i < 8; i++) {
            ret.add((byte)((val & 128) == 0 ? 0 : 1));
            val <<= 1;
        }
        return ret;
    }

    //Przerabia gotowy ciąg binarny w postaci string na ciąg binarny w postaci listy jednakowych ciągów binarnych o długości 64
    public static List<List<Byte>> encodedMessageTo64Byte(List<Byte> message) {
        List<List<Byte>> ret = new ArrayList<>();
        int size = message.size()/64;
        if(message.size()%64!= 0) {
            size +=1;
        }
        if(!cropIsSet) {
            cropValue = 64 - message.size() % 64;
            if (cropValue == 64) {
                cropValue = 0;
            }
            cropIsSet = true;
        }
        for(int i = 0; i < size; i++) {
            ret.add(new ArrayList<>());
            for(int j = 0; j < 64; j++) {
                if(i*64+j >= message.size()) {
                    ret.get(i).add((byte)0);
                } else {
                    ret.get(i).add(message.get(i * 64 + j));
                }
            }
        }
        return ret;
    }

    //Przerabia ciąg znaków na ciąg binarny
    public static String toBinaryString(String message) {
        List<List<Byte>> pom = stringTo64Byte(message);
        StringBuilder sb = new StringBuilder();
        pom.forEach(p -> p.forEach(sb::append));
        return sb.toString();
    }

    //Przerabia ciąg binarny na ciag znaków
    public static String toNormalCharacters(String binary) {
        int pom = binary.length()/8;
        String[] ss = new String[pom];

        for(int i = 0; i < pom; i++) {
            ss[i] = binary.substring(i*8, i*8+8);
        }

        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < ss.length; i++ ) {
            sb.append( (char)Integer.parseInt( ss[i], 2 ) );
        }

        return sb.toString();
    }
}
