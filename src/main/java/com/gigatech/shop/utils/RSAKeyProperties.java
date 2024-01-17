package com.gigatech.shop.utils;
// Klasa RSAKeyProperties reprezentuje właściwości kluczy RSA, a także generuje klucz publiczny i prywatny.
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Getter
@Setter
public class RSAKeyProperties {
    // Klucz publiczny RSA.
    private RSAPublicKey publicKey;
    // Klucz prywatny RSA.
    private RSAPrivateKey privateKey;

    // Konstruktor klasy, generujący nowy zestaw kluczy RSA przy tworzeniu instancji obiektu.
    public RSAKeyProperties(){
        KeyPair pair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
    }
}
