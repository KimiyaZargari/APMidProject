package com.aut;

/**
 *this class is used for complex shift cryptography.
 * it extends BaseShiftCryptography class.
 * can encrypt and decrypt a string using the password(string).
 */
 class ComplexShiftCryptography extends BaseCryptography {
    private String password;

  ComplexShiftCryptography(String password) {
        this.password = password;

    }

    /**
     * encrypts a string by first turning it and the password into base 64 numbers then subtracts the pass number in base 64
     * one by one from the text numbers( if it becomes less than 0 it adds 64 to it) then again turns the shifted numbers into
     * chars and returns the encrypted chars as a string.
     * @param plainText the plain text which would be encrypted
     * @return cipher text
     */

    @Override
    public String encrypt(String plainText) {
        byte[] textBytes = stringToBase64(plainText);
        byte[] passBytes = stringToBase64(password);
        for (int i = 0; i < textBytes.length; ) {
            for (int j = 0; j < passBytes.length && i < textBytes.length; i++, j++) {
                textBytes[i] -= passBytes[j];
                if (textBytes[i] < 0) {
                    textBytes[i] += 64;
                }
            }
        }
        System.out.println("encrypted");
       return base64ToString(textBytes);
    }

    /**
     * turns cipher text into plain text. just like the encrypt method except it saves the sum of the password number and the
     * cipher text instead of the subtraction. and iff it becomes more than 63 it subtracts 64 from it.
     * @param cipherText cipher text
     * @return plain text
     */

    @Override
    public String decrypt(String cipherText) {
        byte[] textBytes = stringToBase64(cipherText);
        byte[] passBytes = stringToBase64(password);
        for (int i = 0; i < textBytes.length; ) {
            for (int j = 0; j < passBytes.length && i < textBytes.length; i++, j++) {
                textBytes[i] += passBytes[j];
                if (textBytes[i] >63) {
                    textBytes[i] -= 64;
                }

            }
        }
        System.out.println("decrypted");
        return base64ToString(textBytes);
    }

    /**
     * turns string to base 64 by adding or subtracting the difference between the base 64 number and the ascii code.
     * @param string the string that would be converted to base 64 numbers.
     * @return byte array of the base 64 numbers.
     */
    private byte[] stringToBase64(String string) {
        byte[] base64 = new byte[string.length()];
        char[] stringChars = string.toCharArray();
        for (int i = 0; i < stringChars.length; i++) {
            if ((int) stringChars[i] > 64 && (int) stringChars[i] < 91) {
                base64[i] = (byte) ((int) stringChars[i] - 65);
            } else if ((int) stringChars[i] > 96 && (int) stringChars[i] < 123) {
                base64[i] = (byte) ((int) stringChars[i] - 71);
            } else if ((int) stringChars[i] > 47 && (int) stringChars[i] < 58) {
                base64[i] = (byte) ((int) stringChars[i] + 4);
            } else if (stringChars[i] == '+') {
                base64[i] = 62;
            } else {
                base64[i] = 63;
            }
        }
        return base64;
    }

    /**
     * works just like stringToBase64 method. except it gets the bytes and returns the string
     * @param bytes the base 64 bytes that should be converted to chars
     * @return the converted string
     *
     */
    private String base64ToString(byte[] bytes) {
        char[] stringChars = new char[bytes.length];
        for (int i = 0; i < stringChars.length; i++) {
            if (bytes[i] > -1 && bytes[i] < 26) {
                stringChars[i] = (char) (bytes[i] + 65);
            } else if (bytes[i] > 25 && bytes[i] < 52) {
                stringChars[i] = (char) (bytes[i] + 71);
            } else if (bytes[i] > 51 && (int) bytes[i] < 62) {
                stringChars[i] = (char) (bytes[i] - 4);
            } else if (bytes[i] == 62) {
                stringChars[i] = '+';
            } else {
                stringChars[i] = '/';
            }
        }
        return (new String(stringChars));
    }
}

