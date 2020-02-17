package com.aut;

import java.io.File;

/**
 * Created by Kimiya :) on 31/03/2017. (Main Class)
 */
public class Main {
    enum EncryptionOrDecryption {ENCRYPTION, DECRYPTION}

    enum Type {COMPLEX, SIMPLE}

    private static EncryptionOrDecryption eOrD = null;
    private static Type type = null;
    private static int simplePass = 0;
    private static String complexPass = null;
    private static String inputPath = null;
    private static String outputPath = null;
    private static Boolean remove = false;

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-es":
                    eOrD = EncryptionOrDecryption.ENCRYPTION;
                    type = Type.SIMPLE;
                    simplePass = Integer.parseInt(args[++i]);
                    break;
                case "-ec":
                    eOrD = EncryptionOrDecryption.ENCRYPTION;
                    type = Type.COMPLEX;
                    complexPass = args[++i];
                    break;
                case "-i":
                    inputPath = args[++i];
                    break;
                case "-r":
                    remove = true;
                    break;
                case "-o":
                    outputPath = args[++i];
                    break;
                case "-ds":
                    eOrD = EncryptionOrDecryption.DECRYPTION;
                    type = Type.SIMPLE;
                    simplePass = Integer.parseInt(args[++i]);

                    break;
                case "-dc":
                    eOrD = EncryptionOrDecryption.DECRYPTION;
                    type = Type.COMPLEX;
                    complexPass = args[++i];

                    break;
            }

        }
        if(eOrD == null || type == null || inputPath == null || simplePass == 0 || complexPass == null){
            System.out.println("wrong input");
            System.exit(0);
        }
        final File folder = new File(inputPath);
        runProgram(folder);


    }

  private static void runProgram(final File folder) {
        if (folder.isDirectory()) {
            File[] file = folder.listFiles();
            for (File aFile : file) {
                if (aFile.isDirectory()) {
                    runProgram(aFile);
                } else {
                    InputFileReader inputFile = new InputFileReader(aFile.getAbsolutePath());
                    Coding coding = new Coding(inputFile.readFile());
                    byte[] bytes;
                    BaseCryptography cryptography;
                    if (type == Type.SIMPLE) {
                        cryptography = new SimpleShiftCryptography(simplePass);

                    } else {
                        cryptography = new ComplexShiftCryptography(complexPass);
                    }
                    if (eOrD == EncryptionOrDecryption.ENCRYPTION) {
                        bytes = coding.decode(cryptography.encrypt(coding.encode()));


                    } else {
                        bytes = coding.decode(cryptography.decrypt(coding.encode()));


                    }
                    if (remove) {
                        aFile.delete();
                    }

                    OutputFileWriter outputFile = new OutputFileWriter(outputPath, inputFile);
                    outputFile.writeFile(bytes);

                }
            }
        } else {
            System.out.println(folder.getName());
        }
    }
}

