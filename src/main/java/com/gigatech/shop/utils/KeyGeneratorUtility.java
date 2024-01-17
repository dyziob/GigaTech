package com.gigatech.shop.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {

    public static KeyPair generateRsaKey(){
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2248);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e){
            throw new IllegalStateException();
        }
        return keyPair;
    }
}
