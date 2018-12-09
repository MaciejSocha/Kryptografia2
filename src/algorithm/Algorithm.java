package algorithm;

import java.io.File;

public interface Algorithm {
    /**
     * @param publicKey - klucz publiczny do zweryfikowania poprawności pliku
     * @return - czy plik jest poprawny
     */
    boolean verifyKey(String publicKey);

    /**
     * @param privateKey - klucz do wygenerowania klucza publicznego
     * @param file       - plik do utworzenia klucza publicznego
     * @return - klucz publiczny
     */
    String generateKey(String privateKey, File file);
}
