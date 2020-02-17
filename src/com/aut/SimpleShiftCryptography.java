package com.aut;

/**
 *this class is used for simple shift cryptography.
 * it extends BaseShiftCryptography class.
 * can encrypt and decrypt a string using the password(int).
 */
public class SimpleShiftCryptography extends BaseCryptography {
    private int password;

    SimpleShiftCryptography(int password) {
        this.password = password;
    }

    /**
     * encrypts String by shifting each character by password.
     * @param plainText plain text
     * @return cipher text
     */
    @Override
    public String encrypt(String plainText) {
        char[] cipherArray = plainText.toCharArray();
        cipherArray = replaceCharForEn(cipherArray, 65, 91);
        cipherArray = replaceCharForEn(cipherArray, 97, 123);
        cipherArray = replaceCharForEn(cipherArray, 48, 58);
        String cipherText = new String(cipherArray);
        System.out.println("encrypted");
        return cipherText;
    }
    /**
     * decrypts String by shifting each character by password.
     * @param cipherText cipher text
     * @return plain text
     */
    @Override
    public String decrypt(String cipherText) {
        char[] plainArray = cipherText.toCharArray();
        plainArray = replaceCharForDe(plainArray, 65, 91);
        plainArray = replaceCharForDe(plainArray, 97, 123);
        plainArray = replaceCharForDe(plainArray, 48, 58);
        String plainText = new String(plainArray);
        System.out.println("decrypted");
        return plainText;
    }

    /**
     * shifts char for encryption.
     * @param plainArray chars of the plain text
     * @param from the bottom ascii code
     * @param to the top ascii code
     * @return chars of cipher text
     */
    private char[] replaceCharForEn(char[] plainArray, int from, int to) {
        for (int i = 0; i < plainArray.length; i++) {
            if ((int) plainArray[i] >= from && (int) plainArray[i] < to)
                plainArray[i] = ((int) plainArray[i] - password) < from ? (char) ((int) plainArray[i] - password + (to - from)) : (char) ((int) plainArray[i] - password);
        }
        return plainArray;

    }
    /**
     * shifts char for decryption.
     * @param cipherArray chars of the cipher text
     * @param from the bottom ascii code
     * @param to the top ascii code
     * @return chars of plain text
     */

    private char[] replaceCharForDe(char[] cipherArray, int from, int to) {
        for (int i = 0; i < cipherArray.length; i++) {
            if ((int) cipherArray[i] >= from && (int) cipherArray[i] < to)
                cipherArray[i] = ((int) cipherArray[i] + password) >= to ? (char) ((int) cipherArray[i] + password - (to - from)) : (char) ((int) cipherArray[i] + password);
        }
        return cipherArray;
    }
}

