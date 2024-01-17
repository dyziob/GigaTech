package com.gigatech.shop.utils;
// Klasa KeyGeneratorUtility jest narzędziem do generowania kluczy RSA.
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {
    // Metoda statyczna generująca nowy zestaw kluczy RSA.
    public static KeyPair generateRsaKey(){
        KeyPair keyPair;
        try {
            // Utworzenie generatora kluczy dla algorytmu RSA.
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // Inicjalizacja generatora kluczy z długością klucza 2248 bitów.
            keyPairGenerator.initialize(2248);
            // Wygenerowanie pary kluczy.
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e){
            // W przypadku błędu rzucenie wyjątkiem IllegalStateException.
            throw new IllegalStateException();
        }
        return keyPair;
    }
}
