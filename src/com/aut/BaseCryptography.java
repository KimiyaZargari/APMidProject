package com.aut;

/**
 * this class is an abstract class for cryptography.
 */
public abstract class BaseCryptography {

    public abstract String encrypt(String plainText);

    public abstract String decrypt(String cipherText);

}
