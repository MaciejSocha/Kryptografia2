package algorithm;

import java.util.List;

public interface Algorithm {
    /**
     *
     * @param message - ciąg bitów do zakodowania
     * @param key - klucz w postci ciągu heksadecymalnego
     * @return - zakodowany ciąg bitów
     */
    public List<Byte> encode (List<Byte> message, String key);

    /**
     *
     * @param message - ciąg zakodowanych bitów do rozkodowania
     * @param key - klucz w postaci ciągu heksadecymalnego
     * @return - odkodoawny ciąg bitów
     */
    public List<Byte> decode (List<Byte> message, String key);
}
