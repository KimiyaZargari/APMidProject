package com.aut;

import java.io.*;

/**
 * this class is used for writing files in a specific path.
 */
class OutputFileWriter {
    private FileOutputStream fout;

    /**
     * if output path is not given the files will be saves in the input path directory
     * if output path does not exist, file would be saved in the output path's directory.
     * otherwise it will be savedin the path given
     *
     * @param outputPath the path that the file should be saved in
     * @param inputFile  the innput path
     */

    OutputFileWriter(String outputPath, InputFileReader inputFile) {
        try {
            if (outputPath != null) {
                File file = new File(outputPath);
                if (!file.exists()) {
                    fout = new FileOutputStream(outputPath);
                } else if (file.isDirectory()) {
                    fout = new FileOutputStream(outputPath + "\\" + inputFile.getFileName() + inputFile.getExtension());
                } else
                    fout = new FileOutputStream(file);
            } else {
                fout = new FileOutputStream(inputFile.getDirectory() + "\\" + inputFile.getFileName() + "(1)" + inputFile.getExtension());
            }
        } catch (
                IOException e)

        {
            System.out.println("error: " + e);
        }

    }

    /**
     * writes bytes given in the output path.
     *
     * @param bytes bytes that should be written
     */
    void writeFile(byte[] bytes) {
        try {
            fout.write(bytes);
            System.out.println("written ");
        } catch (IOException e) {
            System.out.println("could not write file");
        }

    }

}
