package com.aut;

/**
 * this class is for encoding a byte array to string and decoding a string to a byte array.
 */
 class Coding {
    private byte[] bytes;
    private int[] bits;
    private char[] chars;

    /**
     *
     * @param bytes bytes that will be encoded to a string.
     */
    Coding(byte[] bytes) {
        this.bytes = bytes;
        bits = new int[bytes.length * 8];
        chars = new char[bits.length / 6 + 1];
    }

    /**
     * encodes bytes ( a byte array which is an instance of the object) to a String
     * @return encoded String
     */
    String encode() {
        bytesToBinary();
        binaryToChar();
        System.out.println("encoded");
        return new String(chars);
    }

    /**
     * decodes text to a byte array which is saves in bytes( the instance variable of the object)
     * @param text the String that would be decoded.
     * @return the decoded bytes.
     */

    byte[] decode(String text) {
        setChars(text);
        charToBinary();
        binaryToBytes();
        System.out.println("decoded");
        return bytes;

    }

    /**
     * turns each byte of the bytes array of the in
     */
    private void bytesToBinary() {
        for (int i = 0; i < bytes.length; i++) {
            String s = String.format("%8s", Integer.toBinaryString(bytes[i] & 0xff)).replace(' ', '0') + 0;
            for (int j = 0; j < 8; j++) {
                bits[i * 8 + j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }
    }

    /**
     * takes each six bits saved and converts it to a char and saves them in chars
     */

    private void binaryToChar() {
        int sixBits = 0b000000;

        for (int i = 0, charsIndex = 0; i < bits.length; sixBits = 0b000000, charsIndex++) {
            for (int j = 5; j >= 0 && i < bits.length; j--, i++) {
                sixBits = sixBits | bits[i] << j;
            }
            if (sixBits < 52) {
                if (sixBits < 26) {
                    setChar(sixBits, charsIndex, 65);
                } else {
                    setChar(sixBits, charsIndex, 71);
                }
            } else {
                if (sixBits < 62) {
                    setChar(sixBits, charsIndex, -4);
                } else if (sixBits == 62) {
                    chars[charsIndex] = '+';
                } else {
                    chars[charsIndex] = '/';
                }

            }
        }
    }

    /**
     * converts chars to bits and saves each bit in the array bits.
     */

    private void charToBinary() {
        for (int i = 0; i < chars.length; i++) {
            if ((int) chars[i] > 47 && (int) chars[i] < 58) {
                setBits(i, 4);
            } else if ((int) chars[i] > 96 && (int) chars[i] < 123) {
                setBits(i, -71);
            } else if ((int) chars[i] > 64 && (int) chars[i] < 91) {
                setBits(i, -65);
            } else if (chars[i] == '+') {
                setBits(i, 19);
            } else {
                setBits(i, 16);
            }
        }
    }

    /**
     *takes every six bits from the array bits and turns them into a byte and saves them in the bytes array
     */
    private void binaryToBytes() {
        int eightBits = 0b00000000;
        for (int i = 0, z = 0; i < bits.length; eightBits = 0b00000000, z++) {
            for (int j = 7; j >= 0 && i < bits.length; j--, i++) {
                eightBits = eightBits | bits[i] << j;
            }
            bytes[z] = (byte) eightBits;
        }


    }

    /**
     *turns index i of chars array into a binary String and saves each bit in the array bits.
     * @param i index of the c
     * @param difference the difference between the ascii code of the char and the base 64 of the char.
     */
    private void setBits(int i, int difference) {
        String s;
        s = String.format("%6s", Integer.toBinaryString((int) chars[i] + difference & 0xff)).replace(' ', '0') + 0;
        for (int j = 0; (j < 6) && (((i * 6) + j) < bits.length); j++) {
            bits[i * 6 + j] = Integer.parseInt(s.substring(j, j + 1));
        }
    }

    /**
     *saves the char of the sum of the six bits with the difference between the ascii number and the base 64 number of the char in the chars[indexNumber]
     * @param sixBits the six bits of the char
     * @param charsIndex index of the char
     * @param difference difference of the ascii number and the base 64 number.
     */
    private void setChar(int sixBits, int charsIndex, int difference) {
        chars[charsIndex] = (char) (sixBits + difference);
    }

    /**
     * svaes the input String as the a char array in the instance variable of the object (chars)
     * @param string the String that will be saved in chars.
     */
    private void setChars(String string) {
        this.chars = string.toCharArray();
    }
}