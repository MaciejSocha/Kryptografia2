package algorithm;

import java.io.File;

public interface Algorithm {
    /**
     * @param publicKey - klucz publiczny do zweryfikowania poprawności pliku
     * @param file - plik do weryfikacji
     * @return - czy plik jest poprawny
     */
    boolean verifyFile(String publicKey, File file);

    /**
     * @param privateKey - klucz do wygenerowania klucza publicznego
     * @param file       - plik do utworzenia klucza publicznego
     * @return - klucz publiczny
     */
    String generateKey(String privateKey, File file);
}
